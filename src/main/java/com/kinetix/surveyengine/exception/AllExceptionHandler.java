package com.kinetix.surveyengine.exception;


import com.kinetix.surveyengine.restful.RetResponse;
import com.kinetix.surveyengine.restful.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(AllExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = MyRuntimeException.class)
    public RetResult runtimeExceptionHandler(MyRuntimeException ex) {
        logger.error("捕获到业务MyRuntimeException异常 ===》 "+ex.getMsg()+" "+ex.getStackTrace()[0],ex);
        return RetResponse.say(ex.getCode(),ex.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RetResult javaExceptionHandler(Exception ex) {
        logger.error("捕获到Exception异常",ex);
        return RetResponse.say(500,"系统错误，请联系管理员！");
    }


}
