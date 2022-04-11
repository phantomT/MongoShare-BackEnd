package com.ustc.download.service;

import com.ustc.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
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
   * @param fileMd5 文件md5
   * @return 切块列表
   */
  List<String> getChunksByFileMd5(String fileMd5);
  
  byte[] getBytesByUrl(String paramString) throws IOException;

  /**
   * 通过文件id列表获取下载Bean
   * @param fileIds 文件id
   * @return DownloadBean
   */
  DownloadBean getDownloadInfo(List<String> fileIds);

  /**
   * 下载文件
   * @param idJson      文件id列表
   * @param fileName    下载文件名
   * @param fileSuffix  下载文件后缀
   */
  void downloadFile(String idJson, String fileName, String fileSuffix, HttpServletResponse response) throws IOException;

  /**
   * 下载到目的地址
   * @param path  地址
   * @param fos   输出文件
   */
  void downloadToPath(String path, FileOutputStream fos) throws IOException;
}