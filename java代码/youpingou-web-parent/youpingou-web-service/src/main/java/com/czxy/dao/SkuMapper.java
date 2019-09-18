package com.czxy.dao;

import com.czxy.pojo.group.Sku;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SkuMapper extends Mapper<Sku> {

    @Select("select * from tb_sku")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="stock",property="stock"),
            @Result(column="sku_name",property="sku_name"),
            @Result(column="spec_list_code",property="spec_list_code"),
            @Result(column="spu_id",property="spu",
                    one=@One(
                            select="com.czxy.dao.SpuMapper.findSpuById"
                    )),
            @Result(column="spu_id",property="spu",
                    one=@One(
                            select="com.czxy.dao.SpuMapper.findSpuById"
                    ))
    })
    public List<Sku> findAllSkus();

    @Select("select * from tb_sku where spu_id = #{spuId}")
    public List<Sku> findSkuListBySpuId(Integer spuId);
}