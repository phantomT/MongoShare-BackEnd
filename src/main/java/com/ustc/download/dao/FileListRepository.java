package com.ustc.download.dao;

import com.ustc.entity.DiskFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 田宝宁
 * @date 2022/04/07
 */
public interface FileListRepository extends MongoRepository<DiskFile, String> {}
