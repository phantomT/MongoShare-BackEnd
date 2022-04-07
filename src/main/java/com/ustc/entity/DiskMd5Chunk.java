package com.ustc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 该集合主要用于存储文件的切块记录，由于文件在上传时被切分为一个个大小为1M的块
 * 一个DiskMd5集合中的文档会对应于许多DiskMd5Chunk中的文档
 * @author 田宝宁
 * @date 2022/04/01
 */
@Document(collection = "DiskMd5Chunk")
public class DiskMd5Chunk {
    @Id
    private ObjectId id;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 切块名称
     */
    private String chunkName;
    /**
     * 切块序号
     */
    private Integer chunkNumber;
    /**
     * 切块大小
     */
    private Long chunkSize;
    /**
     * 切块总数
     */
    private Integer totalChunks;
    /**
     * 文件大小
     */
    private Long totalSize;
    /**
     * 文件路径
     */
    private String storePath;

    public ObjectId getId() {
        return id;
    }

    public DiskMd5Chunk setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public DiskMd5Chunk setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getChunkName() {
        return chunkName;
    }

    public DiskMd5Chunk setChunkName(String chunkName) {
        this.chunkName = chunkName;
        return this;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public DiskMd5Chunk setChunkNumber(Integer chunkNumber) {
        this.chunkNumber = chunkNumber;
        return this;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public DiskMd5Chunk setChunkSize(Long chunkSize) {
        this.chunkSize = chunkSize;
        return this;
    }

    public Integer getTotalChunks() {
        return totalChunks;
    }

    public DiskMd5Chunk setTotalChunks(Integer totalChunks) {
        this.totalChunks = totalChunks;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public DiskMd5Chunk setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getStorePath() {
        return storePath;
    }

    public DiskMd5Chunk setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }
}
