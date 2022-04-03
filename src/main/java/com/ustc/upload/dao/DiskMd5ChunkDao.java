package com.ustc.upload.dao;

import com.ustc.entity.DiskMd5Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 田宝宁
 * @date 2021/6/20
 */
@Repository
public class DiskMd5ChunkDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 将多个块记录插入DiskMd5Chunk集合中
     *
     * @param chunkList 块列表
     */
    public void insertMany(List<DiskMd5Chunk> chunkList) {
        mongoTemplate.insertAll(chunkList);
    }

    /**
     * 向DiskMd5Chunk集合插入一个文档
     *
     * @param chunk 块
     */
    public void insertOne(DiskMd5Chunk chunk) {
        mongoTemplate.insert(chunk);
    }
}
