package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.ChunkRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.service.UploadStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 切块存储
 * @author 田宝宁
 */
@Component
public class ChunkStoreHandler extends Handler {

    @Autowired
    private UploadStoreService uploadStoreService;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response){
        if (request instanceof ChunkRequest) {
            // 类型转换
            ChunkRequest chunk = (ChunkRequest) request;
            // 将块上传
            String storePath = uploadStoreService.upload(chunk.getBytes(), chunk.getName());
            // 设置存储路径
            chunk.setStorePath(storePath);
            this.updateRequest(chunk);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
