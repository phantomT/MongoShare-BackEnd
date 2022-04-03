package com.ustc.download.service;

import com.ustc.entity.*;

import java.io.IOException;
import java.util.List;

public interface FileService {
  /**
   * 文件列表分页
   * @param page      当前页号
   * @param limit     每页记录数
   * @param pid       父文件夹id
   * @param typeCode  文件类型码
   * @param orderField
   * @param orderType
   * @return 本页所有文件信息
   */
  PageInfo<FileListBean> findPageList(Integer page, Integer limit, String pid, String typeCode,String orderField,String orderType);

  List<DiskFile> findAllFile();

  /**
   * 根据文件Id找出该文件
   * @param paramString 文件Id
   * @return FileBean
   */
  FileBean findOne(String paramString);
  
  List<FileBean> findChildrenFiles(String paramString1, String paramString2);
  
  List<FileBean> findChildrenFiles(String paramString);

  /**
   * 通过文件md5得到切块列表
   * @param paramString 文件md5
   * @return 切块列表
   */
  List<String> getChunksByFileMd5(String paramString);
  
  byte[] getBytesByUrl(String paramString) throws IOException;
  
  void saveFile(DiskFile paramDiskFile);
  
  DownloadBean getDownloadInfo(List<String> paramList);
}