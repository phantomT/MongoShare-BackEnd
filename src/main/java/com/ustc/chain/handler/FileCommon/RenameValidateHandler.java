package com.ustc.chain.handler.FileCommon;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.RenameRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 重命名参数校验
 *
 * @author 田宝宁
 * @date 2022/04/01
 */
@Component
public class RenameValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof RenameRequest) {
            RenameRequest renameRequest = (RenameRequest) request;

            ValidateUtils.validate(renameRequest.getId(), "文件id");
            ValidateUtils.validate(renameRequest.getNewName(), "新名称");
            ValidateUtils.validate(renameRequest.getUserName(), "用户名");
        } else {
            throw new ServiceException(ServiceExceptionEnum.RENAME_PARAM_ERROR);
        }
    }
}
