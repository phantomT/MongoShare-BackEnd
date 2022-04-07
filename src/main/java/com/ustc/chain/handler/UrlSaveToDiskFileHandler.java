package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.UrlUploadRequest;
import com.ustc.entity.DiskFile;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskFileDao;
import com.ustc.utils.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 根据URL上传的文件信息修改DiskFile表
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class UrlSaveToDiskFileHandler extends Handler {

    @Autowired
    private DiskFileDao diskFileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof UrlUploadRequest) {
            UrlUploadRequest bean = (UrlUploadRequest) request;

            // 通过userid-pid-filename-fileMd5查找文件是否存在于DiskFile表中
            DiskFile diskFile = diskFileDao.findFile(bean.getUserId(), bean.getPid(),
                    bean.getFileName(), bean.getFileMd5());

            // 如果不存在用户表中, 则存入表中
            if (diskFile == null) {
                diskFile = new DiskFile();
                diskFile.setPid(bean.getPid());
                diskFile.setFileMd5(bean.getFileMd5());
                diskFile.setFileName(bean.getFileName());
                diskFile.setFileSize(bean.getTotalSize());
                diskFile.setFileType(FileType.FILE.getTypeCode());
                diskFile.setFileSuffix(bean.getFileSuffix());
                diskFile.setUserid(bean.getUserId());
                diskFile.setCreateTime(new Date());

                diskFileDao.insertOne(diskFile);
            } else {
                DiskFile diskFileNew;
                diskFileNew = diskFile;
                diskFileNew.setFileName(bean.getFileName() + "+1." + bean.getFileSuffix());
                diskFileDao.insertOne(diskFileNew);
            }
            this.updateRequest(bean);
            System.out.println("diskFile保存时的Userid: " + diskFile.getUserid());
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
