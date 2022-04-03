package com.ustc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author 田宝宁
 */
@Document(collection = "User")
public class UserDO {
    @Id
    private ObjectId id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户信息
     */
    private Profile profile;

    public ObjectId getId() {
        return id;
    }

    public UserDO setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public UserDO setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public static class Profile {
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 性别
         */
        private String gender;

        public String getNickname() {
            return nickname;
        }

        public Profile setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public Profile setGender(String gender) {
            this.gender = gender;
            return this;
        }
    }
}
