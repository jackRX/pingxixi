package com.czxy.vo;

import lombok.Data;

@Data
public class ReturnSku {
    private Integer id;
    private String goods_name;
    private Double price;
    private String midlogo;
    private Integer comment_count;
}