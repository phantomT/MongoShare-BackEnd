package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.entity.DiskUrl;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskUrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检验URL是否存在
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class UrlIsExistHandler extends Handler {

    @Autowired
    DiskUrlDao diskUrlDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof UrlUploadRequest) {
            UrlUploadRequest bean = (UrlUploadRequest) request;

            boolean isExist = diskUrlDao.findUrlIsExist(bean.getUrl());
            bean.setUrlExist(isExist);

            // 当URL未记录时添加记录
            if(!isExist){
                DiskUrl diskUrl = new DiskUrl();
                diskUrl.setFileUrl(bean.getUrl());
                diskUrlDao.insertUrl(diskUrl);
            }

            this.updateRequest(bean);

            System.out.println("URL存在性检测完成"+isExist);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
