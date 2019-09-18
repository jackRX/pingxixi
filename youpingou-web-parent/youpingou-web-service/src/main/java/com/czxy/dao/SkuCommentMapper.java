package com.czxy.dao;

import com.czxy.pojo.group.SkuComment;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SkuCommentMapper extends Mapper<SkuComment> {
    /**
     * 通过skuid查找sku数量
     * @param skuId
     * @return
     */
    @Select("select count(*) from tb_sku_comment where sku_id = #{skuId}")
    public Integer findNumBySkuId(Integer skuId);

    /**
     * 听过spuId查找评价的星级
     * @param spuId
     * @return
     */
    @Select("select avg(star) from tb_sku_comment where spu_id = #{spuId}")
    public Double findAvgStarlBySpuId(Integer spuId);

    /**
     * 通过评价
     * @param spuid
     * @param ratio
     * @return
     */
    @Select("select count(*) from tb_sku_comment where spu_id = #{spuid} and ratio = #{ratio}")
    public Integer findCommentCountByRatio(@Param("spuid")Integer spuid, @Param("ratio")Integer ratio);

    /**
     * 通过spuid查找评论的总数
     * @param spuid
     * @return
     */
    @Select("select count(*) from tb_sku_comment where spu_id = #{spuid}")
    public Integer findTotalCommentBySpuid(Integer spuid);

    /**
     * 通过spuid查找评价列表
     * @param spuid
     * @return
     */
    @Select("select * from tb_sku_comment where spu_id = #{spuid} order by created_at desc")
    @Results({
            @Result(property = "user",column="user_id",one = @One(select = "com.czxy.dao.UserMapper.selectByPrimaryKey"))
    })
    public List<SkuComment> findCommentsBySpuid(Integer spuid);
}