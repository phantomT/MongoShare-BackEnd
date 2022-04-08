package com.ustc.upload.service;

import com.ustc.entity.Chunk;
import com.ustc.entity.MergeFileBean;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author 田宝宁
 * @date 2022/04/05
 */
public interface UploadFileService {
    /**
     * 上传切块
     * @param chunk 切块
     */
    void uploadChunk(Chunk chunk) throws SolrServerException, IOException;

    /**
     * 检查文件是否存在
     * @param fileMd5 文件的md5
     * @return 检查结果
     */
    Integer checkFile(String fileMd5);

    /**
     * 合并切块
     * @param bean  MergeFileBean
     */
    void mergeChunk(MergeFileBean bean) throws SolrServerException, IOException;

    /**
     * 使用责任链模式上传URL文件
     * @param userName    用户名
     * @param fileUrl   文件的Url
     * @param fileName  文件名
     * @param pid       父文件夹
     */
    void uploadUrlFile(String userName, String fileUrl, String fileName, String pid) throws IOException, InterruptedException;
}
