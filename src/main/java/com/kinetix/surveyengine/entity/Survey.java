package com.kinetix.surveyengine.entity;

import java.math.BigDecimal;
import java.security.Timestamp;

import lombok.Data;


/**
 * @author zhangxin
 * @date 2023-06-12 18:05
 */


public class Survey {
    private BigDecimal version;

    private byte[] excel;

    private BigDecimal surveyType;

    private Timestamp createDate;

    public void setExpiryDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getExpiryDate() {
        return createDate;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public byte[] getExcel() {
        return excel;
    }

    public void setExcel(byte[] excel) {
        this.excel = excel;
    }

    public BigDecimal getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(BigDecimal surveyType) {
        this.surveyType = surveyType;
    }
}