package com.czxy.pojo.vo;

import com.czxy.pojo.group.Category;
import com.czxy.pojo.group.Specification;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class OneSkuResult {

    private Integer skuid;
    private Integer spuid;
    private String goods_name;
    private Double price;
    private Long on_sale_date;
    private Integer comment_count;
    private Double comment_level;
    private Category cat1_info;
    private Category cat2_info;
    private Category cat3_info;
    private Map<String, String> logo;
    private List<Map> photos;
    private String description;
    private String aftersale;
    private Integer stock;
    private List<Specification> spec_list;
    // id_list:'规格ID:选项ID|规格ID:选项ID|...',
    // id_txt:'规格名称:选项名称|规格名称:选项名称|...'
    private Map<String, String> spec_info;
    private List<Map> sku_list;
}