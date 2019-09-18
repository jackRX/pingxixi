package com.czxy.pojo.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:09 2018-12-19
 */
@Table(name = "tb_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cat_name")
    private String cat_name;
    @Column(name = "parent_id")
    private Integer parent_id;
    @Transient
    private List<Category> children;

}
