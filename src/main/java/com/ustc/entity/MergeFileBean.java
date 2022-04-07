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
    private String fileid;
    @ApiModelProperty(value = "文件名", required = true)
    private String filename;
    @ApiModelProperty(value = "文件大小", required = true, example = "1")
    private Long totalSize;
    @ApiModelProperty(value = "文件md5", required = true)
    private String filemd5;
    @ApiModelProperty(value = "用于文件夹上传时,保存文件夹结构相对路径")
    private String relativepath;
    @ApiModelProperty(value = "用户id", required = true)
    private String userid;
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

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

    public String getFileid() {
        return fileid;
    }

    public MergeFileBean setFileid(String fileid) {
        this.fileid = fileid;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public MergeFileBean setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public MergeFileBean setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public MergeFileBean setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
        return this;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public MergeFileBean setRelativepath(String relativepath) {
        this.relativepath = relativepath;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public MergeFileBean setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MergeFileBean setUsername(String username) {
        this.username = username;
        return this;
    }
}
