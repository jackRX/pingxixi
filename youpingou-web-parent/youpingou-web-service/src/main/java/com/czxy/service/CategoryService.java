package com.czxy.service;

import com.czxy.dao.CategoryMapper;
import com.czxy.pojo.group.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:15 2018-12-19
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findCategoryByParentId(Integer parentId){
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId", parentId);
        return categoryMapper.selectByExample(example);
    }

    public List<Category> findAllCategory(){
        return categoryMapper.selectAll();
    }

}
