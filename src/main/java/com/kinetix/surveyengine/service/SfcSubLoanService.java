package com.kinetix.surveyengine.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.*;
import com.kinetix.surveyengine.entity.SubLoan;
import com.kinetix.surveyengine.exception.MyRuntimeException;
import com.kinetix.surveyengine.mapper.SubLoanMapper;
import com.kinetix.surveyengine.restful.RetResponse;
import com.kinetix.surveyengine.restful.RetResult;
import com.kinetix.surveyengine.util.DateUtils;
import com.kinetix.surveyengine.util.StringUtil;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author melo
 * @date 2023-06-16 11:53
 */
@Service
public class SfcSubLoanService {

    private Logger logger = LoggerFactory.getLogger(SfcSubLoanService.class);


    @Resource
    private SubLoanMapper subLoanMapper;

    public int insert(SubLoan record) {
        return subLoanMapper.insert(record);
    }


    public RetResult<JSONObject> validation(MultipartFile file, HttpServletResponse response) throws Exception {
        LinkedHashMap<Integer, LinkedHashMap<String, String>> subLoanMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> subLoanErrorMap = new LinkedHashMap<>();
        Workbook workbook = new Workbook(file.getInputStream());
        Worksheet worksheet = workbook.getWorksheets().get("Data Migration");
        Worksheet worksheetForm = workbook.getWorksheets().get("Form");
        if (worksheet == null || worksheetForm == null) {
            System.out.println("error");
            throw new MyRuntimeException(500, "worksheet error");
        }
        Cells cellCollection = worksheet.getCells();
        Cells cellCollectionFrom = worksheetForm.getCells();
        int row = cellCollection.getMaxDataRow();
        int formRow = cellCollectionFrom.getMaxDataRow();
        LinkedHashMap<Integer, LinkedHashMap<String, String>> dataMigrationMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, LinkedHashMap<Integer, String>> allcolumnMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> errorAllMap = new LinkedHashMap();
        HashSet<String> tableNameSet = new HashSet<>();
        try {
            for (int i = 2; i <= row - 4; i++) {
                int actualRow = i + 1;
                LinkedHashMap<String, String> rowMap = new LinkedHashMap<>();
                tableNameSet.add(cellCollection.get(i, 1).getStringValue().trim());
                if (tableNameSet.size() >= 2) {
                    String errorMessage = "The " + actualRow + " row and the B column (Data Migration Tab) is  inconsistent";
                    errorAllMap.put("B", errorMessage);
                    break;
                }
                rowMap.put("tableName", cellCollection.get(i, 1).getStringValue());
                rowMap.put("columnName", cellCollection.get(i, 2).getStringValue());
                rowMap.put("dataType", cellCollection.get(i, 3).getStringValue());
                rowMap.put("length/Format", cellCollection.get(i, 4).getStringValue());
                rowMap.put("mandatory", cellCollection.get(i, 5).getStringValue());
                rowMap.put("Mapping", cellCollection.get(i, 7).getStringValue());
                dataMigrationMap.put(i, rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (errorAllMap.isEmpty()) {
            for (Map.Entry<Integer, LinkedHashMap<String, String>> entry : dataMigrationMap.entrySet()) {
                Integer key = entry.getKey();
                String mappingValue = entry.getValue().get("columnName");
                LinkedHashMap<String, String> value = entry.getValue();
                String mapping = value.get("Mapping");
                LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<>();
                Integer column = StringUtil.columnToIndex(mapping);
                for (int j = 1; j <= formRow; j++) {
                    Cell cell = cellCollectionFrom.get(j, column);
                    checkCell(cell, value, errorAllMap, columnMap);
                }

                if (mappingValue.equals("CASE_ID")) {
                    checkDuplicate(errorAllMap, columnMap, mapping);
                }
                allcolumnMap.put(key, columnMap);
            }
        }

        JSONObject jsonObject = null;
        if (!errorAllMap.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Run fail !!!" + "\n\n");
            sb.append("Total number of error records : " + errorAllMap.size() + "\n\n");

            for (String script : errorAllMap.values()) {
                sb.append(script).append("\n");
            }
            String content = sb.toString(); // 获取字符串内容
            response.setContentType("text/plain; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=error.txt");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(content.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            jsonObject = JSONObject.parseObject(JSON.toJSONString(errorAllMap));

            System.out.println(JSON.toJSONString(errorAllMap));

            return RetResponse.say(500, "error", jsonObject);
        } else {
            saveSubLoanMap(allcolumnMap, dataMigrationMap, response);
            return RetResponse.say(200, "success", jsonObject);
        }
    }

    private void checkDuplicate(LinkedHashMap<String, String> errorAllMap, LinkedHashMap<Integer, String> columnMap, String mapping) {

        HashSet<String> hashSet = new HashSet<>();

        for (Map.Entry<Integer, String> entry : columnMap.entrySet()) {
            if (hashSet.contains(entry.getValue().trim())) {
                int actualRow = entry.getKey() + 1;
                String errorMessage = "Duplicated CASE_ID : \"" + entry.getValue() + "\" in Row " + actualRow + " Column " + mapping;
                errorAllMap.put(mapping, errorMessage);
            } else {
                hashSet.add(entry.getValue().trim());
            }
        }

    }

    private void checkCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<Integer, String> columnMap) {

        String dataType = valueMap.get("dataType");

        if (dataType.equals("VARCHAR2")) {
            checkVarcharCell(cell, valueMap, errorMap, columnMap);
        } else if (dataType.equals("DATE")) {
            checkDateCell(cell, valueMap, errorMap, columnMap);
        } else if (dataType.equals("NUMBER")) {
            checkNumber(cell, valueMap, errorMap, columnMap);
        }
    }

    private void checkNumber(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<Integer, String> columnMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";
        String numberCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString();

        if (mandatory.equals("Y") && StringUtils.isEmpty(numberCell)) {
            errorMessage = "Missing mandatory data in Row " + row + " Column " + column;
            logger.error(errorMessage);
            errorMap.put(row + "--" + column, errorMessage);
        } else {
            if (numberCell != null) {
                //获取单元格类型
                int type = cell.getType();
                if (CellValueType.IS_NUMERIC != type) {
                    errorMessage = "Type error at line " + row + "column " + column + " numeric type required";
                    logger.error(errorMessage);
                    errorMap.put(row + "--" + column, errorMessage);
                } else {
                    BigDecimal numberValue = new BigDecimal(cell.getValue().toString());
                    columnMap.put(cell.getRow(), numberValue.toPlainString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && columnMap.get(cell.getRow()) == null) {
            columnMap.put(cell.getRow(), null);
        }
        return;

    }

    private void checkDateCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<Integer, String> columnMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";
        String dateCell = (cell == null || cell.getValue() == null || cell.getValue().toString().trim().equals("")) ? null : cell.getValue().toString();
        if (StringUtils.isEmpty(dateCell) && mandatory.equals("Y")) {
            errorMessage = "Missing mandatory data in Row " + row + " Column " + column;
            logger.error(errorMessage);
            errorMap.put(row + "--" + column, errorMessage);
        } else {
            if (dateCell != null) {
                if (dateCell.contains(" ")) {
                    errorMessage = "There is a space in line " + row + ",column " + column;
                    logger.error(errorMessage);
                    errorMap.put(row + "--" + column, errorMessage);
                } else if (!StringUtil.checkDateFormat(dateCell)) {
                    errorMessage = "Wrong date format : \"" + dateCell + "\" in Row " + row + " Column " + column;
                    logger.error(errorMessage);
                    errorMap.put(row + "--" + column, errorMessage);
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage)) {
            columnMap.put(cell.getRow(), dateCell);
        }
        return;
    }

    private void checkVarcharCell(Cell cell, LinkedHashMap<String, String> valueMap, LinkedHashMap<String, String> errorMap, LinkedHashMap<Integer, String> columnMap) {

        int row = cell.getRow() + 1;
        String column = valueMap.get("Mapping");
        String mandatory = valueMap.get("mandatory");
        String errorMessage = "";
        String length = valueMap.get("length/Format").trim();
        if (mandatory.equals("Y")) {
            if (cell == null || cell.getValue() == null || "".equals(StringUtils.isEmpty(cell.getValue().toString()))) {
                errorMessage = "Missing mandatory data in Row " + row + " Column " + column;
                logger.error(errorMessage);
                errorMap.put(row + "--" + column, errorMessage);
            } else {
                if (cell.getValue().toString().length() > Integer.parseInt(length)) {
                    errorMessage = "The maximum word length exceeds " + length + " : \"" + cell.getValue().toString() + "\" in Row " + row + " Column " + column;
                    logger.error(errorMessage);
                    errorMap.put(row + "--" + column, errorMessage);
                } else {
                    columnMap.put(cell.getRow(), cell.getValue().toString());
                }
            }
        } else {
            if (cell != null && cell.getValue() != null) {
                if (cell.getValue().toString().length() > Integer.parseInt(length)) {
                    errorMessage = "The maximum word length exceeds " + length + " : \"" + cell.getValue().toString() + "\" in Row " + row + " Column " + column;
                    logger.error(errorMessage);
                    errorMap.put(row + "--" + column, errorMessage);
                } else {
                    columnMap.put(cell.getRow(), cell.getValue().toString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && columnMap.get(cell.getRow()) == null) {
            columnMap.put(cell.getRow(), null);
        }
        return;

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSubLoanMap(LinkedHashMap<Integer, LinkedHashMap<Integer, String>> subLoanMap, LinkedHashMap<Integer, LinkedHashMap<String, String>> dataMigrationMap, HttpServletResponse response) throws IOException {
        StringBuilder insertSql = new StringBuilder();
        try {
            insertSql.append("INSERT INTO ");
            insertSql.append(dataMigrationMap.get(2).get("tableName"));
            insertSql.append(" (");
            int dataMigrationSize = dataMigrationMap.size();
            int i = 0;
            for (Map.Entry<Integer, LinkedHashMap<String, String>> dataMigrationEntry : dataMigrationMap.entrySet()) {
                String columnName = dataMigrationEntry.getValue().get("columnName");
                i++;
                if (i < dataMigrationSize) {
                    insertSql.append(columnName + ",");
                } else {
                    insertSql.append(columnName + ") VALUES (");
                }
            }

            LinkedHashMap<Integer, LinkedHashMap<Integer, String>> transposedMap = new LinkedHashMap<>();

            for (Integer column : subLoanMap.keySet()) {
                LinkedHashMap<Integer, String> columnData = subLoanMap.get(column);
                for (Integer row : columnData.keySet()) {
                    LinkedHashMap<Integer, String> rowData = transposedMap.getOrDefault(row, new LinkedHashMap<>());
                    String cellData = columnData.get(row);
                    rowData.put(column, cellData);
                    transposedMap.put(row, rowData);
                }
            }

            // 现在 transposedMap 包含了列转行后的数据
            // 可以遍历 transposedMap 进行进一步操作
            for (Integer row : transposedMap.keySet()) {
                LinkedHashMap<Integer, String> rowData = transposedMap.get(row);
                for (Integer column : rowData.keySet()) {

                    String dataType = dataMigrationMap.get(column).get("dataType");
                    if (dataType.equals("VARCHAR2")) {
                        rowData.put(column, stringFormat(rowData.get(column)));
                    } else if (dataType.equals("DATE")) {
                        rowData.put(column, StringDateFormat(DateUtils.StringToDate(rowData.get(column))));
                    } else if (dataType.equals("NUMBER")) {
                        BigDecimal numberColumn = StringUtils.isEmpty(rowData.get(column)) ? null : new BigDecimal(rowData.get(column));
                        rowData.put(column, numberFormat(numberColumn));
                    }

                    String cellData = rowData.get(column);
                    // 在这里进行遍历后的操作
                    System.out.println("Row: " + row + ", Column: " + column + ", Data: " + cellData);
                }
            }

            List<String> scriptList = new ArrayList<>();

            for (Map.Entry<Integer, LinkedHashMap<Integer, String>> transposedMapEntry : transposedMap.entrySet()) {

                StringBuilder scriptBuilder = new StringBuilder();

                scriptBuilder.append(insertSql);

                LinkedHashMap<Integer, String> transposedMapEntryValue = transposedMapEntry.getValue();
                int transposedMapSize = transposedMapEntryValue.size();
                int j = 0;
                for (Map.Entry<Integer, String> entry : transposedMapEntryValue.entrySet()) {
                    String value = entry.getValue();
                    j++;
                    if (j < transposedMapSize) {
                        scriptBuilder.append(value);
                    } else {
                        scriptBuilder.append(value.substring(0, value.length() - 1));
                    }
                }
                scriptBuilder.append(");");
                scriptBuilder.append("\n");
                String script = scriptBuilder.toString();

                scriptList.add(script);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("--Run successful !!!" + "\n\n");
            sb.append("--Total number of success records : " + scriptList.size() + "\n\n");
            for (String script : scriptList) {
                sb.append(script).append("\n");
            }

            String content = sb.toString(); // 获取字符串内容
            response.setContentType("text/plain; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=insert.txt");

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(content.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    private void validationCaseId(Cell CASE_ID_CELL, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap, HashSet<String> caseIdSet) {

        String CASE_ID = CASE_ID_CELL.getValue() == null ? null : CASE_ID_CELL.getValue().toString();
        String errorMessage = "";

        if (CASE_ID == null || "".equals(CASE_ID.trim())) {
            errorMessage = "The" + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else if (CASE_ID.trim().contains(" ")) {

            errorMessage = "There is a space in row " + row + "column " + column;
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else if (CASE_ID.length() > 12) {

            errorMessage = "The length of row " + row + "and column " + column + "cannot exceed 12";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        }
        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, CASE_ID);
        }
        return;
    }


    public void validationDate(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {

        String dateCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString();
        // System.out.print(dateCell + " , ");
        String errorMessage = "";

        if (!(dateCell == null || dateCell.trim().equals(""))) {
            if (dateCell.contains(" ")) {
                errorMessage = "There is a space in line " + row + ",column " + column;
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else if (!StringUtil.checkDateFormat(dateCell)) {

                errorMessage = "The time format of the " + row + " row and the  " + column + "  column is wrong";
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            }
        }
        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, dateCell);
        }
        return;
    }


    public void validationDateNotNull(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {

        String dateCell = (cell == null || cell.getValue() == null) ? null : cell.getValue().toString().trim();
        // System.out.print(dateCell + " , ");
        String errorMessage = "";

        if (StringUtils.isEmpty(dateCell)) {
            //  errorMessage = "第" + hang + "行第" + lie + "列为空";
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else {
            //  There is a space in the fourth row and the third column
            if (!(dateCell == null || dateCell.trim().equals(""))) {
                if (dateCell.contains(" ")) {

                    errorMessage = "There is a space in line " + row + ",column " + column;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                } else if (!StringUtil.checkDateFormat(dateCell)) {
                    //Incorrect time format at line 4, column 5
                    errorMessage = "Incorrect time format at line " + row + "column " + column;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                }
            }
        }

        if (StringUtils.isEmpty(errorMessage)) {
            hangTrueMap.put(column, dateCell);
        }
        return;
    }

    private void validationVARCHAR(Cell cell, int length, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell != null && cell.getValue() != null) {

            if (cell.getValue().toString().length() > length) {
                // errorMessage = "The length of the " + row + " row and the " + column + " column is greater than " + length;
                errorMessage = "The maximum word length exceeds " + length + " : \"" + cell.getValue().toString() + "\" in Row " + row + " Column " + column;

                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else {
                //  System.out.println(cell.getValue().toString() + " , ");
                hangTrueMap.put(column, cell.getValue().toString());
            }
        }

        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }

    private void validationVARCHARNotNull(Cell cell, int length, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell == null || cell.getValue() == null || "".equals(StringUtils.isEmpty(cell.getValue().toString()))) {
            errorMessage = "The " + row + "row and the " + column + " column are empty";
            logger.error(errorMessage);
            hangErrorMap.put(column, errorMessage);
        } else {
            if (cell.getValue() != null) {

                if (cell.getValue().toString().length() > length) {
                    errorMessage = "The length of the " + row + " row and the " + column + " column is greater than" + length;
                    logger.error(errorMessage);
                    hangErrorMap.put(column, errorMessage);
                } else {
                    //  System.out.println(cell.getValue().toString() + " , ");
                    hangTrueMap.put(column, cell.getValue().toString());
                }
            }
        }
        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }

    private void validationNUMBER(Cell cell, int row, String column, LinkedHashMap hangErrorMap, LinkedHashMap hangTrueMap) {
        String errorMessage = "";

        if (cell != null && cell.getValue() != null) {
            //获取单元格类型
            int type = cell.getType();

            if (CellValueType.IS_NUMERIC != type) {

                errorMessage = "Type error at line " + row + "column " + column + " numeric type required";
                logger.error(errorMessage);
                hangErrorMap.put(column, errorMessage);
            } else {
                BigDecimal LOAN_FACILITY_AMT = new BigDecimal(cell.getValue().toString());
                //  System.out.println(LOAN_FACILITY_AMT.toPlainString());
                hangTrueMap.put(column, LOAN_FACILITY_AMT.toPlainString());
            }
        }

        if (StringUtils.isEmpty(errorMessage) && hangTrueMap.get(column) == null) {
            hangTrueMap.put(column, null);
        }
        return;
    }


    private String stringFormat(String sub) {
        //    scriptBuilder.append("'" + subLoan.getApplStatusTerm() + "', ");
        if (sub == null) {
            return "null,";
        } else {

            if (sub.contains("'")) {
                sub = sub.replace("'", "''");
            }
            return "'" + sub + "',";
        }
    }

    public String StringDateFormat(Date sub) {
        if (sub == null) {
            return "null,";
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(sub);

            return ("to_date('" + formattedDate + "', 'YYYY-MM-DD'),");
        }
    }

    public String numberFormat(BigDecimal sub) {

        if (sub == null) {
            return "null,";
        } else {
            return sub.toString() + ",";
        }

    }


}
