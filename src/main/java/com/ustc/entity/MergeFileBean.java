package com.ustc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
@ApiModel
public class MergeFileBean implements Serializable {
    @ApiModelProperty(value = "前端生成的文件夹pid", required = true)
    private String pid;
    @ApiModelProperty(value = "前端生成的uuid", required = true)
    private String uuid;
    @ApiModelProperty(value = "前端生成的文件id", required = true)
    private String fileId;
    @ApiModelProperty(value = "文件名", required = true)
    private String fileName;
    @ApiModelProperty(value = "文件大小", required = true, example = "1")
    private Long totalSize;
    @ApiModelProperty(value = "文件md5", required = true)
    private String fileMd5;
    @ApiModelProperty(value = "用于文件夹上传时,保存文件夹结构相对路径")
    private String relativePath;
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    public String getPid() {
        return pid;
    }

    public MergeFileBean setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public MergeFileBean setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public MergeFileBean setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public MergeFileBean setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public MergeFileBean setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public MergeFileBean setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public MergeFileBean setRelativePath(String relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public MergeFileBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
