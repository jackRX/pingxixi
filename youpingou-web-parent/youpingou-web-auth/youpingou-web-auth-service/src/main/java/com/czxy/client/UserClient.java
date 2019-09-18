package com.czxy.client;

import com.czxy.pojo.group.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther Created by yuanxinqi on 2018/12/17
 */
@FeignClient(value = "web-service",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("query")
    public ResponseEntity<User> findUserByMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password);

}