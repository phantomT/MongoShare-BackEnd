package com.ustc.entity;

import java.io.Serializable;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
public class RedisChunkTemp implements Serializable {
    /**
     * 文件id
     */
    private String id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 总大小
     */
    private Long size;
    /**
     * 总切块数
     */
    private Integer chunks;
    /**
     * 当前切块大小
     */
    private Integer currentSize;
    /**
     * 切块序号
     */
    private Integer chunk;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 存储路径
     */
    private String storePath;
    /**
     * 类型
     */
    private String typeCode;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 相对路径
     */
    private String relativePath;

    public String getId() {
        return id;
    }

    public RedisChunkTemp setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RedisChunkTemp setName(String name) {
        this.name = name;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public RedisChunkTemp setSize(Long size) {
        this.size = size;
        return this;
    }

    public Integer getChunks() {
        return chunks;
    }

    public RedisChunkTemp setChunks(Integer chunks) {
        this.chunks = chunks;
        return this;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public RedisChunkTemp setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
        return this;
    }

    public Integer getChunk() {
        return chunk;
    }

    public RedisChunkTemp setChunk(Integer chunk) {
        this.chunk = chunk;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public RedisChunkTemp setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getStorePath() {
        return storePath;
    }

    public RedisChunkTemp setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public RedisChunkTemp setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public RedisChunkTemp setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public RedisChunkTemp setRelativePath(String relativePath) {
        this.relativePath = relativePath;
        return this;
    }
}
