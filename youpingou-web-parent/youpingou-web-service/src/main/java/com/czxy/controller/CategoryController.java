package com.czxy.controller;

import com.czxy.pojo.group.Category;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:30 2018-12-19
 */
@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys1")
    public BaseResult findAllCategory1(){
        long begin = System.currentTimeMillis();
        List<Category> list1 = categoryService.findCategoryByParentId(0);
        for (Category c1:list1) {
            //根据一级分类查询二级分类
            List<Category> list2 = categoryService.findCategoryByParentId(c1.getParent_id());
            c1.setChildren(list2);
            for (Category c2:list2) {
                //根据二级分类查询三级分类
                List<Category> list3 = categoryService.findCategoryByParentId(c2.getId());
                c2.setChildren(list3);
            }
        }
        long end = System.currentTimeMillis();
        BaseResult baseResult = new BaseResult(0,"成功",list1);
        return baseResult;
    }

    @GetMapping("/categorys")
    public BaseResult findAllCategory(){
        List<Category> list = categoryService.findAllCategory();
        List<Category> list1 = findCategoryByParentId(0, list);
        for (Category c1:list1) {
            //根据一级分类查询二级分类
            List<Category> list2 = findCategoryByParentId(c1.getId(),list);
            c1.setChildren(list2);
            for (Category c2:list2) {
                //根据二级分类查询三级分类
                List<Category> list3 = findCategoryByParentId(c2.getId(),list);
                c2.setChildren(list3);
            }
        }
        BaseResult baseResult = new BaseResult(0,"成功",list1);
        return baseResult;
    }

    public List<Category> findCategoryByParentId(Integer parentId,List<Category> list){
        ArrayList<Category> list1 = new ArrayList<>();
        for (Category c:list) {
            if (c.getParent_id()!=null&&c.getParent_id().equals(parentId)){
                list1.add(c);
            }
        }
        return list1;
    }
}
