package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 16:19 2018-12-20
 */
@Table(name = "tb_brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand_name")
    private String brand_name;
    
    @Column(name = "logo")
    private String logo;

}
