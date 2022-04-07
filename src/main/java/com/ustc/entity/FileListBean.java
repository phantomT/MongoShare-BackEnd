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
    private String pname;
    @ApiModelProperty(value = "文件名", hidden = true)
    private String filename;
    @ApiModelProperty(value = "文件大小", hidden = true)
    private long filesize;
    @ApiModelProperty(value = "文件大小KB、MB等表示", hidden = true)
    private String filesizename;
    @ApiModelProperty(value = "文件后缀", hidden = true)
    private String filesuffix;
    @ApiModelProperty(value = "文件md5", hidden = true)
    private String filemd5;
    @ApiModelProperty(value = "文件类型", notes = "0-文件夹 1-文件", hidden = true)
    private Integer filetype;
    @ApiModelProperty(value = "创建者id", hidden = true)
    private String createuserid;
    @ApiModelProperty(value = "创建者昵称", hidden = true)
    private String createusername;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createtime;


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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getFilesizename() {
        return filesizename;
    }

    public void setFilesizename(String filesizename) {
        this.filesizename = filesizename;
    }

    public String getFilesuffix() {
        return filesuffix;
    }

    public void setFilesuffix(String filesuffix) {
        this.filesuffix = filesuffix;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public void setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
