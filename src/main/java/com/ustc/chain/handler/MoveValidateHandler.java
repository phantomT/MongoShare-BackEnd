package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MoveRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 移动操作的参数校验
 * @author 田宝宁
 * @date 2022/04/01
 */
@Component
public class MoveValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof MoveRequest) {
            MoveRequest moveRequest = (MoveRequest) request;

            ValidateUtils.validate(moveRequest.getPid(), "选择的文件夹");
            ValidateUtils.validate(moveRequest.getUserid(), "用户id");
            ValidateUtils.validate(moveRequest.getIdList(), "需要移动的记录");

        } else {
            throw new ServiceException(ServiceExceptionEnum.VALIDATE_ERROR);
        }
    }
}
