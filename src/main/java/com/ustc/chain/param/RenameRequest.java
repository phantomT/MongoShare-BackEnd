package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

/**
 * @author 田宝宁
 * @date 2021/7/9
 */
public class RenameRequest extends ContextRequest {
    /**
     * 用户id
     */
    private String userid;
    /**
     * 文件id
     */
    private String id;
    /**
     * 新文件名
     */
    private String newName;

    public RenameRequest() {
    }

    public RenameRequest(String userid, String id, String newName) {
        this.userid = userid;
        this.id = id;
        this.newName = newName;
    }

    public String getUserid() {
        return userid;
    }

    public RenameRequest setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getId() {
        return id;
    }

    public RenameRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getNewName() {
        return newName;
    }

    public RenameRequest setNewName(String newName) {
        this.newName = newName;
        return this;
    }
}
