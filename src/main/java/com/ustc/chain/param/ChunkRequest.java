package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

/**
 * 将Chunk封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/03/15
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
     * 用户名
     */
    private String userName;
    /**
     * 块存储路径
     */
    private String storePath;
    /**
     * 文件类型
     */
    private String typeCode;

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

    public String getUserName() {
        return userName;
    }

    public ChunkRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getStorePath() {
        return storePath;
    }

    public ChunkRequest setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public ChunkRequest setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }
}
