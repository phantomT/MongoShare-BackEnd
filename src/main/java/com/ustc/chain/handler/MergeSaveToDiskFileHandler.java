package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.DiskFile;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskFileDao;
import com.ustc.utils.FileType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 将文件信息保存到DiskFile表中
 * @author 田宝宁
 */
@Component
public class MergeSaveToDiskFileHandler extends Handler {

    @Autowired
    private DiskFileDao diskFileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;

            // 通过userName-pid-fileName-fileMd5查找文件是否存在于DiskFile表中
            DiskFile diskFile = diskFileDao.findFile(bean.getUserName(), bean.getPid(),
                    bean.getFileName(), bean.getFileMd5());
            bean.setExistInDiskFile(diskFile == null);

            // 如果不存在用户表中, 则存入表中
            if (diskFile == null) {
                diskFile = new DiskFile();
                diskFile.setPid(bean.getPid());
                diskFile.setFileMd5(bean.getFileMd5());
                diskFile.setFileName(bean.getFileName());
                diskFile.setFileSize(bean.getTotalSize());
                diskFile.setFileType(FileType.FILE.getTypeCode());
                diskFile.setFileSuffix(FilenameUtils.getExtension(bean.getFileName()));
                diskFile.setUserName(bean.getUserName());
                diskFile.setCreateTime(new Date());

                diskFileDao.insertOne(diskFile);
            }
            this.updateRequest(bean);
            System.out.println("diskFile保存时的Userid: "+diskFile.getUserName());
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
