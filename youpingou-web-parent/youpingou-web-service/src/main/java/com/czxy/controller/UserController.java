package com.czxy.controller;

import com.czxy.pojo.group.User;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/regist")
    public BaseResult regist(@RequestBody User user){
        String code = redisTemplate.opsForValue().get(user.getMobile());
        System.out.println(code);
        //判断验证码是否为空
        if (code!=null){
            //验证码输入正确
            if (user.getCode().equals(code)){
                User userByphone = userService.findUserByphone(user);
                if (userByphone!=null){
                    BaseResult br1 = new BaseResult(1, "注册失败");
                    return br1;
                }
                userService.saveUser(user);
                BaseResult br = new BaseResult(0, "注册成功");
                return br;
            }else {
                //验证码输入错误
                BaseResult br = new BaseResult(1, "验证码错误");
                return br;
            }
        }
        return null;
    }

    @GetMapping("query")
    public ResponseEntity<User> findUserByMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password){
        User user = userService.findUserByMobileAndPassword(mobile, password);
        return ResponseEntity.ok(user);
    }

}