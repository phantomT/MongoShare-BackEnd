package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.RedisChunkTemp;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检查上传的文件是否完整
 * @author 田宝宁
 */
@Component
public class MergeFileIsCompleteHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;
            List<RedisChunkTemp> chunkTemps = bean.getChunkTemps();

            long totalSize = 0;
            long size = 0;
            // 取出文件总大小数据
            if (!chunkTemps.isEmpty()) {
                totalSize = chunkTemps.get(0).getSize();
            }
            // 累加计算所有切块大小
            for (RedisChunkTemp temp : chunkTemps) {
                size += temp.getCurrentsize();
            }
            // 如果前端数据和累计的切块大小不符合那就上传失败
            if (size != totalSize) {
                throw new ServiceException(ServiceExceptionEnum.FILE_NOT_COMPLETE);
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
