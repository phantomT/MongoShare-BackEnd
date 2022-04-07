package com.ustc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 使用URL上传文件后记录文件信息
 * @author 田宝宁
 * @date 2022/04/05
 */
public class UrlFileBean implements Serializable {
    /**
     * 文件URL
     */
    private String fileUrl;
    /**
     * 文件名（第一次上传的文件名）
     */
    private String fileName;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 文件总大小
     */
    private long filesize;
    /**
     * 文件MD5
     */
    private String fileMd5;
    /**
     * 上传的切片文件的带路径文件名
     */
    private List<String> chunkList;

    public UrlFileBean(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() { return this.fileUrl; }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return this.fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public long getFilesize() {
        return this.filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getFileMd5() {

        return this.fileMd5;

    }

    public void setFileMd5(String fileMd5) {

        this.fileMd5 = fileMd5;

    }

    public List<String> getChunkList() {
        return chunkList;
    }

    public void setChunkList(List<String> chunkList) {
        this.chunkList = chunkList;
    }
}