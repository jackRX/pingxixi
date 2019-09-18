package com.czxy.pojo.vo;

import com.czxy.pojo.group.Impression;
import com.czxy.pojo.group.SkuComment;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 11:45 2018-12-27
 */
@Data
public class SkuCommentResult{

    private List<Impression> impressions;

    private Map ratio;

    private Integer comment_count;

    private List<SkuComment> comments;

}
