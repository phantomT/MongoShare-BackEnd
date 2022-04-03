package com.ustc.entity;

import java.io.Serializable;

/**
 * @author 田宝宁
 */
public class UrlUploadFileBean implements Serializable {
    private String url;
    private String pid;
    private String fileName;
    private String fileMd5;
    private String userId;
    private String fileId;
    private String uuid;
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

    public String getUserId() {
        return userId;
    }

    public UrlUploadFileBean setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
