package com.ustc.upload.service.impl;

import com.ustc.config.StoreConfiguration;
import com.ustc.entity.UrlFileBean;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.service.UploadStoreService;
import com.ustc.utils.FileUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 田宝宁
 * @date 2022/04/05
 */
@Component
public class StoreServiceImpl implements UploadStoreService {

    @Autowired
    private StoreConfiguration storeConfiguration;

    @Override
    public String upload(byte[] bytes, String filename) {
        // 获取存储路径
        String storePath = storeConfiguration.getStorePath();
        // 获取存储文件夹名称
        String folders = FileUtils.getFolder();
        // 获取存储在文件系统中的新文件名
        String newFilename = UUID.randomUUID() + "." + FilenameUtils.getExtension(filename);
        // 将文件存入服务器中
        File file = new File(storePath + "/" + folders + "/" + newFilename);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs()) {
                throw new ServiceException(ServiceExceptionEnum.FOLDER_CREATE_FAIL);
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copyLarge(inputStream, outputStream);
            // 关闭流
            inputStream.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return folders + "/" + newFilename;
    }

    @Override
    public UrlFileBean uploadUrl(String fileUrl, String fileName) throws IOException, InterruptedException {
        // 获取存储路径
        String storePath = storeConfiguration.getStorePath();
        // 获取存储文件夹名称
        String folders = FileUtils.getFolder();
        // 定义UrlFileBean保存返回数据
        UrlFileBean bean = new UrlFileBean(fileUrl);
        // 保存bean的文件名
        bean.setFileName(fileName);

        // 设置分块大小
        int blockSize = 1024 * 1024;

        URL upUrl = new URL(fileUrl);
        // 获取连接
        URLConnection connection = upUrl.openConnection();
        // 获取文件全路径
        String fileUrlPath = upUrl.getFile();
        // 获取文件后缀
        String fileSuffix = fileUrlPath.substring(fileUrlPath.lastIndexOf(".")+1);
        // 保存文件后缀
        bean.setFileSuffix(fileSuffix);
        // 获取文件大小
        int fileSize = connection.getContentLength();
        // 保存文件大小
        bean.setFilesize(fileSize);
        System.out.println("文件总共大小：" + fileSize + "字节");
        // 文件分块的数量
        int blockNum = fileSize / blockSize;
        if ((fileSize % blockSize) != 0) {
            blockNum += 1;
        }
        System.out.println("分块数->线程数：" + blockNum);

        // 创建保留分块存储路径的List
        List<String> pathList = new ArrayList<>();
        for(int i = 0; i < blockNum; i++){
            pathList.add("shit");
        }

        Thread[] threads = new Thread[blockNum];
        for (int i = 0; i < blockNum; i++) {
            // 匿名函数对象需要用到的变量
            final int index = i;
            final int finalBlockNum = blockNum;
            // 获取存储在文件系统中的新文件名
            final String chunkFileName = UUID.randomUUID().toString();

            // 创建一个线程
            threads[i] = new Thread(() -> {
                try {
                    // 重新获取连接
                    URLConnection conn = upUrl.openConnection();
                    // 重新获取流
                    InputStream in = conn.getInputStream();
                    // 定义起始和结束点
                    int beginPoint, endPoint;

                    System.out.print("第" + (index + 1) + "块文件：");

                    beginPoint = index * blockSize;

                    // 判断结束点
                    if (index < finalBlockNum - 1) {
                        endPoint = beginPoint + blockSize;
                    } else {
                        endPoint = fileSize;
                    }

                    System.out.println("起始字节数：" + beginPoint + ",结束字节数：" + endPoint);

                    // 将下载的文件存储到一个文件夹中
                    // 当该文件夹不存在时，则新建
                    File dirPath = new File(storePath + "/" + folders + "/");
                    if (!dirPath.exists()) {
                        if (!dirPath.mkdirs()) {
                            throw new ServiceException(ServiceExceptionEnum.FOLDER_CREATE_FAIL);
                        }
                    }

                    FileOutputStream fos = new FileOutputStream(
                            new File(storePath + "/" + folders + "/",
                                    chunkFileName + "." + fileSuffix));

                    pathList.set(index, folders + "/" + chunkFileName + "." + fileSuffix);

                    // 跳过 beginPoint个字节进行读取
                    in.skip(beginPoint);
                    byte[] buffer = new byte[1024];
                    int count;
                    // 定义当前下载进度
                    int process = beginPoint;
                    // 当前进度必须小于结束字节数
                    while (process < endPoint) {
                        count = in.read(buffer);
                        // 判断是否读到最后一块
                        if (process + count >= endPoint) {
                            count = endPoint - process;
                            process = endPoint;
                        } else {
                            // 计算当前进度
                            process += count;
                        }
                        // 保存文件流
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        // 当所有线程都结束时才开始文件的合并
        for (Thread t : threads) {
            t.join();
        }

        // 若该文件夹不存在，则创建一个文件夹
        File filePath = new File(storePath + "/checkMd5/");
        if (!filePath.exists()) {
            if (!filePath.mkdirs()) {
                throw new ServiceException(ServiceExceptionEnum.FOLDER_CREATE_FAIL);
            }
        }
        // 定义文件输出流
        FileOutputStream fos = new FileOutputStream(storePath + "/checkMd5/" + fileName + "." + fileSuffix);
        for (int i = 0; i < blockNum; i++) {
            FileInputStream fis = new FileInputStream(storePath + "/" + pathList.get(i));
            byte[] buffer = new byte[1024];
            int count;
            while ((count = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fis.close();
        }
        fos.close();

        // 获取合成文件的MD5
        String Md5 = null;
        try {
            FileInputStream fis = new FileInputStream(storePath + "/checkMd5/" + fileName + "." + fileSuffix);
            Md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
            IOUtils.closeQuietly(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bean.setChunkList(pathList);
        bean.setFileMd5(Md5);

        // 将验证过的合并文件删除
        File removeFile = new File(storePath + "/checkMd5/" + fileName + "." + fileSuffix);
        removeFile.delete();

        return bean;
    }
}
