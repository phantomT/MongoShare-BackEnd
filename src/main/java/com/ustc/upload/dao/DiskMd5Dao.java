package com.ustc.upload.dao;

import com.ustc.entity.DiskFile;
import com.ustc.entity.DiskMd5;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 田宝宁
 */
@Repository
public class DiskMd5Dao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 判断文件md5在文件md5集合中是否存在
     *
     * @param fileMd5 文件Md5
     * @return 存在返回true, 不存在返回false
     */
    public boolean findMd5IsExist(String fileMd5) {
        return mongoTemplate.exists(new Query(Criteria.where("fileMd5").is(fileMd5)), DiskMd5.class);
    }

    /**
     * 将一个diskMd5对象插入集合中
     *
     * @param diskMd5 DiskMd5文档实体对象
     */
    public void insertOne(DiskMd5 diskMd5) {
        mongoTemplate.insert(diskMd5);
    }

    /**
     * 将一个diskMd5对象的引用数量加一
     *
     * @param diskMd5 DiskMd5文档实体对象
     */
    public void quoteAddOne(DiskMd5 diskMd5) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileMd5").is(diskMd5.getFileMd5()));
        Update update = new Update();
        update.set("quoteNumber", diskMd5.getQuoteNumber()+1);
        mongoTemplate.updateFirst(query, update, DiskMd5.class);
    }

    /**
     * 根据文件MD5查找对应的DiskMd5对象
     *
     * @param fileMd5 文件Md5
     * @return DiskMd5 DiskMd5文档实体对象
     */
    public DiskMd5 findOne(String fileMd5) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileMd5").is(fileMd5));
        return mongoTemplate.findOne(query, DiskMd5.class);
    }
}
