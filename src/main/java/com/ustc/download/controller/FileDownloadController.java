package com.ustc.download.controller;

import com.ustc.download.service.FileService;
import com.ustc.entity.DiskMd5Chunk;
import com.ustc.entity.DownloadBean;
import com.ustc.utils.CapacityUtils;
import com.ustc.utils.CommonResult;
import com.ustc.utils.CommonResultUtils;
import com.ustc.utils.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Value("${file-max-size}")
    private Long fileMaxSize;

    @ApiOperation(value = "文件下载", notes = "文件下载")
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
    /**
     * @author 刘玄亮
     * @date 2022/04/11
     */
    @ApiOperation(value = "文件预览", notes = "文件预览")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileMd5", value = "文件md5", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query")
    })
    @PostMapping({"/preview"})
    public CommonResult<String> preview(@RequestParam String fileMd5) throws IOException{
        List<DiskMd5Chunk> chunks = fileService.findStorePath(fileMd5);
        String url = chunks.get(0).getStorePath();
        //String ur=fileService.previewFile(fileMd5);
        return CommonResultUtils.success(url);
    }



}