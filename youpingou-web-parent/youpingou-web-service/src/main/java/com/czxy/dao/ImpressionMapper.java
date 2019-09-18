package com.czxy.dao;
import com.czxy.pojo.group.Impression;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
@org.apache.ibatis.annotations.Mapper
public interface ImpressionMapper extends Mapper<Impression> {

    @Select("select * from tb_impression where spu_id = #{spuid}")
    public List<Impression> findImpressionsBySpuid(Integer spuid);
}