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
     * 用户名
     */
    private String userName;
    /**
     * 记录文件Id的列表
     */
    private List<String> idList;

    public DeleteRequest(String userName, List<String> idList) {
        this.userName = userName;
        this.idList = idList;
    }

    public String getUserName() {
        return userName;
    }

    public DeleteRequest setUserName(String userName) {
        this.userName = userName;
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
