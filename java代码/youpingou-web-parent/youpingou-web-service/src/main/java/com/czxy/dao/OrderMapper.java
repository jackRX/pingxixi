package com.czxy.dao;

import com.czxy.pojo.group.Order;
import com.czxy.pojo.group.OrderGoods;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 18:09 2018-12-24
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends Mapper<Order> {

    @Select("select count(*) from tb_order o,tb_order_good og where og.sku_id =#{skuid} and o.sn=og.sn and o.status > 3")
    public Integer findCountBySkuid(Integer skuid);

}
