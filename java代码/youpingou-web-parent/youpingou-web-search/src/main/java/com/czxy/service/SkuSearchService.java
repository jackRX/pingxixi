package com.czxy.service;

import com.czxy.dao.SkuRepository;
import com.czxy.pojo.vo.BaseResult;
import com.czxy.vo.ReturnSku;
import com.czxy.vo.SearchResult;
import com.czxy.vo.SearchSku;
import com.czxy.vo.SkuSearchRequest;
import org.apache.ibatis.annotations.One;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:03 2018-12-25
 */
@Service
@Transactional
public class SkuSearchService {

    @Autowired
    private SkuRepository skuRepository;



    public SearchResult search(SkuSearchRequest req){
        System.out.println(req);
        //1.准备自定义查询对象
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2.多条件查询BoolQuery
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //3.条件一：验证es中的cat3id和传过来的cat3id是否相等
        //问题：es如何比较Integer类型相等
        boolQueryBuilder.must(QueryBuilders.termQuery("catid",req.getCatid()));
        //4.条件四验证brand_id
        if(req.getBrand_id()!=null&&!"".equals(req.getBrand_id())){
            boolQueryBuilder.must(QueryBuilders.termQuery("brand_id",req.getBrand_id()));
        }
        //5.条件三 spec_list
        //5.1分割字符，转成Map集合
        HashMap<String,String> map=null;
        if (!"".equals(req.getSpec_list())&&req.getSpec_list()!=null){
            //1.不为空就常见map集合
            map=new HashMap<String, String>();
            //2.分割字符串
            String[] spec_list_array = req.getSpec_list().split(",");
            for (String s:spec_list_array) {
                //3.分割
                String[] values = s.split("=");
                if (values.length==1){
                    map.remove(values[0]);
                }else {
                    //4.放入map集合
                    map.put(values[0],values[1]);
                }

            }

        }
        //5.2添加条件
        if (map!=null){
            Set<String> keys = map.keySet();
            for (String key:keys) {
                String value = map.get(key);
                key="specs."+key+".keyword";
                System.out.println(key+"----------"+value);
                boolQueryBuilder.must(QueryBuilders.termQuery(key,value));
            }

        }
        //6.价格
        // 价格
        if(req.getMin_price()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(req.getMin_price()));
        }
        if(req.getMax_price()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(req.getMax_price()));
        }
        //7. 排序
      // 销量
        if("xl".equals(req.getSort_by())){
            queryBuilder.withSort(SortBuilders.fieldSort("seller_count").order(SortOrder.DESC));
        }
        // 评论
        else if("pl".equals(req.getSort_by())){
            queryBuilder.withSort(SortBuilders.fieldSort("comment_count").order(SortOrder.DESC));
        }
       // 上架时间
        else if("sj".equals(req.getSort_by())){
            queryBuilder.withSort(SortBuilders.fieldSort("on_sale_time").order(SortOrder.DESC));
        }
       // 价格
        else if("jg".equals(req.getSort_by())&&"desc".equals(req.getSort_way())){
            queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else if("jg".equals(req.getSort_by())&&"asc".equals(req.getSort_way())){
            queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }
       //8. 分页
        int pageNo = req.getPage() -1 ;//为什么要减1，因为elasticsearch的分页从0开始
        int size = req.getPer_page();
        queryBuilder.withPageable(PageRequest.of(pageNo,size));
        //9.queryBuiler关联boolQueryBuilder
        queryBuilder.withQuery(boolQueryBuilder);
        //10.执行查询
        Page<SearchSku> page = this.skuRepository.search(queryBuilder.build());
        //11.获取总数量
        long count = page.getTotalElements();
        //12.获取catid
        Integer catid = req.getCatid();
        //13.准备data
        //13.1准备List<ReturnSku>
        ArrayList<ReturnSku> list = new ArrayList<>();
        for (SearchSku s: page) {
            //准备ReturnSku需要的数据
            ReturnSku sku = new ReturnSku();
            sku.setId(s.getId());
            sku.setGoods_name(s.getSku_name());
            sku.setPrice(s.getPrice());
            sku.setMidlogo(s.getLogo());
            sku.setComment_count(s.getComment_count());
            list.add(sku);
        }

        //14. 准备SearchResult
        SearchResult result = new SearchResult(0, "成功", list, (int)count, catid);
        return result;
    }






}
