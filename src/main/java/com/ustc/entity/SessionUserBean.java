
package com.ustc.entity;

/**
 * 用户Bean
 * @author 田宝宁
 * @date 2022/03/07
 */
public class SessionUserBean {
    private String userName;

    public SessionUserBean(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}