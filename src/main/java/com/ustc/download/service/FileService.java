package com.ustc.download.service;

import com.ustc.entity.*;

import java.io.IOException;
import java.util.List;

public interface FileService {
  /**
   * 文件列表分页
   * @param page        当前页号
   * @param limit       每页记录数
   * @param pid         父文件夹id
   * @param typeCode    文件类型码
   * @param orderField  排序字段
   * @param orderType   排序类型
   * @return 本页所有文件信息
   */
  PageInfo<FileListBean> findPageList(Integer page, Integer limit, String pid, String typeCode,String orderField,String orderType);

  /**
   * 根据文件Id找出该文件
   * @param id 文件Id
   * @return FileBean
   */
  FileBean findOne(String id);

  /**
   * 将DiskFile类转换为FileBean类
   * @param diskFile  DiskFile类
   * @return FileBean类
   */
  FileBean diskFileToFileBean(DiskFile diskFile);
  
  List<FileBean> findChildrenFiles(String userName, String folderId);
  
  List<FileBean> findChildrenFiles(String folderId);

  /**
   * 通过文件md5得到切块列表
   * @param paramString 文件md5
   * @return 切块列表
   */
  List<String> getChunksByFileMd5(String paramString);
  
  byte[] getBytesByUrl(String paramString) throws IOException;
  
  DownloadBean getDownloadInfo(List<String> paramList);
}