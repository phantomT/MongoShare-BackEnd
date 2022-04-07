package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;
import com.ustc.entity.DiskFile;
import com.ustc.entity.RedisChunkTemp;

import java.util.List;

/**
 * 将MergeFileBean封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/03/15
 */
public class MergeRequest extends ContextRequest {
    /**
     * 文件夹pid, 上传到哪个文件夹
     */
    private String pid;
    /**
     * 前端上传uuid
     */
    private String uuid;
    /**
     * 前端上传文件id
     */
    private String fileid;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 文件大小
     */
    private long totalSize;
    /**
     * 文件md5
     */
    private String filemd5;
    /**
     * 用于文件夹上传时,保存相对路径
     */
    private String relativepath;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 用户名
     */
    private String username;

    //与MergeFileBean不同的内容

    /**
     * 在文件md5表中是否存在
     */
    private boolean existInDiskmd5;
    /**
     * 在文件表中是否存在
     */
    private boolean existInDiskFile;
    /**
     * Redis中查询到的所有块
     */
    private List<RedisChunkTemp> chunkTemps;
    /**
     * 所有的文件夹
     */
    private List<DiskFile> folders;
    /**
     * 文件后缀名
     */
    private String fileSuffix;

    public String getPid() {
        return pid;
    }

    public MergeRequest setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public MergeRequest setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFileid() {
        return fileid;
    }

    public MergeRequest setFileid(String fileid) {
        this.fileid = fileid;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public MergeRequest setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public MergeRequest setTotalSize(long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public MergeRequest setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
        return this;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public MergeRequest setRelativepath(String relativepath) {
        this.relativepath = relativepath;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public MergeRequest setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MergeRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isExistInDiskmd5() {
        return existInDiskmd5;
    }

    public MergeRequest setExistInDiskmd5(boolean existInDiskmd5) {
        this.existInDiskmd5 = existInDiskmd5;
        return this;
    }

    public boolean isExistInDiskFile() {
        return existInDiskFile;
    }

    public MergeRequest setExistInDiskFile(boolean existInDiskFile) {
        this.existInDiskFile = existInDiskFile;
        return this;
    }

    public List<RedisChunkTemp> getChunkTemps() {
        return chunkTemps;
    }

    public MergeRequest setChunkTemps(List<RedisChunkTemp> chunkTemps) {
        this.chunkTemps = chunkTemps;
        return this;
    }

    public List<DiskFile> getFolders() {
        return folders;
    }

    public MergeRequest setFolders(List<DiskFile> folders) {
        this.folders = folders;
        return this;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public MergeRequest setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }
}
