package com.ustc.upload.service.impl;


import com.ustc.config.StoreConfiguration;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.service.UploadStoreService;
import com.ustc.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

/**
 * @author 田宝宁
 */
@Component
public class StoreServiceImpl implements UploadStoreService {

    @Autowired
    private StoreConfiguration storeConfiguration;


    @Override
    public String upload(byte[] bytes, String filename) {
        // 获取存储路径
        String storePath = storeConfiguration.getStorePath();
        // 获取存储文件夹名称
        String folders = FileUtils.getFolder();
        // 获取存储在文件系统中的新文件名
        String newFilename = UUID.randomUUID() + "." + FilenameUtils.getExtension(filename);
        // 将文件存入服务器中
        File file = new File(storePath + "/" + folders + "/" + newFilename);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs()) {
                throw new ServiceException(ServiceExceptionEnum.FOLDER_CREATE_FAIL);
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copyLarge(inputStream, outputStream);
            // 关闭流
            inputStream.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return folders + "/" + newFilename;
    }

    @Override
    public void delete(String path) {

    }
}
