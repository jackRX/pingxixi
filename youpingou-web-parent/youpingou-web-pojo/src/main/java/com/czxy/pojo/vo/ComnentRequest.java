package com.czxy.pojo.vo;

import lombok.Data;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 11:39 2018-12-28
 */
@Data
public class ComnentRequest {

    private Integer limit;

    private Integer per_page;

    private Integer page;

    private String sort_by;

    private String sort_way;

}
