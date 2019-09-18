package com.czxy.dao;

import com.czxy.pojo.group.SkuPhoto;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:32 2018-12-27
 */
@org.apache.ibatis.annotations.Mapper
public interface SkuPhotoMapper extends Mapper<SkuPhoto> {

    @Select("select * from tb_sku_photo where sku_id =#{skuId}")
    public List<SkuPhoto> findSkuPhotoByskuId(Integer skuId);

}
