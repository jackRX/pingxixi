package com.czxy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "skus01", type = "docs", shards = 5, replicas = 1)
public class SearchSku {
    @Id
    private Integer id; // skuId
    @Field(type = FieldType.keyword,index = false)//不分词不索引只存储
    private String logo;//图片地址
    @Field(type = FieldType.text,analyzer = "ik_max_word")// 分词索引存储
    private String sku_name;//sku名字

    @Field(type = FieldType.text,analyzer = "ik_max_word")
    private String all; //用于关键字搜索， 所有需要被搜索的信息，包含标题，分类，甚至品牌

//    @Field(type = FieldType.Date)
//    private Date on_sale_time;//上架时间--用于时间排序
    @Field(type = FieldType.Long)
    private Long on_sale_time;//上架时间--用于时间排序
    //品牌编号
    @Field(type = FieldType.Integer)
    private Integer brand_id;
    // 分类id
    @Field(type = FieldType.Integer)
    private Integer cat3_id;
    //规格列表 : {"机身颜色":"白色","内存":"3GB","机身存储":"16GB"}
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值

    @Field(type = FieldType.Double)
    private Double price;// 价格
    @Field(type = FieldType.text)
    private String spu_name;
    @Field(type = FieldType.Integer)
    // 库存量
    private Integer stock;
    @Field(type = FieldType.text)
    private String description;
    @Field(type = FieldType.text)
    private String packages;//规格与包装
    @Field(type = FieldType.text)
    private String aftersale;//售后保障
    //private String midlogo;
    //评价数
    @Field(type = FieldType.Integer)
    private Integer comment_count;
    // 销量
    @Field(type = FieldType.Integer)
    private Integer seller_count;

    private Integer catid;

}