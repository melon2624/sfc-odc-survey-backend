package com.kinetix.surveyengine.restful;

public class RetResult<T> {
    private int code;

    private String msg;

    private T data;

    public RetResult(){}

    public RetResult(RetCode retCodeEnum) {
        code = retCodeEnum.getCode();
        msg = retCodeEnum.getMsg();
    }

    public RetResult(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RetResult<T> setCode(RetCode retCode) {
        this.code = retCode.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public RetResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
