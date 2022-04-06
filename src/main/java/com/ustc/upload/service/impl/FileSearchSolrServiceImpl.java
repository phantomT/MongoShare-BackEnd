package com.ustc.upload.service.impl;

import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import com.ustc.upload.service.FileSearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 田宝宁
 * @date 2021/6/27
 */
@Component
public class FileSearchSolrServiceImpl implements FileSearchService {

    @Autowired
    @Qualifier("solrClient")
    private SolrClient solrClient;

    @Override
    public PageInfo<FileSearchBean> search(String filename, String userid, Integer page, Integer limit)
            throws Exception {
        PageInfo<FileSearchBean> pageInfo = new PageInfo<>();
        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(filename)) {
            query.set("pid", 0);
        } else {
            query.set("filename:", filename);
        }
        query.setFilterQueries("createuserid:" + userid);
        // 分页
        page = page == null ? 1 : page;
        limit = limit == null ? 10 : limit;
        int start = (page - 1) * limit;
        query.setStart(start);
        query.setRows(limit);

        // 按创建时间倒序输出
        query.setSort("createtime", SolrQuery.ORDER.desc);
        // 设置高亮
        query.setHighlight(true);
        query.addHighlightField("filename");
        query.setHighlightSimplePre("<span style=\"color:red;\">");
        query.setHighlightSimplePost("</span>");

        // 查询到的结果, mark一下
        QueryResponse response = solrClient.query(query);
        // 查询到的文档
        SolrDocumentList solrDocuments = response.getResults();
        // 记录数
        long numbers = solrDocuments.getNumFound();
        // 页数,向上取整
        long pageNum = (numbers % limit == 0) ? (numbers / limit) : (numbers / limit + 1);
        List<FileSearchBean> records = getRecords(solrDocuments, response);

        pageInfo.setPage(page);
        pageInfo.setLimit(limit);
        pageInfo.setRows(records);
        pageInfo.setTotalPage(pageNum);
        pageInfo.setTotalElements(numbers);

        return pageInfo;
    }

    @Override
    public void add(FileSearchBean bean) throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", bean.getId());
        document.addField("filename", bean.getFilename());
        document.addField("pid", bean.getPid());
        document.addField("pname", bean.getPname());
        document.addField("filemd5", bean.getFilemd5());
        document.addField("fileicon", bean.getFileicon());
        document.addField("typecode", bean.getTypecode());
        document.addField("filesuffix", bean.getFilesuffix());
        document.addField("filesize", bean.getFilesize());
        document.addField("filetype", bean.getFiletype());
        document.addField("createuserid", bean.getCreateuserid());
        document.addField("createusername", bean.getCreateusername());
        document.addField("createtime", bean.getCreatetime());

        solrClient.add(document);
        solrClient.commit();
    }

    @Override
    public void delete(String id) throws SolrServerException, IOException {
        solrClient.deleteById(id);
        solrClient.commit();
    }

    @Override
    public void deleteAll() throws SolrServerException, IOException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }

    @Override
    public Long findCount() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");

        QueryResponse response = solrClient.query(query);
        return response.getResults().getNumFound();
    }

    public List<FileSearchBean> getRecords(SolrDocumentList solrDocuments, QueryResponse response) {
        ArrayList<FileSearchBean> records = new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument solrDocument : solrDocuments) {
            String id = solrDocument.get("id") == null ? "" : solrDocument.get("id").toString();
            String pid = solrDocument.get("pid") == null ? "" : solrDocument.get("pid").toString();
            String pname = solrDocument.get("pname") == null ? "" : solrDocument.get("pname").toString();
            String filemd5 = solrDocument.get("filemd5") == null ? "" : solrDocument.get("filemd5").toString();
            String fileicon = solrDocument.get("fileicon") == null ? "" : solrDocument.get("fileicon").toString();
            String typecode = solrDocument.get("typecode") == null ? "" : solrDocument.get("typecode").toString();
            String filesuffix = solrDocument.get("filesuffix") == null ? "" : solrDocument.get("filesuffix").toString();
            String filesize = solrDocument.get("filesize") == null ? "" : solrDocument.get("filesize").toString();
            String filetype = solrDocument.get("filetype") == null ? "" : solrDocument.get("filetype").toString();
            String createuserid = solrDocument.get("createuserid") == null ? "" : solrDocument.get("createuserid").toString();
            String createusername = solrDocument.get("createusername") == null ? "" : solrDocument.get("createusername").toString();
            String createtime = solrDocument.get("createtime") == null ? "" : solrDocument.get("createtime").toString();

            List<String> list = highlighting.get(id).get("filename");
            String title;
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("filename");
            }
            FileSearchBean row = new FileSearchBean();
            row.setId(id);
            row.setFilename(title);
            row.setPid(pid);
            row.setPname(pname);
            row.setFilemd5(filemd5);
            row.setFileicon(fileicon);
            row.setTypecode(typecode);
            row.setFilesuffix(filesuffix);
            row.setFilesize(filesize);
            row.setFiletype(filetype);
            row.setCreateuserid(createuserid);
            row.setCreateusername(createusername);
            row.setCreatetime(createtime);

            records.add(row);
        }

        return records;
    }

}
