package com.kinetix.surveyengine.mapper;

import com.kinetix.surveyengine.entity.SubLoan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author zhangxin
 * @date 2023-06-16 11:27
 */
@Mapper
public interface SubLoanMapper {
    int insert(SubLoan record);

    int insertSelective(SubLoan record);

    void batchInsert(@Param("list")List<SubLoan> entities);

}