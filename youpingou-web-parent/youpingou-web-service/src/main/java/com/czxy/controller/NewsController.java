package com.czxy.controller;

import com.czxy.pojo.group.News;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 9:58 2018-12-20
 */

@RestController
@RequestMapping
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public BaseResult findAllNews(){
        List<News> all = newsService.findAll();
        BaseResult result = new BaseResult(0, "成功", all);
        return  result;
    }

}
