package com.ustc.download.service.impl;

import com.ustc.chain.core.HandlerInitializer;
import com.ustc.chain.core.Pipeline;
import com.ustc.chain.core.ResponsibleChain;
import com.ustc.chain.handler.Download.*;
import com.ustc.chain.param.DownloadRequest;
import com.ustc.download.dao.FileListDao;
import com.ustc.download.service.FileService;
import com.ustc.entity.*;
import com.ustc.utils.CapacityUtils;
import com.ustc.utils.SpringContentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/08
 */
@Component
public class FileServiceImpl implements FileService {

    @Autowired
    private SpringContentUtils scu;
    @Autowired
    private FileListDao fileListDao;

    /**
     * 文件列表分页
     * @param pageIndex     当前页号
     * @param pageSize      每页记录数
     * @param pid           父文件夹id
     * @param typeCode      文件类型码
     * @param orderField    orderField
     * @param orderType     orderType
     * @return 本页所有文件信息
     */
    @Override
    public PageInfo<FileListBean> findPageList(Integer pageIndex, Integer pageSize, String pid, String typeCode, String orderField, String orderType) {
        PageInfo<FileListBean> pageInfo = new PageInfo<>();

        List<DiskFile> page = fileListDao.findPageWithSort(pid, orderField, pageIndex, pageSize);
        List<DiskFile> all = fileListDao.findDiskFileByPid(pid);

        ArrayList<FileListBean> rows = new ArrayList<>();

        for (DiskFile diskFile : page) {
            FileListBean fileListBean = new FileListBean();

            fileListBean.setId(diskFile.getId().toString());
            fileListBean.setPid(diskFile.getPid());
            if (!"0".equals(diskFile.getPid())) {
                fileListBean.setPName(findOne(diskFile.getPid()).getFilename());
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
        pageInfo.setTotalPage((all.size() % pageSize) == 0 ? (long) (all.size() / pageSize) : (long) (all.size() / pageSize + 1))
        ;
        pageInfo.setPage(pageIndex);
        pageInfo.setLimit(pageSize);
        pageInfo.setTotalElements((long) all.size());
        pageInfo.setRows(rows);
        return pageInfo;
    }

    @Override
    public FileBean findOne(String id) {
        FileBean fileBean;
        DiskFile targetFile = fileListDao.findDiskFileById(id);
        fileBean = diskFileToFileBean(targetFile);
        return fileBean;
    }

    @Override
    public FileBean diskFileToFileBean(DiskFile diskFile) {
        FileBean fileBean = new FileBean();
        UserDO user = fileListDao.findUserByName(diskFile.getUserName());

        // 通过父id来查找父文件夹的名字, 如果父id==0, 则该文件存在于根目录下
        DiskFile pFile;
        String pName;
        if("0".equals(diskFile.getPid())){
            pName = "";
        } else {
            pFile = fileListDao.findDiskFileById(diskFile.getPid());
            pName = pFile.getFileName();
        }

        fileBean.setId(diskFile.getId().toString());
        // 将fileBean的父id设为diskFile的父id
        fileBean.setPid(diskFile.getPid());
        fileBean.setpName(pName);
        fileBean.setFilename(diskFile.getFileName());
        fileBean.setUploadDate(diskFile.getCreateTime());
        fileBean.setFileSuffix(diskFile.getFileSuffix());
        fileBean.setFileSize(diskFile.getFileSize());
        fileBean.setUploadUserName(diskFile.getUserName());
        fileBean.setFileMd5(diskFile.getFileMd5());
        fileBean.setFileType(diskFile.getFileType());
        fileBean.setUploadUserName(user.getUserName());

        return fileBean;
    }

    @Override
    public List<FileBean> findChildrenFiles(String folderId) {
        List<FileBean> children = new ArrayList<>();
        List<DiskFile> files = fileListDao.findDiskFileByPid(folderId);
        if (!files.isEmpty()) {
            for (DiskFile file : files) {
                children.add(diskFileToFileBean(file));
            }
        }
        return children;
    }

    @Override
    public DownloadBean getDownloadInfo(List<String> fileIds) {
        if (CollectionUtils.isEmpty(fileIds)) {
            throw new RuntimeException("请选择下载记录");
        }
        DownloadBean bean = new DownloadBean();
        bean.setFileNum(0);
        bean.setFolderNum(0);
        bean.setTotalSize(0L);
        for (String fileId : fileIds) {
            DiskFile file = fileListDao.findDiskFileById(fileId);
            bean.setTotalSize(bean.getTotalSize() + file.getFileSize());
            if (file.getFileType() == 1) {
                bean.setFileNum(bean.getFileNum() + 1);
            } else if (file.getFileType() == 0) {
                bean.setFolderNum(bean.getFolderNum() + 1);
            }
            recGetDownloadInfo(file.getId().toString(), bean);
        }
        return bean;
    }

    /**
     * 递归获取下载信息
     * @param id    文件ID
     * @param bean  Download Bean
     */
    private void recGetDownloadInfo(String id, DownloadBean bean) {
        List<FileBean> childrenFiles = findChildrenFiles(id);
        if (!CollectionUtils.isEmpty(childrenFiles)) {
            for (FileBean file : childrenFiles) {
                bean.setTotalSize(bean.getTotalSize() + file.getFileSize());
                if (file.getFileType() == 1) {
                    bean.setFileNum(bean.getFileNum() + 1);
                } else if (file.getFileType() == 0) {
                    bean.setFolderNum(bean.getFolderNum() + 1);
                }
                recGetDownloadInfo(file.getId(), bean);
            }
        }
    }

    @Override
    public void downloadFile(String idJson, String fileName, String fileSuffix, HttpServletResponse response) throws IOException {
        List<String> idList = Arrays.asList(idJson.split(","));
        DownloadRequest downRequest = new DownloadRequest(fileName, fileSuffix, idList, response);
        ResponsibleChain chain = new ResponsibleChain();
        chain.loadHandler(new HandlerInitializer(downRequest, null) {
            @Override
            protected void initChannel(Pipeline line) {
                // 1.校验下载参数
                line.addLast(scu.getHandler(DownloadValidateHandler.class));
                // 2.获取MD5列表
                line.addLast(scu.getHandler(DownloadGetMd5Handler.class));
                // 3.根据MD5将文件合并到指定文件夹并根据情况压缩
                line.addLast(scu.getHandler(DownloadMergeHandler.class));
                // 4.使用httpResponse传输文件
                line.addLast(scu.getHandler(DownloadResponseHandler.class));
                // 5.删除缓存
                line.addLast(scu.getHandler(DownloadClearBufferHandler.class));
            }
        });
        chain.execute();
    }

    @Override
    public void downloadToPath(String path, FileOutputStream fos) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[1024];
        int count;
        while ((count = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fis.close();
    }

    @Override
    public List<DiskMd5Chunk> findStorePath(String Md5) throws IOException {

        return fileListDao.findDiskChunkByMd5(Md5);
    }
}