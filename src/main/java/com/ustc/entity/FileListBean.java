package com.ustc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
@ApiModel(description = "文件信息")
public class FileListBean implements Serializable {
    @ApiModelProperty(value = "文件id", hidden = true)
    private String id;
    @ApiModelProperty(name = "pid",value = "父文件夹id", required = true)
    private String pid;
    @ApiModelProperty(value = "父文件夹名", hidden = true)
    private String pName;
    @ApiModelProperty(value = "文件名", hidden = true)
    private String fileName;
    @ApiModelProperty(value = "文件大小", hidden = true)
    private long fileSize;
    @ApiModelProperty(value = "文件大小KB、MB等表示", hidden = true)
    private String fileSizeName;
    @ApiModelProperty(value = "文件后缀", hidden = true)
    private String fileSuffix;
    @ApiModelProperty(value = "文件md5", hidden = true)
    private String fileMd5;
    @ApiModelProperty(value = "文件类型", notes = "0-文件夹 1-文件", hidden = true)
    private Integer fileType;
    @ApiModelProperty(value = "创建者用户名", hidden = true)
    private String createUserName;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;


    // ------------------------ get/set方法 ------------------------ //


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSizeName() {
        return fileSizeName;
    }

    public void setFileSizeName(String fileSizeName) {
        this.fileSizeName = fileSizeName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
