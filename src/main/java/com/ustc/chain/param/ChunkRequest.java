package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

/**
 * 将Chunk封装为在责任链中处理的请求
 * @author 田宝宁
 */
public class ChunkRequest extends ContextRequest {
    /**
     * 文件id, 由前端生成
     */
    private String id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 切块序号
     */
    private Integer chunk;
    /**
     * 切块总数
     */
    private Integer chunks;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 该切块对应数据
     */
    private byte[] bytes;
    /**
     * 前端生成的uuid
     */
    private String uuid;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 块存储路径
     */
    private String storePath;
    /**
     * 文件类型
     */
    private String typecode;

    public String getId() {
        return id;
    }

    public ChunkRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChunkRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getChunk() {
        return chunk;
    }

    public ChunkRequest setChunk(Integer chunk) {
        this.chunk = chunk;
        return this;
    }

    public Integer getChunks() {
        return chunks;
    }

    public ChunkRequest setChunks(Integer chunks) {
        this.chunks = chunks;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public ChunkRequest setSize(Long size) {
        this.size = size;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public ChunkRequest setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public ChunkRequest setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public ChunkRequest setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChunkRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getStorePath() {
        return storePath;
    }

    public ChunkRequest setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }

    public String getTypecode() {
        return typecode;
    }

    public ChunkRequest setTypecode(String typecode) {
        this.typecode = typecode;
        return this;
    }
}
