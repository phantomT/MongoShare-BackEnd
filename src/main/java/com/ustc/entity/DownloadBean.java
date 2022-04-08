package com.ustc.entity;

import java.io.Serializable;

/**
 * 定义DownloadBean
 * @author 田宝宁
 * @date 2022/04/08
 */
public class DownloadBean implements Serializable {
    /**
     * 文件分片数
     */
    private Integer fileNum;
    /**
     * 文件夹数量
     */
    private Integer folderNum;
    /**
     * 文件总大小
     */
    private long totalSize;
    /**
     * 文件总大小的字符串
     */
    private String totalSizeName;
    /**
     * 是否是大文件
     */
    private Integer isBig;

    public DownloadBean() {
    }

    public DownloadBean(Integer fileNum, Integer folderNum, long totalSize, String totalSizeName, Integer isBig) {
        this.fileNum = fileNum;
        this.folderNum = folderNum;
        this.totalSize = totalSize;
        this.totalSizeName = totalSizeName;
        this.isBig = isBig;
    }


    public Integer getFileNum() {
        return this.fileNum;
    }


    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }


    public Integer getFolderNum() {
        return this.folderNum;
    }


    public void setFolderNum(Integer folderNum) {
        this.folderNum = folderNum;
    }


    public long getTotalSize() {
        return this.totalSize;
    }


    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }


    public String getTotalSizeName() {
        return this.totalSizeName;
    }


    public void setTotalSizeName(String totalSizeName) {
        this.totalSizeName = totalSizeName;
    }


    public Integer getIsBig() {
        return this.isBig;
    }


    public void setIsBig(Integer isBig) {
        this.isBig = isBig;
    }


    public String toString() {

        return "DownloadBean{fileNum=" + this.fileNum + ", folderNum=" + this.folderNum + ", totalSize=" + this.totalSize + ", totalSizeName='" + this.totalSizeName + '\'' + ", isBig=" + this.isBig + '}';

    }

}
