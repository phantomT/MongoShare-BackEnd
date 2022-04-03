package com.ustc.upload.dao;

import com.ustc.entity.DiskUrl;
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
public class DiskUrlDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 判断文件url在文件url集合中是否存在
     *
     * @param fileUrl 文件Url
     * @return 存在返回true, 不存在返回false
     */
    public boolean findUrlIsExist(String fileUrl) {
        return mongoTemplate.exists(new Query(Criteria.where("fileUrl").is(fileUrl)), DiskUrl.class);
    }

    /**
     * 将一个diskUrl对象插入集合中
     *
     * @param diskUrl DiskUrl文档实体对象
     */
    public void insertUrl(DiskUrl diskUrl) {
        mongoTemplate.insert(diskUrl);
    }

    /**
     * 根据文件Url查找对应的DiskUrl对象
     *
     * @param fileUrl 文件Url
     * @return DiskUrl DiskUrl文档实体对象
     */
    public DiskUrl findMd5(String fileUrl) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileUrl").is(fileUrl));
        return mongoTemplate.findOne(query, DiskUrl.class);
    }

    /**
     * 根据文件Url修改对应的DiskUrl对象的MD5
     *
     * @param fileUrl   文件Url
     * @param fileMd5   文件Md5
     */
    public void setMd5(String fileUrl, String fileMd5) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fileUrl").is(fileUrl));
        Update update = new Update();
        update.set("fileMd5", fileMd5);
        mongoTemplate.updateFirst(query, update, DiskUrl.class);
    }
}
