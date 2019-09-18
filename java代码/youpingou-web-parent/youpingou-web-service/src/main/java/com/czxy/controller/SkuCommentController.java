package com.czxy.controller;
import com.czxy.pojo.vo.ComnentRequest;
import com.czxy.pojo.vo.SkuCommentResult;
import com.czxy.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class SkuCommentController {

    @Autowired
    private CommentService skuCommentService;

    /**
     * 查找评论的方法
     * @param spuid
     * @param comnentRequest
     * @return
     */
    @GetMapping("/comments/{spuid}")
    public ResponseEntity<Object> findCommentsByPage(@PathVariable("spuid") Integer spuid, ComnentRequest comnentRequest){
        SkuCommentResult comments = skuCommentService.findComments(spuid, comnentRequest);
        return ResponseEntity.ok(comments);
    }

}