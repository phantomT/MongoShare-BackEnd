package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.config.StoreConfiguration;
import com.ustc.entity.DiskMd5;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskMd5Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * 检验URL文件的Md5是否存在
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class UrlMd5IsExistHandler extends Handler {
    @Autowired
    DiskMd5Dao diskMd5Dao;
    @Autowired
    private StoreConfiguration storeConfiguration;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof UrlUploadRequest) {
            UrlUploadRequest bean = (UrlUploadRequest) request;

            // 获取存储路径
            String storePath = storeConfiguration.getStorePath();

            boolean isExist = diskMd5Dao.findMd5IsExist(bean.getFileMd5());
            bean.setMd5Exist(isExist);
            System.out.println("Url上传Md5检测" + isExist);

            this.updateRequest(bean);
            if(isExist){
                DiskMd5 diskMd5 = diskMd5Dao.findOne(bean.getFileMd5());
                // 该文件的Md5引用数+1
                diskMd5Dao.quoteAddOne(diskMd5);
                if(bean.getChunkList() != null){
                    // 删除MD5重复的文件分片
                    List<String> filePath = bean.getChunkList();
                    for(String path : filePath){
                        File file = new File(storePath + "/" + path);
                        if(file.isFile() && file.exists()){
                            file.delete();
                            System.out.println("Delete Exist file success: " + path);
                        } else {
                            System.out.println("Delete Exist file failure: " + path);
                        }
                    }
                }
            } else {
                String md5 = bean.getFileMd5();
                DiskMd5 diskMd5 = new DiskMd5();
                diskMd5.setQuoteNumber(1);
                diskMd5.setFileMd5(md5);
                diskMd5.setFileName(bean.getFileName());
                diskMd5.setFileNum(bean.getFileNum());
                diskMd5.setFileSuffix(bean.getFileSuffix());
                diskMd5.setFileSize(bean.getTotalSize());
                diskMd5.setTypeCode("0");

                diskMd5Dao.insertOne(diskMd5);
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
