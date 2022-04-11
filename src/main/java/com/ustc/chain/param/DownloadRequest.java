package com.ustc.chain.param;

import com.ustc.chain.core.ContextRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 将Download信息封装为在责任链中处理的请求
 * @author 田宝宁
 * @date 2022/04/08
 */
public class DownloadRequest extends ContextRequest {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 文件Id列表
     */
    private List<String> idList;
    /**
     * 文件名列表
     */
    private List<String> nameList;
    /**
     * 文件MD5列表
     */
    private List<String> md5List;
    /**
     * 最终下载文件的保存路径
     */
    private String filePath;
    private Boolean compressFile;
    private HttpServletResponse response;

    public DownloadRequest(String fileName, String fileSuffix, List<String> idList,
                           HttpServletResponse response){
        this.fileName = fileName;
        this.fileSuffix = fileSuffix;
        this.idList = idList;
        this.response = response;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getMd5List() {
        return md5List;
    }

    public void setMd5List(List<String> md5List) {
        this.md5List = md5List;
    }

    public Boolean getCompressFile() {
        return compressFile;
    }

    public void setCompressFile(Boolean compressFile) {
        this.compressFile = compressFile;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
