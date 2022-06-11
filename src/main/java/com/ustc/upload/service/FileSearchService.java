package com.ustc.upload.service;

import com.ustc.entity.FileListBean;
import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author 田宝宁 王博
 * @date 2022/06/11
 */
public interface FileSearchService {
    /**
     * 搜索文件
     *
     * @param page          当前页数（从1开始）
     * @param limit         分页记录数
     * @param keyWord       搜索关键词
     * @param searchField   搜索域
     * @param orderField    排序字段
     * @param orderType     排序类型
     * @return              本页所有文件信息
     */
    PageInfo<FileListBean> findPageList(Integer page, Integer limit, String keyWord, String searchField,
                                        String orderField, String orderType);

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
