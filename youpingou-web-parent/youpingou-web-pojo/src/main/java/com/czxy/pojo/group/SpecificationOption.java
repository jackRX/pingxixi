package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 18:27 2018-12-20
 */
@Table(name = "tb_specification_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "option_name")
    private String option_name;

    @Column(name = "spec_id")
    private  String spec_id;

}
