package com.ustc.download.dao;

import com.ustc.entity.DiskFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileListRepository extends MongoRepository<DiskFile, String> {}
