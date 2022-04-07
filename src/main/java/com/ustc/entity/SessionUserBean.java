
package com.ustc.entity;

/**
 * 用户Bean
 * @author 田宝宁
 * @date 2022/03/07
 */
public class SessionUserBean {
    private String userId;

    public SessionUserBean(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}