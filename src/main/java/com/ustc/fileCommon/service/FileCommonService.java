package com.ustc.fileCommon.service;

import com.ustc.entity.FileListBean;

import java.io.IOException;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/01
 */
public interface FileCommonService {
    /**
     * 查找文件夹列表(用于文件移动和复制), 查找pid下所有的文件id不在idList下的文件夹
     *
     * @param userName  用户名
     * @param pid       父文件夹id
     * @param idList    文件id列表
     * @return 返回文件夹列表
     */
    List<FileListBean> findFolderList(String userName, String pid, List<String> idList);

    /**
     * 用于文件移动, 文件移动到指定目录下
     *
     * @param userName  用户名
     * @param pid       父文件夹id
     * @param idList    文件id列表
     */
    void move(String userName, String pid, List<String> idList) throws IOException;

    /**
     * 修改文件名
     *
     * @param userName  用户名
     * @param id        文件id
     * @param newName   新文件名
     */
    void rename(String userName, String id, String newName) throws IOException;

    /**
     * 根据id获取一条记录
     *
     * @param userName  用户名
     * @param id        文件id
     * @return 记录
     */
    FileListBean findOneRecord(String userName, String id);

    /**
     * 删除文件
     *
     * @param userName  用户名
     * @param idList    文件id列表
     */
    void delete(String userName, List<String> idList) throws IOException;
}
