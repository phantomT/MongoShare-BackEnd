package com.ustc.entity;

import java.io.Serializable;

/**
 * @author 田宝宁
 * @date 2022/04/05
 */
public class UrlUploadFileBean implements Serializable {
    /**
     * 文件URL
     */
    private String url;
    /**
     * 父文件夹
     */
    private String pid;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件MD5
     */
    private String fileMd5;
    /**
     * 上传的用户名
     */
    private String userName;
    /**
     * 文件Id
     */
    private String fileId;
    /**
     * UUID
     */
    private String uuid;
    /**
     * 文件总大小
     */
    private Long totalSize;

    public UrlUploadFileBean(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public UrlUploadFileBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public UrlUploadFileBean setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UrlUploadFileBean setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public UrlUploadFileBean setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public UrlUploadFileBean setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public UrlUploadFileBean setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public UrlUploadFileBean setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UrlUploadFileBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
