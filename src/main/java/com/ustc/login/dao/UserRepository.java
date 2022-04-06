package com.ustc.login.dao;

import com.ustc.entity.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDO, String> {}
