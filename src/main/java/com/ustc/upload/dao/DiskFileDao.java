package com.ustc.upload.dao;

import com.ustc.entity.DiskFile;
import com.ustc.utils.FileType;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author 田宝宁
 * @date 2022/04/03
 */
@Repository
public class DiskFileDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据username-pid-name-filetype能够唯一确定一个文件夹
     *
     * @param userName  用户名
     * @param pid       父文件夹id
     * @param name      文件名
     * @return 对应文件夹
     */
    public DiskFile findFolder(String userName, String pid, String name) {
        Query query = new Query();
        // 文件类型为0, 即文件夹
        query.addCriteria(Criteria.where("fileType").is(FileType.FOLDER.getTypeCode()));
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("pid").is(pid));
        query.addCriteria(Criteria.where("fileName").is(name));
        return mongoTemplate.findOne(query, DiskFile.class);
    }

    public void insertOne(DiskFile diskFile) {
        mongoTemplate.insert(diskFile);
    }

    /**
     * 使用userid-pid-fileName-fileMd5来唯一确定一个用户文件
     *
     * @param userName  用户名
     * @param pid       父文件夹id
     * @param fileName  文件名
     * @param fileMd5   文件md5
     * @return 用户文件表文档
     */
    public DiskFile findFile(String userName, String pid, String fileName, String fileMd5) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("pid").is(pid));
        query.addCriteria(Criteria.where("fileName").is(fileName));
        query.addCriteria(Criteria.where("fileMd5").is(fileMd5));
        return mongoTemplate.findOne(query, DiskFile.class);
    }

    public DiskFile findFileById(ObjectId id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), DiskFile.class);
    }
}
