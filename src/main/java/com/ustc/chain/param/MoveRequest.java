package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

import java.util.List;

/**
 * 将Move操作的信息封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/03/23
 */
public class MoveRequest extends ContextRequest {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 文件夹Id（要移动到的文件夹）
     */
    private String pid;
    /**
     * 记录要操作的文件Id的列表
     */
    private List<String> idList;

    public MoveRequest(String userName, String pid, List<String> idList) {
        this.userName = userName;
        this.idList = idList;
        this.pid = pid;
    }

    public String getUserName() {
        return userName;
    }

    public MoveRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<String> getIdList() {
        return idList;
    }

    public MoveRequest setIdList(List<String> idList) {
        this.idList = idList;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public MoveRequest setPid(String pid) {
        this.pid = pid;
        return this;
    }
}
