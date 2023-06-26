package com.kinetix.surveyengine.controller;

import com.alibaba.fastjson.JSONObject;
import com.kinetix.surveyengine.restful.RetResult;
import com.kinetix.surveyengine.service.SfcSubLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author melo
 * @date 2023
 * 2023-06-08 11:47
 */
@RestController
@RequestMapping("/subloan")
public class SFCSubLoanController {

    private Logger logger = LoggerFactory.getLogger(SectionAndPreviewController.class);

    @Resource
    SfcSubLoanService sfcSubLoanService;

    @RequestMapping("/validation")
    public RetResult<JSONObject> validation(@RequestParam("fileName") MultipartFile file, HttpServletResponse response) throws IOException {
        RetResult<JSONObject> validationResult = null;
        try {
            validationResult = sfcSubLoanService.validation(file,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validationResult;
    }

}
