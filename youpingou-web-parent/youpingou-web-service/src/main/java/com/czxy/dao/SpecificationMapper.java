package com.czxy.dao;

import com.czxy.pojo.group.Specification;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 20:12 2018-12-20
 */
@org.apache.ibatis.annotations.Mapper
public interface SpecificationMapper extends Mapper<Specification> {

    @Select("select * from tb_specification where category_id = #{catId}")
    @Results({
             @Result(property = "id",column = "id"),
             @Result(property = "spec_name",column = "spec_name"),
             @Result(property = "spec_id",column = "spec_id"),
             @Result(property = "options",column = "id",many = @Many(select = "com.czxy.dao.SpecifitionOptionsMapper.findSpecifitionOptionBySpecId"))
    })
    public List<Specification> findSpecificationBycatId(@Param("catId") Integer catId);

    @Select("select * from tb_specification where id = #{specId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "spec_name",property = "spec_name"),
            @Result(column = "category_id",property = "category_id"),
            @Result(column = "id",property = "options",many = @Many(select = "com.czxy.dao.SpecifitionOptionsMapper.findSpecifitionOptionBySpecId"))
    })
    public Specification findSpecificationBySpecid(Integer specId);
}
