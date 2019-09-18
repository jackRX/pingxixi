package com.czxy.vo;

import lombok.Data;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 14:59 2018-12-25
 */
@Data
public class SkuSearchRequest {
    private String keyword;// 关键字搜索，预留
    private Integer catid;// 3 级类目
    private Integer brand_id;// 品牌
    private String spec_list;// 规格选项列表
    private Double min_price;//最低价格
    private Double max_price;//最高价格
    private Integer limit;//限制条数
    private Integer page;//当前页
    private String sort_by;//排序字段
    private String sort_way;//排序方式 asc desc
    private Integer per_page;//每页条数

}
