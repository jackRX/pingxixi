package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 18:23 2018-12-20
 */
@Table(name = "tb_specification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "spec_name")
    private String spec_name;

    @Column(name = "spec_id")
    private String spec_id;

    @Column(name = "category_id")
    private String category_id;

    @Transient
    private List<SpecificationOption> options;
}
