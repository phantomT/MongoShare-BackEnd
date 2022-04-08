package com.ustc.download.dao;

import com.ustc.entity.DiskFile;
import com.ustc.entity.DiskMd5Chunk;
import com.ustc.entity.UserDO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/08
 */
@Repository
public class FileListDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据文件id查询一条记录
     * @param id    文件id
     * @return 一条记录
     */
    public DiskFile findDiskFileById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, DiskFile.class);
    }

    /**
     * 根据用户名查找用户
     * @param userName  用户名
     * @return UserDO
     */
    public UserDO findUserByName(String userName){
        Query query = new Query();
        query.addCriteria((Criteria.where("userName").is(userName)));
        return mongoTemplate.findOne(query, UserDO.class);
    }

    /**
     * 根据pid查找DiskFile列表
     * @param pid   文件夹id
     * @return List<DiskFile>
     */
    public List<DiskFile> findDiskFileByPid(String pid){
        Query query = new Query();
        query.addCriteria(Criteria.where("pid").is(pid));
        return mongoTemplate.find(query, DiskFile.class);
    }

    /**
     * 根据MD5查找Md5Chunk
     * @param md5   md5
     * @return List<DiskMd5Chunk>
     */
    public List<DiskMd5Chunk> findDiskChunkByMd5(String md5){
        Query query = new Query();
        query.addCriteria(Criteria.where("fileMd5").is(md5));
        return mongoTemplate.find(query, DiskMd5Chunk.class);
    }

    /**
     * 提供分页文件查询
     * @param pid           父文件夹id
     * @param orderField    排序字段
     * @param pageIndex     页索引
     * @param pageSize      页大小
     * @return List<DiskFile>
     */
    public List<DiskFile> findPageWithSort(String pid, String orderField, Integer pageIndex, Integer pageSize){
        Query query = new Query(Criteria.where("pid").is(pid));
        Sort sort = Sort.by(Sort.Order.desc(orderField));
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
        query.with(pageable);
        return mongoTemplate.find(query, DiskFile.class);
    }
}
