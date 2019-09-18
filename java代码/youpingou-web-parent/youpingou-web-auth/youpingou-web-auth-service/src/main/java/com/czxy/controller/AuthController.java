package com.czxy.controller;

import com.czxy.auth.entity.UserInfo;
import com.czxy.auth.utils.JwtUtils;
import com.czxy.client.CartClient;
import com.czxy.config.JwtProperties;
import com.czxy.pojo.group.User;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.pojo.vo.LoginResult;
import com.czxy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther Created by haungfurong
 */
@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private HttpServletRequest req;
    @Autowired
    private CartClient cartClient;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user){
        System.out.println(user);
        // 1. 调用Service，查找用户信息
        User dbUser = authService.findUserByMobile(user);
        // 2. 如果找不到用户信息，直接报错
        //    dbUser.getId()==-100 触发了熔断
        if(dbUser==null||dbUser.getId()==-100){
            BaseResult br = new BaseResult(1, "未找到用户信息");
            return ResponseEntity.ok(br);
        }
        // 3. 如果找到了用户信息，生成jwt，
        //    第一个参数：userInfo
        //    第二个参数：私钥
        //    第三个参数：存活时间
        UserInfo userInfo = new UserInfo(dbUser.getId(), dbUser.getName());
        String token = null;
        try {
            token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), 30);
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult br = new BaseResult(1, "服务器异常");
            return ResponseEntity.ok(br);
        }
        if(user.getCart()!=null){
            cartClient.addLoginCart(userInfo.getId(),user.getCart());
        }
        // 4. 根据接口拼接返回结果，返回数据
        LoginResult lr = new LoginResult(0, "成功", token, dbUser.getName());

        return ResponseEntity.ok(lr);
    }
    /**
     * 验证用户信息
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser() {
        try {
            String token = req.getHeader("authorization");
            // 获取token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            // 成功后直接返回
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            // 抛出异常，证明token无效，直接返回401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
