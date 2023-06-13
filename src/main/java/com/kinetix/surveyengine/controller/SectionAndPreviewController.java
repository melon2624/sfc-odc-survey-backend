package com.kinetix.surveyengine.controller;

import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.kinetix.surveyengine.exception.MyRuntimeException;
import com.kinetix.surveyengine.restful.RetResponse;
import com.kinetix.surveyengine.restful.RetResult;
import com.kinetix.surveyengine.service.SectionAndPreviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author melo
 * @date 2023-05-17 10:44
 */
@RestController
public class SectionAndPreviewController {


    @Value("${excelPath:}")
    public String excelPath;

    private Logger logger = LoggerFactory.getLogger(SectionAndPreviewController.class);

    @Resource
    SectionAndPreviewService sectionAndPreviewService;

    @RequestMapping("/validation")
    public RetResult<JSONObject> validation(@RequestParam("fileName") MultipartFile file) {
        return sectionAndPreviewService.validation(file);
    }

    @RequestMapping("/getExcelFile")
    public void getFile(@RequestParam(value = "fileName", required = true) String fileName, HttpServletResponse resp) throws Exception {
        System.out.println(excelPath);

        if (excelPath==null||excelPath.equals("")){
            throw new MyRuntimeException(501,"excelPath路径不存在");
        }

      //  String excelPath = "C:/Users/zhangxin/Desktop/output.xlsm";
        String downExcelPath=  excelPath+fileName+".xlsm";
        File inputFile = new File(downExcelPath);
        if (!inputFile.exists()){
            throw new MyRuntimeException(501,"文件不存在");
        }
        Workbook workbook = new Workbook(inputFile.getAbsolutePath());
        downLoadExcel(fileName, resp, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + "." + "xlsm", "UTF-8"));
            //workbook.write(response.getOutputStream());
            // 保存工作簿
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.save(outputStream, SaveFormat.XLSM);
            // 将工作簿内容写入到响应流中
            byte[] bytes = outputStream.toByteArray();
            response.getOutputStream().write(bytes);
            // 关闭流
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
