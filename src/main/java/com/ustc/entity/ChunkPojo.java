package com.ustc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 定义文件分片参数
 * @author 田宝宁
 * @date 2022/03/07
 */
@ApiModel(description = "切块参数")
public class ChunkPojo {
    @ApiModelProperty(value = "前端生成的uuid", required = true)
    private String uuid;
    @ApiModelProperty(value = "前端生成的文件id", required = true)
    private String id;
    @ApiModelProperty(value = "文件名称", required = true)
    private String name;
    @ApiModelProperty(value = "文件大小", required = true, example = "1")
    private Long size;
    @ApiModelProperty(value = "切块序号", required = true, example = "1")
    private Integer chunk;
    @ApiModelProperty(value = "切块数量", required = true, example = "10")
    private Integer chunks;
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    public String getUuid() {
        return uuid;
    }

    public ChunkPojo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getId() {
        return id;
    }

    public ChunkPojo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChunkPojo setName(String name) {
        this.name = name;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public ChunkPojo setSize(Long size) {
        this.size = size;
        return this;
    }

    public Integer getChunk() {
        return chunk;
    }

    public ChunkPojo setChunk(Integer chunk) {
        this.chunk = chunk;
        return this;
    }

    public Integer getChunks() {
        return chunks;
    }

    public ChunkPojo setChunks(Integer chunks) {
        this.chunks = chunks;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ChunkPojo setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
