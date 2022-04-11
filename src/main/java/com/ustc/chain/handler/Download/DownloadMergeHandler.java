package com.ustc.chain.handler.Download;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.config.StoreConfiguration;
import com.ustc.download.dao.FileListDao;
import com.ustc.download.service.FileService;
import com.ustc.entity.DiskMd5;
import com.ustc.entity.DiskMd5Chunk;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskMd5Dao;
import com.ustc.utils.FileZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 合并需要下载的文件并根据情况压缩
 *
 * @author 田宝宁
 * @date 2022/04/09
 */
@Component
public class DownloadMergeHandler extends Handler {

    @Autowired
    private DiskMd5Dao diskMd5Dao;
    @Autowired
    private FileListDao fileListDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private StoreConfiguration storeConfiguration;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException {
        if (request instanceof DownloadRequest) {
            // 类型转换
            DownloadRequest downRequest = (DownloadRequest) request;
            List<String> md5List = downRequest.getMd5List();
            List<String> nameList = downRequest.getNameList();
            List<String> downList = new ArrayList<>();
            // 获取存储路径
            String storePath = storeConfiguration.getStorePath();
            // 每个请求单独使用一个文件夹，使用UUID作为文件夹名
            String uuid = UUID.randomUUID().toString();
            for(int i = 0; i < md5List.size(); i++){
                String md5 = md5List.get(i);
                String fileName = nameList.get(i);
                DiskMd5 diskMd5 = diskMd5Dao.findOne(md5);
                String fileSuffix = diskMd5.getFileSuffix();
                List<DiskMd5Chunk> fileChunks = fileListDao.findDiskChunkByMd5(md5);
                List<String> pathList = new ArrayList<>();
                for(int j = 0; j < diskMd5.getFileNum(); j++){
                    pathList.add("shit");
                }
                for(DiskMd5Chunk chunk : fileChunks){
                    pathList.set(chunk.getChunkNumber(), chunk.getStorePath());
                }
                // 若该文件夹不存在，则创建一个文件夹
                File filePath = new File(storePath + "/downloadMerge/" + uuid + "/");
                if (!filePath.exists()) {
                    if (!filePath.mkdirs()) {
                        throw new ServiceException(ServiceExceptionEnum.FOLDER_CREATE_FAIL);
                    }
                }
                // 定义文件输出流
                FileOutputStream fos = new FileOutputStream(storePath + "/downloadMerge/" +
                        uuid + "/" + fileName + "." + fileSuffix);
                for (int j = 0; j < diskMd5.getFileNum(); j++) {
                    fileService.downloadToPath(storePath + "/" + pathList.get(j), fos);
                }
                fos.close();

                downList.add(storePath + "/downloadMerge/" + uuid + "/" + fileName + "." + fileSuffix);
            }
            if(md5List.size() == 1){
                downRequest.setFilePath(downList.get(0));
                downRequest.setCompressFile(false);
            }else{
                // 如果不是单文件则要压缩
                String zipPath = storePath + "/downloadMerge/" + uuid + "." + downRequest.getFileSuffix();
                FileZipUtils.fileToZip(storePath + "/downloadMerge/"+ uuid + "/", zipPath);
                downRequest.setFilePath(zipPath);
            }

            this.updateRequest(downRequest);
        } else {
            throw new ServiceException(ServiceExceptionEnum.DOWNLOAD_PARAM_ERROR);
        }
    }
}
