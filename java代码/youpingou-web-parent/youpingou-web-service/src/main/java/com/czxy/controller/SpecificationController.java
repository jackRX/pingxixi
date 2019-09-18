package com.czxy.controller;

import com.czxy.pojo.group.Specification;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.SpecifitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 20:32 2018-12-20
 */
@RestController
@RequestMapping
public class SpecificationController {

    @Autowired
    private SpecifitionService specifitionService;

    @GetMapping("/specifications/{catid}")
    public BaseResult findSpecificationBycatId(@PathVariable("catid") Integer catId){
        List<Specification> list = specifitionService.findSpecificationBycatId(catId);
        BaseResult result = new BaseResult(0, "成功", list);
        return result;
    }

}
