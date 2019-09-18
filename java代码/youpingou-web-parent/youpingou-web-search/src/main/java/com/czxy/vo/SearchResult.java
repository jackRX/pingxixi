package com.czxy.vo;

import com.czxy.pojo.vo.BaseResult;
import lombok.Data;

@Data
public class SearchResult extends BaseResult {

    private Integer count;
    private Integer catid;

    public SearchResult(Integer count, Integer catid) {
        this.count = count;
        this.catid = catid;
    }

    public SearchResult(Integer errno, String errmsg, Object data, Integer count, Integer catid) {
        super(errno, errmsg, data);
        this.count = count;
        this.catid = catid;
    }

    public SearchResult(Integer errno, String errmsg, Integer count, Integer catid) {
        super(errno, errmsg);
        this.count = count;
        this.catid = catid;
    }
}