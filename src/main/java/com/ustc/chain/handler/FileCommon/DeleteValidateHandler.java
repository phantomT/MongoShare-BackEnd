package com.ustc.chain.handler.FileCommon;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DeleteRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 田宝宁
 * @date 2022/04/01
 */
@Component
public class DeleteValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof DeleteRequest) {
            DeleteRequest deleteRequest = (DeleteRequest) request;

            ValidateUtils.validate(deleteRequest.getUserName(), "用户名");
            ValidateUtils.validate(deleteRequest.getIdList(), "需要删除的记录");

        } else {
            throw new ServiceException(ServiceExceptionEnum.VALIDATE_ERROR);
        }
    }
}
