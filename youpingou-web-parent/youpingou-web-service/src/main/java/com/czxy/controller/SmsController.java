package com.czxy.controller;

import com.czxy.pojo.group.User;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.util.GetRandomCodeUtil;
import com.czxy.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class SmsController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/sms")
    public BaseResult sendSms(@RequestBody User user){

        String mobile = user.getMobile();
        //生成随机验证码
        String code = GetRandomCodeUtil.randomCode();

        try {
            SmsUtil.sendSms(mobile,code);
            System.out.println(code);
            //把验证码保存到redis中
            redisTemplate.opsForValue().set(mobile,code,30,TimeUnit.MINUTES);
            BaseResult br = new BaseResult(0, "发送成功");
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult br = new BaseResult(1, "发送失败");
            return br;
        }

    }

}