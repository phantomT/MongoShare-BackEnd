package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

import java.util.List;

/**
 * 将删除操作的信息封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/04/01
 */
public class DeleteRequest extends ContextRequest {
    /**
     * 用户id
     */
    private String userid;
    /**
     * 记录文件Id的列表
     */
    private List<String> idList;

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
