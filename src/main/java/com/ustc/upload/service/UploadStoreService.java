package com.ustc.upload.service;

import java.io.FileNotFoundException;
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
     * 删除文件
     * @param path 文件路径
     */
    void delete(String path);
}
