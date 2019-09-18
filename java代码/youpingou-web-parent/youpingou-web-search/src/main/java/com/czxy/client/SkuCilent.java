package com.czxy.client;

import com.czxy.vo.SearchSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 9:59 2018-12-25
 */
@FeignClient(value = "web-service")
@RequestMapping
public interface SkuCilent {

    @GetMapping("/esData")
    public ResponseEntity<List<SearchSku>> findESData();



}
