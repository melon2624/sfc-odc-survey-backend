package com.kinetix.surveyengine.service;

import com.aspose.cells.*;
import com.kinetix.surveyengine.strategy.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangxin
 * @date 2023-03-29 11:40
 */
@Service
public class VerificationService {

    @Value("${upload.engFilePath}")
    private String engFilePath;

    @Value("${upload.chiFilePath}")
    private String chiFilePath;

    private Logger logger = LoggerFactory.getLogger(VerificationService.class);

    public Boolean chiCheck(MultipartFile file) {
        Context context = new Context();
        Boolean isChange = false;
        try {
            //String englishPath = "C:\\Users\\zhangxin\\Desktop\\SFO_FRR_Template_v10_chi_unlock.xlsm";
            // 加载 Excel 文件
            Workbook workbook = new Workbook(chiFilePath);
            Workbook workbook1 = new Workbook(file.getInputStream());

            for (int i = 0; i < 22; i++) {

                Worksheet worksheet = workbook.getWorksheets().get(i);
                Worksheet worksheet1 = workbook1.getWorksheets().get(i);

                int numm = i + 1;
                logger.info("第" + numm + "个sheet---------------" + "名字: " + worksheet1.getName());

                Cells cellCollection = worksheet.getCells();
                Cells cellCollection1 = worksheet1.getCells();

                // 获取最大数据行和列
                int row = cellCollection.getMaxDataRow();
                int column = cellCollection.getMaxDataColumn();
                //遍历行
                for (int j = 0; j <= row; j++) {
                    //遍历列
                    for (int c = 0; c <= column; c++) {
                        Cell cell = cellCollection.get(j, c);
                        Cell cell1 = cellCollection1.get(j, c);
                        Style cell1Style = cell.getStyle();

                        if (!cell1Style.isLocked()) {
                            continue;
                        } else {
                            // 非空处理
                            String value = (null == cell || cell.getValue() == null) ? "" : cell.getValue().toString();
                            String value1 = (null == cell1 || cell1.getValue() == null) ? "" : cell1.getValue().toString();

                            if (context.isSkip(i, j, c)) {
                                continue;
                            } else {
                                if (!value.equals(value1)) {
                                    int jjj = j + 1;
                                    int ccc = c + 1;
                                    //System.out.println(cell1Style.isLocked());
                                    logger.info("第" + jjj + "行的第" + ccc + "列-不一样,template的值是 " + value
                                            + ",上传的值是: " + value1 + "template的lock情况是" + cell1Style.isLocked());

                                    isChange = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isChange;
    }


    public Boolean engCheck(MultipartFile file) {
        Context context = new Context();

        Boolean isChange = false;
        try {
            // String englishPath = "C:\\Users\\zhangxin\\Desktop\\SFO_FRR_Template_v10_eng_unlock.xlsm";
            // 加载 Excel 文件
            Workbook workbook = new Workbook(engFilePath);

            Workbook workbook1 = new Workbook(file.getInputStream());

            for (int i = 0; i < 22; i++) {

                Worksheet worksheet = workbook.getWorksheets().get(i);
                Worksheet worksheet1 = workbook1.getWorksheets().get(i);

                int numm = i + 1;
                logger.info("第" + numm + "个sheet---" + "名字: " + worksheet1.getName());

                Cells cellCollection = worksheet.getCells();
                Cells cellCollection1 = worksheet1.getCells();

                // 获取最大数据行和列
                int row = cellCollection.getMaxDataRow();
                int column = cellCollection.getMaxDataColumn();
                //遍历行
                for (int j = 0; j <= row; j++) {
                    //遍历列
                    for (int c = 0; c <= column; c++) {
                        Cell cell = cellCollection.get(j, c);
                        Cell cell1 = cellCollection1.get(j, c);
                        Style cell1Style = cell.getStyle();

                        if (!cell1Style.isLocked()) {
                            continue;
                        } else {
                            // 非空处理
                            String value = (null == cell || cell.getValue() == null) ? "" : cell.getValue().toString();
                            String value1 = (null == cell1 || cell1.getValue() == null) ? "" : cell1.getValue().toString();

                            if (context.isSkip(i, j, c)) {
                                continue;
                            } else {
                                if (!value.equals(value1)) {
                                    int jjj = j + 1;
                                    int ccc = c + 1;
                                    //System.out.println(cell1Style.isLocked());
                                    logger.info("第" + jjj + "行的第" + ccc + "列-不一样,template的值是 " + value
                                            + ",上传的值是: " + value1 + "template的lock情况是" + cell1Style.isLocked());

                                    isChange = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isChange;
    }

}
