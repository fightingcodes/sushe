package com.chen.exception;


import com.chen.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ServiceExciption.class)
    @ResponseBody//java对象转为json
    public Result handle(ServiceExciption se){
        return  Result.error(se.getCode(),se.getMessage());
    }
}
