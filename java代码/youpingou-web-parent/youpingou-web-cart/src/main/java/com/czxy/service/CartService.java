package com.czxy.service;
import com.alibaba.fastjson.JSON;
import com.czxy.auth.entity.UserInfo;
import com.czxy.auth.utils.JwtUtils;
import com.czxy.config.JwtProperties;
import com.czxy.pojo.group.Cart;
import com.czxy.pojo.vo.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 18:31 2019-01-02
 */
@Service
@Transactional
public class CartService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    /**
     * 添加商品到购物车
     * @param cartReqeuest
     * @param request
     */
    public void addToCart(Cart cartReqeuest, HttpServletRequest request) {
        //4. 获取token中的用户信息
        String userId = getUserIdByToken(request).toString();
        //5. 取出redis中的当前用户的购物车信息
        String cartListStr = redisTemplate.opsForValue().get(userId);

        if (cartListStr != null && StringUtils.isNotBlank(cartListStr)) {
            List<Cart> carts = JSON.parseArray(cartListStr, Cart.class);
            Cart cart=null;
            for (Cart cartinfo : carts) {
                //7. 如果取出，则判断当前sku是否已经存在，
                if (cartReqeuest.getSkuid().equals(cartinfo.getSkuid())){
                   cart=cartinfo;
                   break;
                }
            }
            if (cart!=null){
                //8. 如果存在，更新数量
                cart.setCount(cart.getCount()+cartReqeuest.getCount());
            }else {
                //9. 如果不存在，追加
                carts.add(cartReqeuest);
            }
            redisTemplate.opsForValue().set(userId,JSON.toJSONString(carts));
        }else {
            //6. 如果没有取出，直接保存
            ArrayList<Cart> carts = new ArrayList<>();
            carts.add(cartReqeuest);
            redisTemplate.opsForValue().set(userId,JSON.toJSONString(carts));
        }
    }

    /**
     * 通过userId查询所有的购物车项
     * @param request
     * @return
     */
    public List<Cart> findAll(HttpServletRequest request) {
        //解析token，获取userId
        String userId = getUserIdByToken(request).toString();
        //根据userId去Redis中查找数据
        String redisCarts = redisTemplate.opsForValue().get(userId);
        //判断redisCarts是否存在
        List<Cart> list=null;
        if(redisCarts!=null&&StringUtils.isNotBlank(redisCarts)){
            list = JSON.parseArray(redisCarts, Cart.class);
        }
        return  list;

    }


    /**
     * 修改购物车数据
     * @param request
     * @param skuId
     * @param cartRequest
     */
    public void updateCarts(HttpServletRequest request,Integer skuId, Cart cartRequest) {
        //1. 解析token，获取userId
        String userId = getUserIdByToken(request).toString();
        //2. 从购物车中获取商品
        String info = redisTemplate.opsForValue().get(userId);
        //3. 解析info
        List<Cart> list = JSON.parseArray(info, Cart.class);
        //4. 遍历info，找到对应的商品信息
        Cart updateCart=null;
        for (Cart cart:list) {
            if (skuId.equals(cart.getSkuid())){
                updateCart=cart;
                break;
            }
        }

        //5. 修改数量和是否选中
        updateCart.setCount(cartRequest.getCount());
        updateCart.setChecked(cartRequest.getChecked());
        //6. 将数据放入redis
        redisTemplate.opsForValue().set(userId,JSON.toJSONString(list));
    }

    public void addLoginCart(Integer userId, String carts) {
        //1. 将页面的购物车数据转成对象
        List<Cart> cartsRequestList = JSON.parseArray(carts, Cart.class);
        //2. 取出redis中的购物车数据
        String redisInfo = redisTemplate.opsForValue().get(userId.toString());
        //3. 判断redis购物车的数据是否存在
        if (redisInfo == null) {
            //4. redis购物车数据不存,直接将数据丢进redis中
            redisTemplate.opsForValue().set(userId.toString(), carts);
        } else {
            //5. redis中的购物车数据存在，判断是追加还是修改数量？
            //6. 将redisInfo转成对象
            List<Cart> redisInfoList = JSON.parseArray(redisInfo, Cart.class);
            //7. 外层遍历cartsRequestList
            for (Cart c1 : cartsRequestList) {
                //8. 内存遍历redisInfoList
                // 定义一个对象
                Cart cart = null;
                for (Cart c2 : redisInfoList) {
                    if (c1.getSkuid().equals(c2.getSkuid())) {
                        cart = c2;
                    }
                }
                //9 判断cart是否存在
                if (cart != null) {
                    //10. 存在，则修改c2数量
                    cart.setCount(cart.getCount() + c1.getCount());
                } else {
                    //11. 不存在，则追加c1
                    redisInfoList.add(c1);
                }
            }
            //12. 更新redis缓存
            redisTemplate.opsForValue().set(userId.toString(), JSON.toJSONString(redisInfoList));
        }
    }
    /**
     * 解析token中的数据
     * @param request
     * @return
     */
    public Integer getUserIdByToken(HttpServletRequest request){
        //1. 获取token
        String token = request.getHeader("authorization");
        //2. 解析token : 公钥解密
        // 默认解析成功
        boolean flag = true;
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        // 解析失败，就无需继续执行下去
        if(!flag){
            return -1;
        }

        //3. 获取当前用户的信息
        Integer userId = userInfo.getId();
        return userId;
    }

    public void deleteCarts(Integer skuid, HttpServletRequest request) {
        //通过token获取userId
        String userId= getUserIdByToken(request).toString();
        //获取userId的里的购物车数据
        String redisCartsInfo = redisTemplate.opsForValue().get(userId);
        //判断是否为空
            List<Cart> list = JSON.parseArray(redisCartsInfo, Cart.class);
            Cart cart=null;
            for (Cart c:list) {
                if (skuid.equals(c.getSkuid())){
                   cart=c;
                   break;
                }
            }
            //从集合中移除
            list.remove(cart);
            //将新列表存入Redis中
            redisTemplate.opsForValue().set(userId,JSON.toJSONString(list));


    }
}
