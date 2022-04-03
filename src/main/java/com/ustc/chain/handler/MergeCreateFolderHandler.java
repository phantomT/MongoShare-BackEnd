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
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 判断是否为文件夹上传, 如果是文件夹上传则会创建文件夹
 */
@Component
public class MergeCreateFolderHandler extends Handler {

    @Autowired
    private DiskFileDao diskFileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;
            String relativePath = bean.getRelativepath();

            if (!StringUtils.isEmpty(relativePath)) {
                String[] names = relativePath.split("/");
                String userid = bean.getUserid();
                String pid = bean.getPid();

                List<DiskFile> folders = new ArrayList<>();
                // 为每一个路径创建一个文件夹, 最后一个为文件名, 不包括在内
                for (int i = 0; i < names.length - 1; i++) {
                    String name = names[i];
                    // 先判断是否存在该文件夹
                    DiskFile diskFile = diskFileDao.findFolder(userid, pid, name);

                    // 如果不存在该文件夹, 则创建该文件夹, 否则不需要创建
                    if (diskFile == null) {
                        diskFile = new DiskFile();
                        diskFile.setFileType(FileType.FOLDER.getTypeCode());
                        diskFile.setFileName(name);
                        diskFile.setPid(pid);
                        diskFile.setUserid(userid);
                        diskFile.setFileSize(0L);
                        diskFile.setCreateTime(new Date());
                        // 给文件夹设置个随机object-id
                        diskFile.setId(ObjectId.get());
                        diskFileDao.insertOne(diskFile);
                    }

                    // 将当前文件夹的id设置为新的pid
                    pid = String.valueOf(diskFile.getId());
                    System.out.println("文件夹id:" + pid);
                    // 将对应的文件夹对象加入到
                    folders.add(diskFile);
                }
                // 更新当前文件的pid
                bean.setPid(pid);
                bean.setFolders(folders);
                this.updateRequest(bean);

            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }
}
