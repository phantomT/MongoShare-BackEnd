package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/4/1
 */
public class DeleteRequest extends ContextRequest {
    private String userid;
    private List<String> idList;

    public DeleteRequest() {
    }

    public DeleteRequest(String userid, List<String> idList) {
        this.userid = userid;
        this.idList = idList;
    }

    public String getUserid() {
        return userid;
    }

    public DeleteRequest setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public List<String> getIdList() {
        return idList;
    }

    public DeleteRequest setIdList(List<String> idList) {
        this.idList = idList;
        return this;
    }
}
