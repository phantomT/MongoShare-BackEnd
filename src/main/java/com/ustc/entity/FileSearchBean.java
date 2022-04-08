package com.ustc.entity;

/**
 * @author 王博
 * @date 2022/03/07
 */
public class FileSearchBean {
    private String id;
    /**
     * 查询关键字
     */
    private String fileName;
    /**
     * 父文件夹id
     */
    private String pid;
    /**
     * 父文件夹名字
     */
    private String pName;
    /**
     * 文件md5
     */
    private String fileMd5;
    private String fileIcon;
    /**
     * 文件类型码
     */
    private String typeCode;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 用户名
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public FileSearchBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileSearchBean setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public FileSearchBean setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getPName() {
        return pName;
    }

    public FileSearchBean setPName(String pName) {
        this.pName = pName;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public FileSearchBean setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getFileIcon() {
        return fileIcon;
    }

    public FileSearchBean setFileIcon(String fileIcon) {
        this.fileIcon = fileIcon;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public FileSearchBean setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public FileSearchBean setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    public String getFileSize() {
        return fileSize;
    }

    public FileSearchBean setFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public FileSearchBean setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public FileSearchBean setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public FileSearchBean setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
