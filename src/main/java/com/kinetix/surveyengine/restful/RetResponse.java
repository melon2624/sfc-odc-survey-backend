package com.kinetix.surveyengine.restful;

public class RetResponse {
    private final static String SUCCESS = "success";

    public static <T> RetResult<T> OK() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> OK(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetResult<T> Err(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(message);
    }

    public static <T> RetResult<T> say(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> say(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
