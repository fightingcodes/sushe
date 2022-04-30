package com.chen.controller.dto;

import lombok.Data;


/**
 * 接受前端登录请求
 * */
@Data
public class UserDto {
    private  String name;
    private  String password;
    private  String nickname;
    private  String avatarurl;
}
