package com.ustc.upload.service;

import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author 田宝宁
 * @date 2021/6/27
 */
public interface FileSearchService {
    /**
     * 查找文件
     *
     * @param filename 文件名
     * @param userid   用户id
     * @param page     页数
     * @param limit    每页记录数
     * @return 文件
     */
    PageInfo<FileSearchBean> search(String filename, String userid, Integer page, Integer limit) throws Exception;

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
