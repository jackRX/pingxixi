package com.czxy.dao;

import com.czxy.pojo.group.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 16:25 2018-12-20
 */
@org.apache.ibatis.annotations.Mapper
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 根据分类id查找品牌
     * @param catId 分类id
     * @return 查询到的品牌
     */
    @Select("select * from tb_brand where id in (select brand_id from tb_category_brand where category_id = #{catId})")
    List<Brand> findBrandByCatId(@Param("catId") Integer catId);
}
