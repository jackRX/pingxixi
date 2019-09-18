package com.czxy.service;

import com.czxy.dao.ImpressionMapper;
import com.czxy.dao.SkuCommentMapper;
import com.czxy.pojo.group.Impression;
import com.czxy.pojo.group.SkuComment;
import com.czxy.pojo.vo.ComnentRequest;
import com.czxy.pojo.vo.SkuCommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 11:44 2018-12-27
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private SkuCommentMapper skucommentMapper;

    @Autowired
    private ImpressionMapper impressionMapper;

    public SkuCommentResult findComments(Integer spuid, ComnentRequest comnentRequest){

        //1.创建返回对象
        SkuCommentResult result = new SkuCommentResult();

        //2.查找印象
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuid(spuid);
        result.setImpressions(impressionList);

        //4.评价总数
        Integer totalCommentBySpuid = skucommentMapper.findTotalCommentBySpuid(spuid);
        result.setComment_count(totalCommentBySpuid);

        //3.查找评价率
        Integer goodratio = skucommentMapper.findCommentCountByRatio(spuid, 0);
        Integer commonment = skucommentMapper.findCommentCountByRatio(spuid, 1);
        Integer badratio = skucommentMapper.findCommentCountByRatio(spuid, 2);
        //计算并赋值
        Integer totalCount = skucommentMapper.findCommentCountByRatio(spuid,0);
        Map<String,Object> ratio = new HashMap<>();
//        Integer goods = (goodratio%totalCount)==0? goodratio*100/totalCount :  goodratio*100/totalCount+1;
        Integer goods=(goodratio*100)/totalCommentBySpuid;
        ratio.put("goods",goods);
//        Integer common = (commonment%totalCount)==0? commonment*100/totalCount :  commonment*100/totalCount+1;
        Integer common=(commonment*100)/totalCommentBySpuid;
         ratio.put("common",common);
//        Integer bad = (badratio%totalCount)==0? badratio*100/totalCount :  badratio*100/totalCount+1;
        Integer bad=(badratio*100)/totalCommentBySpuid;
        ratio.put("bad",bad);

        result.setRatio(ratio);



        //5.通过spuid查找评论
        List<SkuComment> comments = skucommentMapper.findCommentsBySpuid(spuid);
        result.setComments(comments);

        //返回结果
        return  result;
    }


}
