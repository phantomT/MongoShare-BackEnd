package com.ustc.chain.param;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ustc.chain.core.ContextRequest;

import java.util.List;

/**
 * 将UrlUploadFileBean封装为在责任链中处理的请求
 * @author 田宝宁
 */
public class UrlUploadRequest extends ContextRequest {
    private String url;
    private String pid;
    private String fileName;
    private String fileMd5;
    private String userId;
    private String uuid;
    private Long totalSize;
    private Integer fileNum;
    private String fileSuffix;
    private Boolean isUrlExist;
    private Boolean isMd5Exist;
    private List<String> chunkList;

    public String getUrl() {
        return url;
    }

    public UrlUploadRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public UrlUploadRequest setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public UrlUploadRequest setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public UrlUploadRequest setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public UrlUploadRequest setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public UrlUploadRequest setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UrlUploadRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getMd5Exist() {
        return isMd5Exist;
    }

    public void setMd5Exist(Boolean md5Exist) {
        isMd5Exist = md5Exist;
    }

    public Boolean getUrlExist() {
        return isUrlExist;
    }

    public void setUrlExist(Boolean urlExist) {
        isUrlExist = urlExist;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public void setChunkList(List<String> chunkList) {
        this.chunkList = chunkList;
    }

    public List<String> getChunkList() {
        return chunkList;
    }
}
