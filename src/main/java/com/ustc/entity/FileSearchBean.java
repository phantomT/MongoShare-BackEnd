package com.ustc.entity;

/**
 * @author 田宝宁
 * @date 2021/6/27
 */
public class FileSearchBean {
    private String id;
    /**
     * 查询关键字
     */
    private String filename;
    /**
     * 父文件夹id
     */
    private String pid;
    /**
     * 父文件夹名字
     */
    private String pname;
    /**
     * 文件md5
     */
    private String filemd5;
    private String fileicon;
    /**
     * 文件类型码
     */
    private String typecode;
    /**
     * 文件后缀
     */
    private String filesuffix;
    /**
     * 文件大小
     */
    private String filesize;
    /**
     * 文件类型
     */
    private String filetype;
    /**
     * 用户id
     */
    private String createuserid;
    /**
     * 用户名
     */
    private String createusername;
    /**
     * 创建时间
     */
    private String createtime;

    public String getId() {
        return id;
    }

    public FileSearchBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public FileSearchBean setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public FileSearchBean setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getPname() {
        return pname;
    }

    public FileSearchBean setPname(String pname) {
        this.pname = pname;
        return this;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public FileSearchBean setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
        return this;
    }

    public String getFileicon() {
        return fileicon;
    }

    public FileSearchBean setFileicon(String fileicon) {
        this.fileicon = fileicon;
        return this;
    }

    public String getTypecode() {
        return typecode;
    }

    public FileSearchBean setTypecode(String typecode) {
        this.typecode = typecode;
        return this;
    }

    public String getFilesuffix() {
        return filesuffix;
    }

    public FileSearchBean setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
        return this;
    }

    public String getFilesize() {
        return filesize;
    }

    public FileSearchBean setFilesize(String filesize) {
        this.filesize = filesize;
        return this;
    }

    public String getFiletype() {
        return filetype;
    }

    public FileSearchBean setFiletype(String filetype) {
        this.filetype = filetype;
        return this;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public FileSearchBean setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
        return this;
    }

    public String getCreateusername() {
        return createusername;
    }

    public FileSearchBean setCreateusername(String createusername) {
        this.createusername = createusername;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public FileSearchBean setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }
}
