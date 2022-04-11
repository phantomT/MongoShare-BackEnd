package com.ustc.chain.handler.Merge;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.DiskMd5Chunk;
import com.ustc.entity.RedisChunkTemp;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskMd5ChunkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 将切块信息保存到DiskMd5Chunk表中
 *
 * @author 田宝宁
 */
@Component
public class MergeSaveToDiskMd5ChunkHandler extends Handler {

    @Autowired
    private DiskMd5ChunkDao diskMd5ChunkDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;

            // 如果bean不存在于DiskMd5表中, 即服务器内不存在该文件时上传
            if (!bean.isExistInDiskMd5()) {
                // 获取所有切块记录
                List<RedisChunkTemp> chunkTemps = bean.getChunkTemps();
                // 将Redis切块记录转换为MongoDb切块记录
                List<DiskMd5Chunk> chunkList = new ArrayList<>();

                for (RedisChunkTemp temp : chunkTemps) {
                    // 设置文档属性
                    // 存入集合中的文档不设id, 使用mongoDB自带的ObjectId
                    DiskMd5Chunk md5Chunk = new DiskMd5Chunk();
                    md5Chunk.setFileMd5(bean.getFileMd5());
                    md5Chunk.setChunkName(temp.getName());
                    md5Chunk.setChunkNumber(temp.getChunk());
                    md5Chunk.setChunkSize(temp.getCurrentSize().longValue());
                    md5Chunk.setTotalChunks(temp.getChunks());
                    md5Chunk.setTotalSize(temp.getSize());
                    md5Chunk.setStorePath(temp.getStorePath());

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
