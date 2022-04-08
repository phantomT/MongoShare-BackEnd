package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

/**
 * 将重命名操作中的信息封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/03/23
 */
public class RenameRequest extends ContextRequest {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 文件id
     */
    private String id;
    /**
     * 新文件名
     */
    private String newName;

    public RenameRequest(String userName, String id, String newName) {
        this.userName = userName;
        this.id = id;
        this.newName = newName;
    }

    public String getUserName() {
        return userName;
    }

    public RenameRequest setUserName(String userName) {
        this.userName = userName;
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
