package com.ustc.chain.handler.Download;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.download.dao.FileListDao;
import com.ustc.entity.DiskFile;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.fileCommon.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 根据下载文件的id列表获取MD5列表
 *
 * @author 田宝宁
 * @date 2022/04/09
 */
@Component
public class DownloadGetMd5Handler extends Handler {

    @Autowired
    private FileListDao fileListDao;
    @Autowired
    private FileDao fileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException {
        if (request instanceof DownloadRequest) {
            // 类型转换
            DownloadRequest downRequest = (DownloadRequest) request;
            List<String> md5List = new ArrayList<>();
            List<String> nameList = new ArrayList<>();
            List<String> idList = downRequest.getIdList();
            Queue<DiskFile> fileList = new LinkedList<>();
            for(String id : idList){
                DiskFile diskFile = fileListDao.findDiskFileById(id);
                fileList.offer(diskFile);
            }
            while(!fileList.isEmpty()){
                DiskFile diskFile = fileList.element();
                if(diskFile.getFileType().equals(0)){
                    for (DiskFile df : fileDao.findFiles(diskFile.getId().toString())) {
                        fileList.offer(df);
                    }
                }else{
                    String md5 = diskFile.getFileMd5();
                    String name = diskFile.getFileName() + "-" + diskFile.getUserName();
                    md5List.add(md5);
                    nameList.add(name);
                }
                fileList.remove();
            }
            // 将ID转换为MD5
            downRequest.setMd5List(md5List);
            downRequest.setNameList(nameList);

            // 修改是否压缩的记录成员
            downRequest.setCompressFile(md5List.size() > 1);

            this.updateRequest(downRequest);
        } else {
            throw new ServiceException(ServiceExceptionEnum.DOWNLOAD_PARAM_ERROR);
        }
    }
}
