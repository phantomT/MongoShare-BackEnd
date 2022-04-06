package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

import java.util.List;

/**
 * @author 田宝宁
 * @date 2021/7/9
 */
public class MoveRequest extends ContextRequest {
    private String userid;
    private String pid;
    private List<String> idList;

    public MoveRequest() {
    }

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
