package com.czxy.service;

import com.czxy.dao.BrandMapper;
import com.czxy.pojo.group.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.BreakIterator;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 16:26 2018-12-20
 */
@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public List<Brand> findBrandByCatId(Integer catId){
        List<Brand> list=brandMapper.findBrandByCatId(catId);
        return list;
    }
}
