package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by XINQI.YUAN on 2018/6/11.
 */
@Table(name = "tb_spu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Spu  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="created_at")
    private String created_at;
    @Column(name="updated_at")
    private String updated_at;
    //spu名字
    @Column(name="spu_name")
    private String spu_name;
    //spu副名称
    @Column(name="spu_subname")
    private String spu_subname;
    //商品logo
    @Column(name="logo")
    private String logo;
    //分类1Id
    @Column(name="cat1_id")
    private Integer cat1_id;
    //分类2ID
    @Column(name="cat2_id")
    private Integer cat2_id;
    //分类3Id
    @Column(name="cat3_id")
    private Integer cat3_id;
    @Column(name="brand_id")
    private Integer brand_id;
    @Transient
    private Brand brand;
    //审核时间
    @Column(name="check_time")
    private String check_time;
    //审核状态 审核状态，0：未审核，1：已通过，2：未通过
    @Column(name="check_status")
    private String check_status;
    //价格
    @Column(name="price")
    private String price;
    //是否上架
    @Column(name="is_on_sale")
    private Integer is_on_sale;
    //上架时间
    @Column(name="on_sale_time")
    private Long on_sale_time;
    //删除时间
    @Column(name="deleted_at")
    private String deleted_at;
    @Column(name="weight")
    private String weight;
    //商品描述
    @Column(name="description")
    private String description;
    //规格与包装
    @Column(name="packages")
    private String packages;
    //售后保障
    @Column(name="aftersale")
    private String aftersale;
    //规格列表，json串
    @Column(name="spec_list")
    private String spec_list;
}