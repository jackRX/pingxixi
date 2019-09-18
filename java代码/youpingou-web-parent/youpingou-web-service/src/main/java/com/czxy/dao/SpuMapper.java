package com.czxy.dao;

import com.czxy.pojo.group.Spu;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SpuMapper extends Mapper<Spu> {
    @Select("select * from tb_spu where id = #{spuId}")
    @Results({
            @Result(column="brand_id",property="brand",
                    one=@One(
                            select="com.czxy.dao.BrandMapper.selectByPrimaryKey"
                    ))
    })
    public Spu findSpuById(Integer spuId);
}