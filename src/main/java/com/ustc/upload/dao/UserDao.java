package com.ustc.upload.dao;

import com.ustc.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试用户DAO
 * @author yejiayun
 */
@Repository
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertOne(UserDO userDO) {
        mongoTemplate.insert(userDO);
    }

    public void updateById(UserDO userDO) {
        Update update = new Update();
        update.set("username", "fuck");
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(userDO.getId())), update, userDO.getClass());
    }

    public void deleteById(Integer id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

    public List<UserDO> findAllById(List<Integer> ids) {
        return mongoTemplate.find(new Query(Criteria.where("_id").in(ids)), UserDO.class);
    }
}
