package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "tb_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="user_id")
    private Integer user_id;
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
    @Column(name="isdefault")
    private Integer isdefault;

}