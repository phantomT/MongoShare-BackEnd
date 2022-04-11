package com.ustc.chain.handler.Download;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.utils.FileZipUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 删除下载完成的文件
 *
 * @author 田宝宁
 * @date 2022/04/11
 */
@Component
public class DownloadClearBufferHandler extends Handler {

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException {
        if (request instanceof DownloadRequest) {
            // 类型转换
            DownloadRequest downRequest = (DownloadRequest) request;
            String filePath = downRequest.getFilePath();
            // 删除下载完成的文件
            File delPath = new File(filePath);
            String delParentPath = delPath.getParent();
            System.out.println("FilePath:" + filePath);
            System.out.println("ParentPath:" + delParentPath);
            if(!downRequest.getCompressFile()){
                FileZipUtils.delFile(delParentPath);
            }else{
                delPath.delete();
            }

            this.updateRequest(downRequest);
        } else {
            throw new ServiceException(ServiceExceptionEnum.DOWNLOAD_PARAM_ERROR);
        }
    }
}
