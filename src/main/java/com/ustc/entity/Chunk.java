package com.ustc.entity;

import java.io.Serializable;

/**
 * @author 田宝宁
 */
public class Chunk implements Serializable {
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

    public String getId() {
        return id;
    }

    public Chunk setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Chunk setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getChunk() {
        return chunk;
    }

    public Chunk setChunk(Integer chunk) {
        this.chunk = chunk;
        return this;
    }

    public Integer getChunks() {
        return chunks;
    }

    public Chunk setChunks(Integer chunks) {
        this.chunks = chunks;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public Chunk setSize(Long size) {
        this.size = size;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Chunk setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Chunk setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Chunk setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Chunk setUsername(String username) {
        this.username = username;
        return this;
    }
}
