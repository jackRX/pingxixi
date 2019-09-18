package com.czxy.pojo.group;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Cart implements Serializable {
    private Integer skuid;
    private String goods_name;
    private Double price;
    private Integer count;//购买数量
    private String checked;
    private String midlogo;
    private String spec_info;
}