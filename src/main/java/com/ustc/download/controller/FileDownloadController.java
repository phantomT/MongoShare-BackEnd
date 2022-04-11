package com.ustc.download.controller;

import com.ustc.config.StoreConfiguration;
import com.ustc.download.service.FileService;
import com.ustc.entity.DownloadBean;
import com.ustc.entity.FileBean;
import com.ustc.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/08
 */
@Api(tags = {"文件下载"})
@RestController
@RequestMapping({"/disk/fileDownload"})
public class FileDownloadController {

    @Autowired
    private FileService fileService;

    @Autowired
    private StoreConfiguration storeConfiguration;

    @Value("${file-max-size}")
    private Long fileMaxSize;

    @ApiOperation(value = "单文件下载", notes = "单文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idJson", value = "文件ID列表", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "downloadName", value = "下载文件名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "downloadSuffix", value = "下载文件后缀", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query")
    })
    @PostMapping({"/download"})
    public void download(String downloadName, String downloadSuffix, String idJson, HttpServletResponse response) throws IOException {
        fileService.downloadFile(idJson, downloadName, downloadSuffix, response);
        System.out.println("文件下载完成" + downloadName);
    }

    @ApiOperation(value = "多文件下载-获取文件信息", notes = "是否大于1G(超过1G不支持)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idJson", value = "勾选文件ID（[{'id':'xx'}]）", dataTypeClass = String.class, paramType = "query", required = true),
            @ApiImplicitParam(name = "token", value = "token", dataTypeClass = String.class, paramType = "query", required = true)})

    @PostMapping({"/getDownloadInfo"})
    public CommonResult<DownloadBean> getDownloadInfo(@RequestParam String idJson) {
        ValidateUtils.validate(idJson, "下载记录");
        String[] ids = idJson.split(",");
        List<String> fileIds = new ArrayList<>(Arrays.asList(ids));
        // 获取用户信息
        // SessionUserBean loginUser = (SessionUserBean) request.getSession().getAttribute("loginUser");
        // 获取下载信息
        DownloadBean bean = fileService.getDownloadInfo(fileIds);
        // 将字节数转换为K、M、G等
        bean.setTotalSizeName(CapacityUtils.convert(bean.getTotalSize()));
        // 设置文件大小不超过1G
        if (bean.getTotalSize() <= fileMaxSize) {
            bean.setIsBig(0);
        } else {
            bean.setIsBig(1);
        }
        return CommonResultUtils.success(bean);
    }

    @ApiOperation("下载切块合并")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "downloadName", value = "下载文件名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "downloadSuffix", value = "文件后缀", dataTypeClass = String.class, required = true)
    })
    @PostMapping({"/mergeFiles"})
    public CommonResult<String> mergeFiles(String downloadName, String downloadSuffix, String idJson, HttpServletResponse response) {
        try {
            ValidateUtils.validate(downloadName, "下载文件名称");
            ValidateUtils.validate(downloadSuffix, "下载文件格式");
            ValidateUtils.validate(idJson, "下载记录");
            String[] fileIds = idJson.split(",");
            String path = storeConfiguration.getStorePath() + "/downloadMerge/" + downloadName;
            File fileRootZip = new File(path + "." + downloadSuffix);
            if (fileRootZip.exists()) {
                throw new RuntimeException("该下载名称已经存在,请更换一个!");
            }
            File fileRoot = new File(path);
            if (!fileRoot.exists()) {
                fileRoot.mkdirs();
            } else {
                throw new RuntimeException("该下载名称已经存在,请更换一个!");
            }
            for (String fileId : fileIds) {
                FileBean bean = fileService.findOne(fileId);
                // 如果是文件夹
                if (bean.getFileType().equals(FileType.FOLDER.getTypeCode())) {
                    File file = new File(path + "/" + bean.getFilename());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    dgDownload("ttbb", path + "/" + bean.getFilename(), bean.getId());
                } else {
                    String filename = path + "/" + bean.getFilename();
                    FileOutputStream out = new FileOutputStream(filename);
                    List<String> urls = fileService.getChunksByFileMd5(bean.getFileMd5());
                    for (String url : urls) {
                        byte[] bytes = fileService.getBytesByUrl(storeConfiguration.getStorePath() + "/" + url);
                        out.write(bytes);
                        out.flush();
                    }
                    out.close();
                }
            }
            String zipPath = path + "." + downloadSuffix;
            FileZipUtils.fileToZip(path, zipPath);

            String filename = downloadName + "." + downloadSuffix;
            String paths = path + "." + downloadSuffix;
            //String ip=IpUtils.getInternetIp();
            //返回具体的路径，让前端直接直连下载
            String url = "http://127.0.0.1:8080" + "/disk/fileDownload/downloadZip?filename=" + filename + "&path=" + paths;

            return CommonResultUtils.success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResultUtils.error(1, "e.getMessage()");
        }
    }

    private void dgDownload(String userName, String path, String pid) throws Exception {
        List<FileBean> beans = this.fileService.findChildrenFiles(userName, pid);
        if (!CollectionUtils.isEmpty(beans)) {
            for (FileBean bean : beans) {
                if (bean.getFileType() == 0) {
                    File file = new File(path + "/" + bean.getFilename());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    dgDownload(userName, path + "/" + bean.getFilename(), bean.getId());
                    continue;
                }
                String filename = path + "/" + bean.getFilename();
                FileOutputStream out = new FileOutputStream(filename);
                List<String> urls = fileService.getChunksByFileMd5(bean.getFileMd5());
                for (String url : urls) {
                    byte[] bytes = fileService.getBytesByUrl(storeConfiguration.getStorePath() + "/" + url);
                    out.write(bytes);
                    out.flush();
                }
                out.close();
            }
        }
    }

    @ApiOperation(value = "多文件下载-压缩文件下载", notes = "多文件下载-压缩文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filename", value = "压缩后的名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "path", value = "压缩文件存储路径", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query", required = true)})
    @GetMapping({"/downloadZip"})
    public void downloadZip(String filename, String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        byte[] bs = new byte[1024];
        int len;
        while ((len = input.read(bs)) != -1) {
            servletOutputStream.write(bs, 0, len);
            servletOutputStream.flush();
        }
        input.close();
        servletOutputStream.close();
        file.delete();
    }
}