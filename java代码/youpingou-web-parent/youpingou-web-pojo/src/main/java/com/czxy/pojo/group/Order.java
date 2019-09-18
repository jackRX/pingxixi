package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="created_at")
    private Timestamp created_at;
    @Column(name="updated_at")
    private Timestamp updated_at;
    @Column(name="sn")
    private String sn;
    @Column(name="shr_name")
    private String shr_name;
    @Column(name="shr_mobile")
    private String shr_mobile;
    @Column(name="shr_province")
    private String shr_province;
    @Column(name="shr_city")
    private String shr_city;
    @Column(name="shr_area")
    private String shr_area;
    @Column(name="shr_address")
    private String shr_address;
    @Column(name="status")
    private Integer status;
    @Column(name="pay_time")
    private String pay_time;
    @Column(name="post_time")
    private String post_time;
    @Column(name="user_id")
    private String user_id;
    @Transient
    private User user;
    @Column(name="total_price")
    private Double total_price;
}