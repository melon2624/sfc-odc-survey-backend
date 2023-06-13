package com.kinetix.surveyengine.exception;


import com.kinetix.surveyengine.restful.RetCode;

public class MyRuntimeException extends RuntimeException {

    private int code;
    private String msg;


    public MyRuntimeException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyRuntimeException(RetCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
