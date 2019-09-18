package com.czxy.controller;

import com.czxy.pojo.group.Cart;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 18:31 2019-01-02
 */
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity<BaseResult> addToCart(@RequestBody Cart cartReqeuest){
        cartService.addToCart(cartReqeuest,request);
        BaseResult baseResult = new BaseResult(0, "成功", null);
        return ResponseEntity.ok(baseResult);
    }

    @GetMapping
    public ResponseEntity<BaseResult> findAll(){

        List<Cart> list=cartService.findAll(request);
        BaseResult result = new BaseResult(0, "成功", list);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{skuid}")
    public ResponseEntity<BaseResult> updateCarts(@PathVariable("skuid") Integer skuId, @RequestBody Cart cartRequest){
      cartService.updateCarts(request,skuId,cartRequest);
        BaseResult result = new BaseResult(0, "成功");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{skuid}")
    public ResponseEntity<BaseResult> deleteCarts(@PathVariable("skuid") Integer skuid){
        cartService.deleteCarts(skuid,request);
        BaseResult baseResult = new BaseResult(0, "成功");
        return ResponseEntity.ok(baseResult);
    }
    /**
     * 需要的参数：
     * 1 userId/token
     * 2 cart数据
     * @return
     */
    @GetMapping("/addLoginCart")
    public ResponseEntity<BaseResult> addLoginCart(@RequestParam("userId") Integer userId,@RequestParam("carts") String carts){
        cartService.addLoginCart(userId,carts);
        //2. 返回结果
        BaseResult br = new BaseResult(0, "成功");
        return ResponseEntity.ok(br);
    }
}
