package com.ustc.chain.handler;

import com.ustc.chain.core.ContextRequest;
import com.ustc.chain.core.ContextResponse;
import com.ustc.chain.core.Handler;
import com.ustc.chain.param.MergeRequest;
import com.ustc.entity.DiskFile;
import com.ustc.entity.FileSearchBean;
import com.ustc.exception.ServiceException;
import com.ustc.exception.ServiceExceptionEnum;
import com.ustc.upload.dao.DiskFileDao;
import com.ustc.upload.service.FileSearchService;
import com.ustc.utils.CapacityUtils;
import com.ustc.utils.FileType;
import com.ustc.utils.JsonUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author 王博
 * @date 2022/04/07
 */
public class MergeSolrHandler extends Handler {

    @Autowired
    private FileSearchService fileSearchService;

    @Autowired
    private DiskFileDao diskFileDao;

    @Override
    public void doHandler(ContextRequest request, ContextResponse response) throws IOException {
        if (request instanceof MergeRequest) {
            MergeRequest bean = (MergeRequest) request;

            // 对文件夹的操作
            if (!CollectionUtils.isEmpty(bean.getFolders())) {
                int size = bean.getFolders().size();

                for (int i = 0; i < size; i++) {
                    DiskFile diskFile = bean.getFolders().get(i);
                    List<Map<String, Object>> lists = new ArrayList<>();
                    // 将每个文件夹都加入其父文件夹上
                    if (i > 0) {
                        for (int y = 0; y < i; y++) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", bean.getFolders().get(y).getId());
                            map.put("name", bean.getFolders().get(y).getFileName());
                            lists.add(map);
                        }
                    }
                    String pName = JsonUtils.objectToJson(lists);

                    FileSearchBean fileSearchBean = new FileSearchBean();
                    fileSearchBean.setId(String.valueOf(diskFile.getId()));
                    fileSearchBean.setFileName(diskFile.getFileName());
                    fileSearchBean.setPid(diskFile.getPid());
                    fileSearchBean.setFileMd5(diskFile.getFileMd5());
                    fileSearchBean.setPName(pName);
                    fileSearchBean.setFileMd5("");
                    fileSearchBean.setFileIcon("");
                    fileSearchBean.setTypeCode("");
                    fileSearchBean.setFileSuffix("");
                    fileSearchBean.setFileSize("");
                    fileSearchBean.setFileType(String.valueOf(FileType.FOLDER.getTypeCode()));
                    fileSearchBean.setCreateUserName(diskFile.getUserName());
                    fileSearchBean.setCreateTime(DateUtils.formatDate(diskFile.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

                    try {
                        fileSearchService.add(fileSearchBean);
                    } catch (SolrServerException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 对文件的操作
            if (!bean.isExistInDiskFile()) {
                //获取pName
                List<Map<String,Object>> lists=new ArrayList<>();
                getPName(lists, bean.getPid());
                String pName=JsonUtils.objectToJson(lists);

                FileSearchBean fileSearchBean=new FileSearchBean();
                fileSearchBean.setId(bean.getFileId());
                fileSearchBean.setFileName(bean.getFileName());
                fileSearchBean.setPid(bean.getPid());
                fileSearchBean.setPName(pName);
                fileSearchBean.setFileMd5(bean.getFileMd5());
                fileSearchBean.setFileIcon("");
                fileSearchBean.setTypeCode("");
                fileSearchBean.setFileSuffix(bean.getFileSuffix());
                fileSearchBean.setFileSize(CapacityUtils.convert(bean.getTotalSize()));
                fileSearchBean.setFileType(String.valueOf(FileType.FILE.getTypeCode()));
                fileSearchBean.setCreateUserName(bean.getUserName());
                fileSearchBean.setCreateTime(DateUtils.formatDate(new Date()));
                try {
                    fileSearchService.add(fileSearchBean);
                } catch (SolrServerException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new ServiceException(ServiceExceptionEnum.UPLOAD_PARAM_ERROR);
        }
    }


    /**
     * 递归获取pName
     * @param lists lists
     * @param pid   pid
     */
    private void getPName(List<Map<String, Object>> lists, String pid) {
        if (!"0".equals(pid)) {
            DiskFile diskFile = diskFileDao.findFileById(new ObjectId(pid));
            Map<String, Object> map = new HashMap<>();
            map.put("id", diskFile.getId());
            map.put("name", diskFile.getFileName());
            lists.add(map);
            getPName(lists, diskFile.getPid());
        }
    }
}
