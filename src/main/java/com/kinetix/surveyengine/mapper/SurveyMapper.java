package com.kinetix.surveyengine.mapper;

import com.kinetix.surveyengine.entity.Survey;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author zhangxin
 * @date 2023-06-12 18:05
 */
@Mapper
public interface SurveyMapper {
    int insert(Survey record);

    int insertSelective(Survey record);

    int countNum();
}