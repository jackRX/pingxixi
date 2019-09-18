package com.czxy.client;

import com.czxy.pojo.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 21:07 2019-01-04
 */
@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/carts/addLoginCart")
    public ResponseEntity<BaseResult> addLoginCart(@RequestParam("userId") Integer userId,@RequestParam("carts") String carts);


}
