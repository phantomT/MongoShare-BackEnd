package com.ustc.chain.handler.Download;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 使用httpResponse传输文件
 *
 * @author 田宝宁
 * @date 2022/04/11
 */
@Component
public class DownloadResponseHandler extends Handler {

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException, InterruptedException {
        if (request instanceof DownloadRequest) {
            // 类型转换
            DownloadRequest downRequest = (DownloadRequest) request;
            HttpServletResponse httpResponse = downRequest.getResponse();
            String filePath = downRequest.getFilePath();
            InputStream inputStream = new FileInputStream(filePath);
            String fileName = downRequest.getFileName();
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            httpResponse.setHeader("Access-Control-Allow-Origin","*");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
            httpResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            httpResponse.setContentType("application/octet-stream");
            ServletOutputStream outputStream = httpResponse.getOutputStream();
            try{
                byte[] bytes = new byte[1024 * 1024];
                int len;
                while((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                outputStream.flush();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try{
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            downRequest.setResponse(httpResponse);

            this.updateRequest(downRequest);
        } else {
            throw new ServiceException(ServiceExceptionEnum.DOWNLOAD_PARAM_ERROR);
        }
    }
}
