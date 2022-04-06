package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.ChunkRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

/**
 * 切块上传参数校验
 * @author 田宝宁
 */
@Component
public class ChunkValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof ChunkRequest) {
            ChunkRequest chunk = (ChunkRequest) request;
            if (chunk.getBytes() == null || chunk.getBytes().length == 0) {
                throw new ServiceException(ServiceExceptionEnum.CHUNK_NOT_NULL);
            }
            ValidateUtils.validate(chunk.getUuid(), "uuid");
            ValidateUtils.validate(chunk.getId(), "文件id");
            ValidateUtils.validate(chunk.getName(), "文件名称");
            ValidateUtils.validate(chunk.getSize(), "文件大小");
            ValidateUtils.validate(chunk.getChunk(), "切块序号");
            ValidateUtils.validate(chunk.getChunks(), "切块数量");
            ValidateUtils.validate(chunk.getUserid(), "用户ID");
            ValidateUtils.validate(chunk.getUsername(), "用户姓名");
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
