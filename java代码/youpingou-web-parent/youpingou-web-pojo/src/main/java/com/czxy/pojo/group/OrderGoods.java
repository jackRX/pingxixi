package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_order_good")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderGoods implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="sn")
    private String sn;
    @Transient
    private Order order;
    @Column(name="spu_id")
    private Integer spu_id;
    @Transient
    private Spu spu;
    @Column(name="sku_id")
    private Long sku_id;
    @Transient
    private Sku sku;
    @Column(name="number")
    private Integer number;
    @Column(name="spec_list")
    private String spec_list;
    @Column(name="sku_name")
    private String sku_name;
    @Column(name="url")
    private String url;
    @Column(name="price")
    private Double price;
}