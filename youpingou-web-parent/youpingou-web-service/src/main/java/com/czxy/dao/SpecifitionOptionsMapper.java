package com.czxy.dao;

import com.czxy.pojo.group.Specification;
import com.czxy.pojo.group.SpecificationOption;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 20:14 2018-12-20
 */
@org.apache.ibatis.annotations.Mapper
public interface SpecifitionOptionsMapper extends Mapper<Specification> {

    @Select("select * from tb_specification_option where spec_id = #{specId}")
    public List<SpecificationOption> findSpecifitionOptionBySpecId(@Param("specId") Integer speacId);
}
