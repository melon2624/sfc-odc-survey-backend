package com.kinetix.surveyengine.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.kinetix.surveyengine.mapper.SurveyMapper;
import com.kinetix.surveyengine.entity.Survey;

import java.math.BigDecimal;

/**
 * @author zhangxin
 * @date 2023-06-12 16:17
 */

@Service
public class SurveyService {

    @Resource
    private SurveyMapper surveyMapper;


    public int insert(Survey record) {
        return surveyMapper.insert(record);
    }


    public int insertSelective(Survey record) {
        return surveyMapper.insertSelective(record);
    }

    public void insertSurvey(Survey survey) {

        int num= surveyMapper.countNum();
        survey.setVersion(new BigDecimal(num+1));
        surveyMapper.insert(survey);
    }
}


