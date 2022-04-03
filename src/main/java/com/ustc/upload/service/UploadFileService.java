package com.ustc.upload.service;

import com.ustc.entity.Chunk;
import com.ustc.entity.MergeFileBean;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author 田宝宁
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
     * @param bean
     */
    void mergeChunk(MergeFileBean bean) throws SolrServerException, IOException;
}
