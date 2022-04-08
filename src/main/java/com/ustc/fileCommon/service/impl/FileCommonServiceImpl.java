package com.ustc.fileCommon.service.impl;

import com.ustc.chain.core.HandlerInitializer;
import com.ustc.chain.core.Pipeline;
import com.ustc.chain.core.ResponsibleChain;
import com.ustc.chain.handler.*;
import com.ustc.chain.param.DeleteRequest;
import com.ustc.chain.param.MoveRequest;
import com.ustc.chain.param.RenameRequest;
import com.ustc.entity.DiskFile;
import com.ustc.entity.FileListBean;
import com.ustc.fileCommon.dao.FileDao;
import com.ustc.fileCommon.service.FileCommonService;
import com.ustc.utils.CapacityUtils;
import com.ustc.utils.SpringContentUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/01
 */
@Service
public class FileCommonServiceImpl implements FileCommonService {

    @Autowired
    FileDao fileDao;

    @Resource(name = "springContentUtils")
    SpringContentUtils scu;

    @Override
    public List<FileListBean> findFolderList(String userName, String pid, List<String> idList) {
        List<DiskFile> folders = fileDao.findFolders(pid, idList);
        List<FileListBean> folderList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(folders)) {
            for (DiskFile diskFile : folders) {
                FileListBean fileListBean = new FileListBean();
                fileListBean.setId(diskFile.getId().toString());
                fileListBean.setPid(diskFile.getPid());
                fileListBean.setFileName(diskFile.getFileName());
                fileListBean.setCreateUserName(diskFile.getUserName());
                fileListBean.setCreateTime(DateUtils.formatDate(diskFile.getCreateTime(), "yyyy-MM-dd HH:mm"));
                // 将文件夹bean加入列表中
                folderList.add(fileListBean);
            }
        }
        return folderList;
    }

    @Override
    public void move(String userName, String pid, List<String> idList) throws IOException {
        // 创建移动职责链
        ResponsibleChain responsibleChain = new ResponsibleChain();
        MoveRequest moveRequest = new MoveRequest(userName, pid, idList);

        responsibleChain.loadHandler(new HandlerInitializer(moveRequest, null) {
            @Override
            protected void initChannel(Pipeline line) {
                // 1. 参数校验
                line.addLast(scu.getHandler(MoveValidateHandler.class));
                // 2. 移动
                line.addLast(scu.getHandler(MoveUpdateHandler.class));
            }
        });
        responsibleChain.execute();
    }

    @Override
    public void rename(String userName, String id, String newName) throws IOException {
        // 创建重命名职责链
        ResponsibleChain responsibleChain = new ResponsibleChain();
        RenameRequest renameRequest = new RenameRequest(userName, id, newName);

        responsibleChain.loadHandler(new HandlerInitializer(renameRequest, null) {
            @Override
            protected void initChannel(Pipeline line) {
                // 1. 参数校验
                line.addLast(scu.getHandler(RenameValidateHandler.class));
                // 2. 检查是否存在同名记录
                line.addLast(scu.getHandler(RenameIsExistHandler.class));
                // 3. 重命名, 即修改数据库记录
                line.addLast(scu.getHandler(RenameUpdateHandler.class));
            }
        });
        // 执行职责链
        responsibleChain.execute();
    }

    @Override
    public FileListBean findOneRecord(String userName, String id) {
        DiskFile diskFile = fileDao.findOne(id);
        FileListBean fileListBean = new FileListBean();
        fileListBean.setFileType(diskFile.getFileType());
        fileListBean.setFileSuffix(diskFile.getFileSuffix());
        fileListBean.setId(diskFile.getId().toString());
        fileListBean.setPid(diskFile.getPid());
        fileListBean.setFileName(diskFile.getFileName());
        fileListBean.setFileSize(diskFile.getFileSize());
        fileListBean.setFileSizeName(CapacityUtils.convert(diskFile.getFileSize()));
        fileListBean.setCreateUserName(diskFile.getUserName());
        fileListBean.setFileMd5(diskFile.getFileMd5());
        fileListBean.setCreateTime(DateUtils.formatDate(diskFile.getCreateTime(), "yyyy-MM-dd mm:ss"));
        return fileListBean;
    }

    @Override
    public void delete(String userName, List<String> idList) throws IOException {
        // 创建删除职责链
        ResponsibleChain responsibleChain = new ResponsibleChain();
        DeleteRequest deleteRequest = new DeleteRequest(userName, idList);

        responsibleChain.loadHandler(new HandlerInitializer(deleteRequest, null) {
            @Override
            protected void initChannel(Pipeline line) {
                // 1. 参数校验
                line.addLast(scu.getHandler(DeleteValidateHandler.class));
                // 2. 删除
                line.addLast(scu.getHandler(DeleteUpdateHandler.class));
            }
        });
        responsibleChain.execute();
    }
}
