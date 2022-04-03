package com.ustc.entity;

import java.io.Serializable;

/**
 * @author 田宝宁
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
    private Integer currentsize;
    /**
     * 切块序号
     */
    private Integer chunk;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 存储路径
     */
    private String storepath;
    /**
     * 类型
     */
    private String typecode;
    /**
     * 文件后缀
     */
    private String filesuffix;
    /**
     * 相对路径
     */
    private String relativepath;

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

    public Integer getCurrentsize() {
        return currentsize;
    }

    public RedisChunkTemp setCurrentsize(Integer currentsize) {
        this.currentsize = currentsize;
        return this;
    }

    public Integer getChunk() {
        return chunk;
    }

    public RedisChunkTemp setChunk(Integer chunk) {
        this.chunk = chunk;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public RedisChunkTemp setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getStorepath() {
        return storepath;
    }

    public RedisChunkTemp setStorepath(String storepath) {
        this.storepath = storepath;
        return this;
    }

    public String getTypecode() {
        return typecode;
    }

    public RedisChunkTemp setTypecode(String typecode) {
        this.typecode = typecode;
        return this;
    }

    public String getFilesuffix() {
        return filesuffix;
    }

    public RedisChunkTemp setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
        return this;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public RedisChunkTemp setRelativepath(String relativepath) {
        this.relativepath = relativepath;
        return this;
    }
}
