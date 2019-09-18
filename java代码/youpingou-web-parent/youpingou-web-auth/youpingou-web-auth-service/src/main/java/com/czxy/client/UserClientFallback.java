package com.czxy.client;

import com.czxy.pojo.group.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @auther Created by yuanxinqi on 2018/12/17
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public ResponseEntity<User> findUserByMobile(String mobile, String password) {
        User user = new User();
        user.setId(-100);

        return ResponseEntity.ok(user);
    }
}