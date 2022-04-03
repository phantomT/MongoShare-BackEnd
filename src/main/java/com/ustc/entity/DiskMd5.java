package com.ustc.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 文件Md5, 每个文件唯一一份, 一个文件Md5表的数据项对应文件切块表中的多个数据项
 * @author 田宝宁
 */
@Document(collection = "DiskMd5")
public class DiskMd5 {
    /**
     * 文件id
     */
    @Id
    private ObjectId id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 文件切块数量
     */
    private Integer fileNum;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件类型
     */
    private String typeCode;
    /**
     * 用户文件引用次数
     */
    private Integer quoteNumber;

    public ObjectId getId() {
        return id;
    }

    public DiskMd5 setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public DiskMd5 setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public DiskMd5 setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
        return this;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public DiskMd5 setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
        return this;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public DiskMd5 setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public DiskMd5 setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public DiskMd5 setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public Integer getQuoteNumber() { return quoteNumber; }

    public DiskMd5 setQuoteNumber(Integer quoteNumber) {
        this.quoteNumber = quoteNumber;
        return this;
    }
}
