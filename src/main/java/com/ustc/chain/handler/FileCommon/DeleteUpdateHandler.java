package com.ustc.chain.handler.FileCommon;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DeleteRequest;
import com.ustc.entity.DiskFile;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.fileCommon.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 田宝宁
 * @date 2022/04/01
 */
@Component
public class DeleteUpdateHandler extends Handler {

    @Autowired
    FileDao fileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof DeleteRequest) {
            DeleteRequest deleteRequest = (DeleteRequest) request;
            Queue<DiskFile> delList = new LinkedList<>();
            // 依次处理每个记录
            for (String id : deleteRequest.getIdList()) {
                // 根据id从数据库中取出相应要被删除的记录
                DiskFile record = fileDao.findOne(id);
                delList.offer(record);
            }
            while (!delList.isEmpty()) {
                DiskFile record = delList.element();

                if (record.getFileType().equals(0)) {
                    // 如果该文件是文件夹
                    for (DiskFile df : fileDao.findFiles(record.getId().toString())) {
                        delList.offer(df);
                    }
                    fileDao.removeFolder(record.getPid(), record.getId());
                } else {
                    // 如果该文件不是文件夹
                    fileDao.removeDiskFile(record.getUserName(), record.getId());
                    fileDao.removeDiskMd5(record.getFileMd5());
                    fileDao.removeDiskUrl(record.getFileMd5());
                }
                delList.remove();
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.MOVE_PARAM_ERROR);
        }
    }
}
