package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.ChunkRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

/**
 * 校验文件格式
 * @author 田宝宁
 */
@Component
public class ChunkFileSuffixHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof ChunkRequest) {
            ChunkRequest chunk = (ChunkRequest) request;

            // 获取文件名后缀
            String suffix = FilenameUtils.getExtension(chunk.getName());
            // 待完善该功能
            chunk.setTypeCode("0");

        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
