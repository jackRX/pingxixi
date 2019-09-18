package com.czxy;

import com.czxy.client.SkuCilent;
import com.czxy.dao.SkuRepository;
import com.czxy.vo.SearchSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 10:12 2018-12-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YoupingouWebSearchApplication.class)
public class SkuClientTest {

    @Autowired
    private SkuRepository skuRepository;
    
    @Autowired
    private SkuCilent skuCilent;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 获取web-service的数据
     */
    @Test
    public void Test(){
        ResponseEntity<List<SearchSku>> esData = skuCilent.findESData();
        List<SearchSku> list= esData.getBody();
        for (SearchSku s:list) {
            System.out.println(s);
        }
    }

    /**
     * 创建索引
     */
    @Test
    public void createIndex(){
     this.elasticsearchTemplate.createIndex(SearchSku.class);
     this.elasticsearchTemplate.putMapping(SearchSku.class);

    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex(){
        this.elasticsearchTemplate.deleteIndex(SearchSku.class);
    }

    /**
     * 把数据保存到es中
     */
    @Test
    public void add(){
        ResponseEntity<List<SearchSku>> esData = skuCilent.findESData();
        List<SearchSku> list= esData.getBody();
        for (SearchSku s:list) {
            System.out.println(s);
        }
      this.skuRepository.saveAll(list);
    }


}
