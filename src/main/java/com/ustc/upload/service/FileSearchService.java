package com.ustc.upload.service;

import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author 王博
 * @date 2022/03/07
 */
public interface FileSearchService {
    /**
     * 查找文件
     *
     * @param fileName  文件名
     * @param userName  用户名
     * @param page      页数
     * @param limit     每页记录数
     * @return 文件
     */
    PageInfo<FileSearchBean> search(String fileName, String userName, Integer page, Integer limit) throws Exception;

    /**
     * 添加一项记录
     *
     * @param bean 查找bean
     * @throws SolrServerException Solr异常
     * @throws IOException IO异常
     */
    void add(FileSearchBean bean) throws SolrServerException, IOException;

    /**
     * 删除
     * @param id 文件id
     * @throws SolrServerException Solr异常
     * @throws IOException IO异常
     */
    void delete(String id) throws SolrServerException, IOException;

    /**
     * 删除全部
     * @throws SolrServerException Solr异常
     * @throws IOException IO异常
     */
    void deleteAll() throws SolrServerException, IOException;

    /**
     * 记录数
     * @return 记录数
     * @throws SolrServerException Solr异常
     * @throws IOException IO异常
     */
    Long findCount() throws SolrServerException, IOException;
}
