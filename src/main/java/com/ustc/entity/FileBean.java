package com.ustc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
public class FileBean implements Serializable {
    private String id;
    private String pid;
    private String pName;
    private String filename;
    private Date uploadDate;
    private String fileSuffix;
    private long fileSize;
    private String fileMd5;
    private Integer fileType;
    private String uploadUserName;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getpName() {
        return this.pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileSuffix() {
        return this.fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadUserName() {
        return this.uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {

        this.uploadUserName = uploadUserName;

    }

    public String getFileMd5() {

        return this.fileMd5;

    }

    public void setFileMd5(String fileMd5) {

        this.fileMd5 = fileMd5;

    }

    public Integer getFileType() {

        return this.fileType;

    }

    public void setFileType(Integer fileType) {

        this.fileType = fileType;

    }


    @Override
    public String toString() {
        return "FileBean{id='" + this.id + '\'' + ", pid='" + this.pid + '\'' + ", pname='" + this.pName + '\'' + ", filename='" + this.filename + '\'' + ", uploadDate=" + this.uploadDate + ", fileSuffix='" + this.fileSuffix + '\'' + ", filesize=" + this.fileSize + ", filemd5='" + this.fileMd5 + '\'' + ", filetype=" + this.fileType + ", uploadUserName='" + this.uploadUserName + '\'' + '}';
    }

}