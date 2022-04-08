package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.ValidateUtils;
import org.springframework.stereotype.Component;

/**
 * 切块上传参数校验
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class UrlValidateHandler extends Handler {
    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof UrlUploadRequest) {
            UrlUploadRequest urlRequest = (UrlUploadRequest) request;

            ValidateUtils.validate(urlRequest.getUuid(), "uuid");
            ValidateUtils.validate(urlRequest.getFileName(), "文件名");
            ValidateUtils.validate(urlRequest.getUrl(), "url");
            ValidateUtils.validate(urlRequest.getPid(), "文件夹pid");
            ValidateUtils.validate(urlRequest.getUserName(), "用户名");

            System.out.println("URL上传验证完成");
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
