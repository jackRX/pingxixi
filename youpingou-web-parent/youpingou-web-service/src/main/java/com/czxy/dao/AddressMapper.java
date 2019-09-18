package com.czxy.dao;

import com.czxy.pojo.group.Address;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface AddressMapper extends Mapper<Address> {

    @Select("select * from tb_address where user_id=#{userId}")
    public List<Address> findAddressByUserId(Integer userId);
}