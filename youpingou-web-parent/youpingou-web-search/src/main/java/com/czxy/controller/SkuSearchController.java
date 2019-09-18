package com.czxy.controller;

import com.czxy.service.SkuSearchService;
import com.czxy.vo.SearchResult;
import com.czxy.vo.SkuSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:01 2018-12-25
 */
@RestController
@RequestMapping
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;

    @GetMapping("/goods")
    public ResponseEntity<Object> findSkus(SkuSearchRequest req){
        SearchResult search = skuSearchService.search(req);
        return ResponseEntity.ok(search);
    }



}
