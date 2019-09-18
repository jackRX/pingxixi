package com.czxy.controller;

import com.czxy.pojo.vo.ESData;
import com.czxy.pojo.vo.OneSkuResult;
import com.czxy.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/esData")
    public ResponseEntity<Object> findESData(){
        List<ESData> esData = skuService.findESData();
        return ResponseEntity.ok(esData);
    }

    @GetMapping("/goods/{skuid}")
    public ResponseEntity<Object> findSkuById(@PathVariable(value = "skuid")Integer skuId){
        OneSkuResult result = skuService.findSkuByskuId(skuId);
        return ResponseEntity.ok(result);
    }
}