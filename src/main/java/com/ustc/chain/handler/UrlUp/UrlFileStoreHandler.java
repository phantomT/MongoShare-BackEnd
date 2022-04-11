package com.ustc.chain.handler.UrlUp;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.entity.DiskMd5;
import com.ustc.entity.DiskUrl;
import com.ustc.entity.UrlFileBean;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskMd5Dao;
import com.ustc.upload.dao.DiskUrlDao;
import com.ustc.upload.service.UploadStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 根据URL下载存储文件并计算MD5
 *
 * @author 田宝宁
 * @date 2022/04/06
 */
@Component
public class UrlFileStoreHandler extends Handler {

    @Autowired
    private UploadStoreService uploadStoreService;
    @Autowired
    private DiskUrlDao diskUrlDao;
    @Autowired
    private DiskMd5Dao diskMd5Dao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException {
        if (request instanceof UrlUploadRequest) {
            // 类型转换
            UrlUploadRequest urlUp = (UrlUploadRequest) request;
            if (!urlUp.getUrlExist()) {
                // 当URL记录不存在时将块上传
                UrlFileBean bean = uploadStoreService.uploadUrl(urlUp.getUrl(), urlUp.getFileName());
                // 设置urlUp的参数
                urlUp.setChunkList(bean.getChunkList());
                urlUp.setFileMd5(bean.getFileMd5());
                urlUp.setFileSuffix(bean.getFileSuffix());
                urlUp.setTotalSize(bean.getTotalSize());
                urlUp.setFileNum(bean.getChunkList().size());

                diskUrlDao.setMd5(urlUp.getUrl(), bean.getFileMd5());
            } else {
                DiskUrl diskUrl = diskUrlDao.findMd5(urlUp.getUrl());
                urlUp.setFileMd5(diskUrl.getFileMd5());
                DiskMd5 diskMd5 = diskMd5Dao.findOne(diskUrl.getFileMd5());
                urlUp.setTotalSize(diskMd5.getFileSize());
                urlUp.setFileSuffix(diskMd5.getFileSuffix());
                urlUp.setFileNum(diskMd5.getFileNum());
            }

            this.updateRequest(urlUp);
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
