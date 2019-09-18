package com.czxy.dao;

import com.czxy.vo.SearchSku;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 10:06 2018-12-25
 */
public interface SkuRepository extends ElasticsearchRepository<SearchSku,Integer> {


}
