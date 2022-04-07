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
     * 用户Id
     */
    private String userid;
    /**
     * 文件夹Id（要移动到的文件夹）
     */
    private String pid;
    /**
     * 记录要操作的文件Id的列表
     */
    private List<String> idList;

    public MoveRequest(String userid, String pid, List<String> idList) {
        this.userid = userid;
        this.idList = idList;
        this.pid = pid;
    }

    public String getUserid() {
        return userid;
    }

    public MoveRequest setUserid(String userid) {
        this.userid = userid;
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
