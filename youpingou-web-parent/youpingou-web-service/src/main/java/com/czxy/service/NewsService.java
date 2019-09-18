package com.czxy.service;

import com.czxy.dao.NewsMapper;
import com.czxy.pojo.group.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 9:57 2018-12-20
 */
@Service
@Transactional
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;


    public List<News> findAll(){
        List<News> news = newsMapper.selectAll();
        return news;
    }
}
