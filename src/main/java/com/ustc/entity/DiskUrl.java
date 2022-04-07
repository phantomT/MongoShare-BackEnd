package com.ustc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 记录用户上传文件的URL，减少同一URL重复上传已有文件
 * @author 田宝宁
 * @date 2022/04/04
 */
@Document(collection = "DiskUrl")
public class DiskUrl {
    /**
     * 文件id
     */
    @Id
    private ObjectId id;
    /**
     * 文件URL
     */
    private String fileUrl;
    /**
     * 文件md5
     */
    private String fileMd5;

    public ObjectId getId() {
        return id;
    }

    public DiskUrl setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFileUrl() { return fileUrl; }

    public DiskUrl setFileUrl(String url) {
        this.fileUrl = url;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public DiskUrl setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }
}
