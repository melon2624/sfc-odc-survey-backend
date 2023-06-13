package com.kinetix.surveyengine.controller;


import com.kinetix.surveyengine.entity.Survey;
import com.kinetix.surveyengine.mapper.SurveyMapper;
import com.kinetix.surveyengine.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author melo
 * @date 2023-03-22 9:25
 */
@RestController
public class VerificationController {

    private Logger logger = LoggerFactory.getLogger(VerificationController.class);

    @Resource
    VerificationService verificationService;

    @RequestMapping("/upload")
    public Boolean upload1(@RequestParam("fileName") MultipartFile file) {

        Boolean chiCheck = verificationService.chiCheck(file);
        logger.info("-----------------------------------------------");
        Boolean engCheck = verificationService.engCheck(file);

        return chiCheck && engCheck;
    }

}
