package com.ustc.login.service.impl;

import com.ustc.entity.UserDO;
import com.ustc.login.dao.UserRepository;
import com.ustc.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据用户id查询用户
     * @param id  用户id
     * @return User
     */
    @Override
    public UserDO findOne(String id){
        Query id1 = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(id1, UserDO.class);
    }

    /**
     * 保存用户
     * @param admin 用户
     */
    @Override
    public void saveAdmin(UserDO admin){
        // 可以设置一些默认值
        userRepository.save(admin);
    }

    /**
     * 保存普通用户
     * @param user 用户
     */
    @Override
    public void saveUser(UserDO user){
        userRepository.save(user);
    }

    @Override
    public void deleteAdminById(String id){
        userRepository.deleteById(id);
    }


    /**
     * 查询所有用户，包括普通用户和管理员
     * @return 所有用户信息
     */
    @Override
    public List<UserDO> findAllAdmin(){
        return userRepository.findAll();
    }

    /**
     * 查询所有普通用户
     * @return 所有普通用户信息
     */
    @Override
    public List<UserDO> findAllUser(){
        // 查询条件
        Query query = Query.query(Criteria.where("type").is("user"));

        return mongoTemplate.find(query, UserDO.class);
    }

    /**
     * 查询所有管理员信息
     * @return 查询所有管理员信息
     */
    @Override
    public List<UserDO> findAllManage(){
        // 查询条件
        Query query = Query.query(Criteria.where("type").is("manage"));

        return mongoTemplate.find(query, UserDO.class);
    }

    /**
     *
     */
    @Override
    public boolean userIsExist(String username){
        // 查询条件
        Query name = Query.query(Criteria.where("userName").is(username));
        List<UserDO> user = mongoTemplate.find(name, UserDO.class);
        return !user.isEmpty();
    }

    /**
     * 根据用户名查询密码
     * @param username 用户名
     * @return 密码
     */
    @Override
    public String getPasswordByUsername(String username){
        // 根据用户名查询用户
        Query query = Query.query(Criteria.where("userName").is(username));
        UserDO target = mongoTemplate.findOne(query, UserDO.class);
        return target.getPassword();
    }
}

