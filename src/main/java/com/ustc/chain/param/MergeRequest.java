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
    private String fileId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private long totalSize;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 用于文件夹上传时,保存相对路径
     */
    private String relativePath;
    /**
     * 用户名
     */
    private String userName;

    //与MergeFileBean不同的内容

    /**
     * 在文件md5表中是否存在
     */
    private boolean existInDiskMd5;
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

    public String getFileId() {
        return fileId;
    }

    public MergeRequest setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public MergeRequest setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public MergeRequest setTotalSize(long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public MergeRequest setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public MergeRequest setRelativePath(String relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public MergeRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public boolean isExistInDiskMd5() {
        return existInDiskMd5;
    }

    public MergeRequest setExistInDiskMd5(boolean existInDiskMd5) {
        this.existInDiskMd5 = existInDiskMd5;
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
