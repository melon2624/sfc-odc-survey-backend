package com.kinetix.surveyengine.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.*;
import com.kinetix.surveyengine.entity.Survey;
import com.kinetix.surveyengine.mapper.SurveyMapper;
import com.kinetix.surveyengine.restful.RetResponse;
import com.kinetix.surveyengine.restful.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author melo
 * @date 2023-05-17 11:45
 */
@Service
public class SectionAndPreviewService {


    @Value("${excelPath}")
    private  String savePath;

    private Logger logger = LoggerFactory.getLogger(SectionAndPreviewService.class);

    @Resource
    SurveyService  surveyService;

    @Resource
    SurveyMapper surveyMapper;



    public RetResult<JSONObject> validation(MultipartFile file) {
        int errorNum = 1;
        Map<String, String> surveyMap = new LinkedHashMap<>();
        Map<String, String> errorMap = new LinkedHashMap<>();
        Boolean isHaveError = false;
        try {
            Workbook workbook = new Workbook(file.getInputStream());

            Worksheet worksheet = workbook.getWorksheets().get("Sheet1");

            Cells cellCollection = worksheet.getCells();
            int row = cellCollection.getMaxDataRow();

            for (int i = 1; i <= row; i++) {
                int hang = i + 1;
                Cell cellSheetName = cellCollection.get(i, 2);
                String sheetName = cellSheetName.getValue() == null ? null : cellSheetName.getValue().toString();
                if (sheetName == null || "".equals(sheetName.trim())) {
                    logger.info("第" + hang + "行的sheetName为空");
                    errorMap.put("error" + errorNum, "第" + hang + "行的sheetName为空");
                    errorNum++;
                    isHaveError = true;
                } else {
                    Worksheet worksheet1 = workbook.getWorksheets().get(sheetName);
                    if (worksheet1 == null) {
                        logger.info(sheetName + "的sheet不存在");
                        errorMap.put("error" + errorNum, sheetName + "的sheet不存在");
                        errorNum++;
                        isHaveError = true;
                    } else {
                        Cells cellCollection1 = worksheet1.getCells();
                        Cell cellReference = cellCollection.get(i, 3);
                        String reference = cellReference.getValue() == null ? null : cellReference.getValue().toString();

                        if (reference == null || "".equals(reference.trim())) {
                            logger.info("第" + hang + "行的cellReference为空");
                            errorMap.put("error" + errorNum, "第" + hang + "行的cellReference为空");
                            errorNum++;
                            isHaveError = true;
                        } else if (reference.length() <= 1) {
                            logger.info("第" + hang + "行的cellReference格式错误");
                            errorMap.put("error" + errorNum, "第" + hang + "行的cellReference格式错误");
                            errorNum++;
                            isHaveError = true;
                        } else {

                            String cellColumnString = reference.substring(0, 1);
                            String cellRowString = reference.substring(1);

                            Integer cellColumn = (cellColumnString.charAt(0) - 'A');
                            Integer cellRow = Integer.valueOf(cellRowString) - 1;

                            Cell valueCell = cellCollection1.get(cellRow, cellColumn);
                            Cell fieldNameCell = cellCollection.get(i, 0);
                            String cellValue = valueCell.getValue() == null ? "" : valueCell.getValue().toString();
                            String cellFieldName = fieldNameCell.getValue() == null ? "" : fieldNameCell.getValue().toString();

                            if (cellValue.trim().equals("") && cellCollection.get(i, 4).getValue().toString().equals("Yes")) {
                                logger.info("sheet:" + sheetName + "中第" + (cellRow + 1) + "行" + "第" + (cellColumn + 1) + "列" + "内容为空");
                                errorMap.put("error" + errorNum, "sheet:" + sheetName + "中第" + (cellRow + 1) + "行" + "第" + (cellColumn + 1) + "列" + "内容为空");
                                errorNum++;
                                isHaveError = true;
                            }

                            if (cellFieldName.trim().equals("")) {
                                logger.info("Mapping table 中 第" + hang + "行的FieldName为空");
                                errorMap.put("error" + errorNum, "Mapping table 中 第" + hang + "行的FieldName为空");
                                errorNum++;
                                isHaveError = true;
                            }
                            surveyMap.put(cellFieldName, cellValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject;
        if (isHaveError) {
            jsonObject = JSONObject.parseObject(JSON.toJSONString(errorMap));
            return RetResponse.say(500,"error",jsonObject);
        } else {
            addExtractionResult(surveyMap, file);
            jsonObject = JSONObject.parseObject(JSON.toJSONString(surveyMap));
        }
        return RetResponse.say(200,"success",jsonObject);
    }


    public MultipartFile addExtractionResult(Map<String, String> surveyMap, MultipartFile file) {
        try {
            Workbook workbook = new Workbook(file.getInputStream());
            // 添加一个新的工作表
            WorksheetCollection worksheets = workbook.getWorksheets();
            int index = worksheets.add();
            Worksheet newSheet = worksheets.get(index);
            // 设置新工作表的名称
            newSheet.setName("template_version");
            newSheet.setVisible(false);

            String fileName = "CybersecuritySection9-" + System.currentTimeMillis();

            JSONObject    jsonObject = JSONObject.parseObject(JSON.toJSONString(surveyMap));
            Survey survey = new Survey();
            survey.setExcel(jsonObject.toString().getBytes());
            surveyService.insertSurvey(survey);

            Cells cellCollection1 = newSheet.getCells();

            Cell valueCell = cellCollection1.get(0, 0);

            Integer version=surveyMapper.countNum();
            valueCell.setValue(version);

            logger.info(jsonObject.toJSONString());
            System.out.println(surveyMap);
            surveyMap.clear();
            surveyMap.put("fileName", fileName);

            // 保存修改后的 Excel 文件
            workbook.save(savePath + fileName + ".xlsm");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
