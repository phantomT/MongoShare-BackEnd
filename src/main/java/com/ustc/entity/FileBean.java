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
    private String pname;
    private String filename;
    private Date uploadDate;
    private String fileSuffix;
    private long filesize;
    private String uploadUserId;
    private String filemd5;
    private Integer filetype;
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

    public String getPname() {
        return this.pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

    public long getFilesize() {
        return this.filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getUploadUserName() {
        return this.uploadUserName;
    }


    public void setUploadUserName(String uploadUserName) {

        this.uploadUserName = uploadUserName;

    }


    public String getFilemd5() {

        return this.filemd5;

    }


    public void setFilemd5(String filemd5) {

        this.filemd5 = filemd5;

    }


    public Integer getFiletype() {

        return this.filetype;

    }


    public void setFiletype(Integer filetype) {

        this.filetype = filetype;

    }


    public String getUploadUserId() {

        return this.uploadUserId;

    }


    public void setUploadUserId(String uploadUserId) {

        this.uploadUserId = uploadUserId;

    }


    @Override
    public String toString() {
        return "FileBean{id='" + this.id + '\'' + ", pid='" + this.pid + '\'' + ", pname='" + this.pname + '\'' + ", filename='" + this.filename + '\'' + ", uploadDate=" + this.uploadDate + ", fileSuffix='" + this.fileSuffix + '\'' + ", filesize=" + this.filesize + ", uploadUserId='" + this.uploadUserId + '\'' + ", filemd5='" + this.filemd5 + '\'' + ", filetype=" + this.filetype + ", uploadUserName='" + this.uploadUserName + '\'' + '}';
    }

}