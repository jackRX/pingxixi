package com.czxy.service;

import com.czxy.dao.SpecificationMapper;
import com.czxy.pojo.group.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 20:29 2018-12-20
 */
@Service
@Transactional
public class SpecifitionService {
    @Autowired
    private SpecificationMapper specificationMapper;

    public List<Specification> findSpecificationBycatId(Integer catId){
        List<Specification> list= specificationMapper.findSpecificationBycatId(catId);
        return list;
    }
}
