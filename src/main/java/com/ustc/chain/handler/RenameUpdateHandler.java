package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.RenameRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.fileCommon.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 重命名过程处理
 * @author 田宝宁
 * @date 2022/04/01
 */
@Component
public class RenameUpdateHandler extends Handler {

    @Autowired
    FileDao fileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof RenameRequest) {
            RenameRequest renameRequest = (RenameRequest) request;

            fileDao.updateRecordFileName(renameRequest.getId(), renameRequest.getNewName());
        } else {
            throw new ServiceException(ServiceExceptionEnum.RENAME_PARAM_ERROR);
        }
    }
}
