package com.czxy.controller;

import com.czxy.pojo.group.Brand;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 16:30 2018-12-20
 */
@RestController
@RequestMapping
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands/{catid}")
    public BaseResult findBrandByCatId(@PathVariable("catid") Integer catid){
        List<Brand> list = brandService.findBrandByCatId(catid);
        BaseResult result = new BaseResult(0, "成功", list);
        return result;
    }
}
