package com.ustc.chain.handler.Download;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

/**
 * 文件下载参数校验
 *
 * @author 田宝宁
 * @date 2022/04/08
 */
@Component
public class DownloadValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof DownloadRequest) {
            DownloadRequest downRequest = (DownloadRequest) request;

            ValidateUtils.validate(downRequest.getFileName(), "文件名");
            ValidateUtils.validate(downRequest.getFileSuffix(), "文件后缀");
            ValidateUtils.validate(downRequest.getIdList(), "文件id列表");

        } else {
            throw new ServiceException(ServiceExceptionEnum.DOWNLOAD_PARAM_ERROR);
        }
    }
}
