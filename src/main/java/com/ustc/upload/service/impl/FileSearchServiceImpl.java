package com.ustc.upload.service.impl;

import com.ustc.download.dao.FileListDao;
import com.ustc.download.service.FileService;
import com.ustc.entity.DiskFile;
import com.ustc.entity.FileListBean;
import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import com.ustc.upload.service.FileSearchService;
import com.ustc.utils.CapacityUtils;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 田宝宁 王博
 * @date 2022/06/11
 */
@Component
public class FileSearchServiceImpl implements FileSearchService {

    @Autowired
    @Qualifier("solrClient")
    private SolrClient solrClient;
    @Autowired
    private FileListDao fileListDao;
    @Autowired
    private FileService fileService;

//    @Override
//    public PageInfo<FileSearchBean> search(String fileName, String userName, Integer page, Integer limit)
//            throws Exception {
//        PageInfo<FileSearchBean> pageInfo = new PageInfo<>();
//        SolrQuery query = new SolrQuery();
//        if (StringUtils.isEmpty(fileName)) {
//            query.set("pid", 0);
//        } else {
//            query.set("fileName:", fileName);
//        }
//        query.setFilterQueries("createUserName:" + userName);
//        // 分页
//        page = page == null ? 1 : page;
//        limit = limit == null ? 10 : limit;
//        int start = (page - 1) * limit;
//        query.setStart(start);
//        query.setRows(limit);
//
//        // 按创建时间倒序输出
//        query.setSort("createTime", SolrQuery.ORDER.desc);
//        // 设置高亮
//        query.setHighlight(true);
//        query.addHighlightField("fileName");
//        query.setHighlightSimplePre("<span style=\"color:red;\">");
//        query.setHighlightSimplePost("</span>");
//
//        // 查询到的结果, mark一下
//        QueryResponse response = solrClient.query(query);
//        // 查询到的文档
//        SolrDocumentList solrDocuments = response.getResults();
//        // 记录数
//        long numbers = solrDocuments.getNumFound();
//        // 页数,向上取整
//        long pageNum = (numbers % limit == 0) ? (numbers / limit) : (numbers / limit + 1);
//        List<FileSearchBean> records = getRecords(solrDocuments, response);
//
//        pageInfo.setPage(page);
//        pageInfo.setLimit(limit);
//        pageInfo.setRows(records);
//        pageInfo.setTotalPage(pageNum);
//        pageInfo.setTotalElements(numbers);
//
//        return pageInfo;
//    }

    @Override
    public PageInfo<FileListBean> findPageList(Integer pageIndex, Integer pageSize, String keyWord, String searchField,
                                               String orderField, String orderType){
        PageInfo<FileListBean> pageInfo = new PageInfo<>();

        List<DiskFile> page = new ArrayList<>();
        List<DiskFile> all = new ArrayList<>();

        if(Objects.equals(searchField, "+id")){
            page = fileListDao.findPageByWordWithSort(keyWord, orderField, pageIndex, pageSize);
            all = fileListDao.findDiskFileByWord(keyWord);
        }else if (Objects.equals(searchField, "-id")){
            page = fileListDao.findPageByTypeWithSort(keyWord, orderField, pageIndex, pageSize);
            all = fileListDao.findDiskFileByType(keyWord);
        }

        ArrayList<FileListBean> rows = new ArrayList<>();

        for (DiskFile diskFile : page) {
            FileListBean fileListBean = new FileListBean();

            fileListBean.setId(diskFile.getId().toString());
            fileListBean.setPid(diskFile.getPid());
            if (!"0".equals(diskFile.getPid())) {
                fileListBean.setPName(fileService.findOne(diskFile.getPid()).getFilename());
            } else {
                fileListBean.setPName("");
            }
            fileListBean.setFileName(diskFile.getFileName());
            fileListBean.setFileSize(diskFile.getFileSize());
            fileListBean.setFileSizeName(CapacityUtils.convert(diskFile.getFileSize()));
            fileListBean.setFileSuffix(diskFile.getFileSuffix());
            fileListBean.setFileMd5(diskFile.getFileMd5());
            fileListBean.setFileType(diskFile.getFileType());
            fileListBean.setCreateUserName(diskFile.getUserName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            fileListBean.setCreateTime(simpleDateFormat.format(diskFile.getCreateTime()));

            rows.add(fileListBean);
        }
        pageInfo.setTotalPage((all.size() % pageSize) == 0
                ? (long) (all.size() / pageSize)
                : (long) (all.size() / pageSize + 1));
        pageInfo.setPage(pageIndex);
        pageInfo.setLimit(pageSize);
        pageInfo.setTotalElements((long) all.size());
        pageInfo.setRows(rows);
        return pageInfo;
    }

    @Override
    public void add(FileSearchBean bean) throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", bean.getId());
        document.addField("filename", bean.getFileName());
        document.addField("pid", bean.getPid());
        document.addField("pName", bean.getPName());
        document.addField("fileMd5", bean.getFileMd5());
        document.addField("fileIcon", bean.getFileIcon());
        document.addField("typeCode", bean.getTypeCode());
        document.addField("fileSuffix", bean.getFileSuffix());
        document.addField("fileSize", bean.getFileSize());
        document.addField("fileType", bean.getFileType());
        document.addField("createUserName", bean.getCreateUserName());
        document.addField("createTime", bean.getCreateTime());

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
            String pName = solrDocument.get("pName") == null ? "" : solrDocument.get("pName").toString();
            String fileMd5 = solrDocument.get("fileMd5") == null ? "" : solrDocument.get("fileMd5").toString();
            String fileIcon = solrDocument.get("fileIcon") == null ? "" : solrDocument.get("fileIcon").toString();
            String typeCode = solrDocument.get("typeCode") == null ? "" : solrDocument.get("typeCode").toString();
            String fileSuffix = solrDocument.get("fileSuffix") == null ? "" : solrDocument.get("fileSuffix").toString();
            String fileSize = solrDocument.get("fileSize") == null ? "" : solrDocument.get("fileSize").toString();
            String fileType = solrDocument.get("fileType") == null ? "" : solrDocument.get("fileType").toString();
            String createUserName = solrDocument.get("createUserName") == null ? "" : solrDocument.get("createUserName").toString();
            String createTime = solrDocument.get("createTime") == null ? "" : solrDocument.get("createTime").toString();

            List<String> list = highlighting.get(id).get("filename");
            String title;
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("filename");
            }
            FileSearchBean row = new FileSearchBean();
            row.setId(id);
            row.setFileName(title);
            row.setPid(pid);
            row.setPName(pName);
            row.setFileMd5(fileMd5);
            row.setFileIcon(fileIcon);
            row.setTypeCode(typeCode);
            row.setFileSuffix(fileSuffix);
            row.setFileSize(fileSize);
            row.setFileType(fileType);
            row.setCreateUserName(createUserName);
            row.setCreateTime(createTime);

            records.add(row);
        }

        return records;
    }

}
