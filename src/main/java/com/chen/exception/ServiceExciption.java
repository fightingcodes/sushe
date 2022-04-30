package com.chen.exception;

import lombok.Getter;

/**
 * 自定义异常
 * */
@Getter
public class ServiceExciption extends RuntimeException {
    private String code;
    public ServiceExciption(String code,String msg){
        super(msg);
        this.code=code;
    }
}
