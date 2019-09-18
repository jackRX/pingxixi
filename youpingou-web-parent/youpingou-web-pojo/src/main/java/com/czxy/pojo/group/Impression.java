package com.czxy.pojo.group;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 11:36 2018-12-27
 */
@Data
@Table(name = "tb_impression")
public class Impression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "count")
    private Integer count;

    @Column(name = "sku_id")
    private Integer sku_id;

    @Column(name = "spu_id")
    private Integer spu_id;


}
