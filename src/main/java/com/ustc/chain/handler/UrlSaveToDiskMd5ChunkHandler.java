package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.config.StoreConfiguration;
import com.ustc.entity.DiskMd5Chunk;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskMd5ChunkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 将切块信息保存到DiskMd5Chunk表中
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class UrlSaveToDiskMd5ChunkHandler extends Handler {

    @Autowired
    private DiskMd5ChunkDao diskMd5ChunkDao;

    @Autowired
    private StoreConfiguration storeConfiguration;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof UrlUploadRequest) {
            UrlUploadRequest bean = (UrlUploadRequest) request;

            String storePath = storeConfiguration.getStorePath();

            // 如果bean不存在于DiskMd5表中, 即服务器内不存在该文件时上传
            if (!bean.getMd5Exist()) {
                // 获取所有切块记录
                List<String> filePath = bean.getChunkList();
                // 将切块记录转换为MongoDb切块记录
                List<DiskMd5Chunk> chunkList = new ArrayList<>();

                for (int i = 0; i < filePath.size(); i++) {
                    String path = filePath.get(i);
                    File temp = new File(storePath + "/" +path);
                    // 设置文档属性
                    // 存入集合中的文档不设id, 使用mongoDB自带的ObjectId
                    DiskMd5Chunk md5Chunk = new DiskMd5Chunk();
                    md5Chunk.setFileMd5(bean.getFileMd5());
                    md5Chunk.setChunkName(path.substring(path.lastIndexOf("/")));
                    md5Chunk.setChunkNumber(i);
                    md5Chunk.setChunkSize(temp.length());
                    md5Chunk.setTotalChunks(bean.getFileNum());
                    md5Chunk.setTotalSize(bean.getTotalSize());
                    md5Chunk.setStorePath(path);

                    // 将对应文档加入List中
                    chunkList.add(md5Chunk);
                }
                diskMd5ChunkDao.insertMany(chunkList);
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
