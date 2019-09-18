package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_sku_photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkuPhoto  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //外键
    @Column(name="sku_id")
    private Integer skuId;
    @Column(name="url")
    private String url;
}