package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_sku")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sku  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //库存量
    @Column(name="stock")
    private Integer stock;

    @Column(name="spu_id")
    private Integer spu_id;
    @Transient
    private Spu spu;
    //sku名字
    @Column(name="sku_name")
    private String sku_name;
    //规格列表码,格式：规格ID-选项ID|规格ID-选项ID（规格ID正序）
    @Column(name="spec_list_code")
    private String spec_list_code;
    //规格列表
    @Column(name="spec_list")
    private String spec_list;

    @Column(name="price")
    private Double price;

    @Column(name = "images")
    private String images;
}