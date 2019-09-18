package com.czxy.service;

import com.czxy.client.UserClient;
import com.czxy.pojo.group.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @auther Created by yuanxinqi on 2018/12/17
 */
@Service
public class AuthService {

    @Autowired
    private UserClient userClient;


    public User findUserByMobile(User user){
        ResponseEntity<User> entity = this.userClient.findUserByMobile(user.getMobile(), user.getPassword());
        User body = entity.getBody();
        return body;
    }


}