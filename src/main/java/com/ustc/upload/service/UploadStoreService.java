package com.ustc.upload.service;

import com.ustc.entity.UrlFileBean;

import java.io.IOException;

public interface UploadStoreService {
    /**
     * 上传文件
     * @param bytes 文件二进制数据
     * @param filename 文件名
     * @return 存储在服务器文件系统中的文件名
     */
    String upload(byte[] bytes, String filename);

    /**
     * 上传URL文件
     * @param fileUrl   URL
     * @param fileName  用户定义文件名
     * @return 存储在服务器文件系统中的文件名
     */
    UrlFileBean uploadUrl(String fileUrl, String fileName) throws IOException, InterruptedException;
}
