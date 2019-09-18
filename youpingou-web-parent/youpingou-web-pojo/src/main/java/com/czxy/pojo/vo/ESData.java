package com.czxy.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESData {
    private Integer id; // skuId
    private String logo;//图片地址
    private String sku_name;//sku名字
    private String all; // 所有需要被搜索的信息，包含标题，分类，甚至品牌
    private Long on_sale_time;//上架时间
    //品牌编号
    private Integer brand_id;
    //分类id
    private Integer catid;
    //规格列表
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值
    private Double price;// 价格
    private String spu_name;
    private Integer stock;
    private String description;
    private String packages;//规格与包装
    private String aftersale;//售后保障
    // private String midlogo;
    //评价数
    private Integer comment_count;
    // 销量
    private Integer seller_count;
}