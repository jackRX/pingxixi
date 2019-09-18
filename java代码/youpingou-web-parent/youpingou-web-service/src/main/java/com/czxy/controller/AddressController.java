package com.czxy.controller;

import com.czxy.auth.entity.UserInfo;
import com.czxy.auth.utils.JwtUtils;
import com.czxy.config.JwtProperties;
import com.czxy.pojo.group.Address;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:35 2019-01-05
 */
@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/address")
    public ResponseEntity<BaseResult> findAddressByUserId(){
        String token = request.getHeader("authorization");
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer userId = userInfo.getId();
        //2.调用service
        List<Address> address = addressService.findAddressByUserId(userId);
        //3.组装返回的地址
        BaseResult result = new BaseResult(0, "成功", address);
        return ResponseEntity.ok(result);
    }




}
