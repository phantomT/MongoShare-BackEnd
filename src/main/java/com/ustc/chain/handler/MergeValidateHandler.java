package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

/**
 * 合并切块校验参数
 * @author 田宝宁
 */
@Component
public class MergeValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof MergeRequest) {
            MergeRequest bean=(MergeRequest) request;

            ValidateUtils.validate(bean.getUserid(), "用户ID");
            ValidateUtils.validate(bean.getUsername(), "用户姓名");
            ValidateUtils.validate(bean.getPid(), "PID");
            ValidateUtils.validate(bean.getUuid(), "UUID");
            ValidateUtils.validate(bean.getFileid(), "文件ID");
            ValidateUtils.validate(bean.getFilemd5(), "文件MD5");
            ValidateUtils.validate(bean.getFilename(), "文件名称");
            ValidateUtils.validate(bean.getTotalSize(), "文件大小");
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
