package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "tb_sku_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkuComment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="created_at")
    private Timestamp created_at;
    @Column(name="updated_at")
    private  Timestamp updated_at;
    @Column(name="user_id")
    private Integer user_id;
    @Transient
    private User user;
    @Column(name="spu_id")
    private Integer spu_id;
    @Transient
    private Spu spu;
    @Column(name="content")
    private String content;
    @Column(name="star")
    private Integer star;
    @Column(name="isshow")
    private String isshow;
}