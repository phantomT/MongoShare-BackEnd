package com.ustc.chain.handler.Merge;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 将相应的块从Redis中删除
 *
 * @author 田宝宁
 */
@Component
public class MergeDelRedisHandler extends Handler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        // key: 用户名-uuid-文件id-文件名-块序号
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;

            String key = bean.getUserName() + "-" + bean.getUuid() + "-" + bean.getFileId() +
                    "-" + bean.getFileName() + "-*";
            stringRedisTemplate.delete(key);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
