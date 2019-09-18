package com.czxy.pojo.vo;

import lombok.Data;

/**
 * @auther Created by yuanxinqi on 2018/12/17
 */
@Data
public class LoginResult extends BaseResult {

    private String token;
    private String name;

    public LoginResult(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public LoginResult(Integer errno, String errmsg, Object data, String token, String name) {
        super(errno, errmsg, data);
        this.token = token;
        this.name = name;
    }

    public LoginResult(Integer errno, String errmsg, String token, String name) {
        super(errno, errmsg);
        this.token = token;
        this.name = name;
    }
}