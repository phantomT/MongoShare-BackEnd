package com.ustc.fileCommon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ustc.entity.FileListBean;
import com.ustc.fileCommon.service.FileCommonService;
import com.ustc.utils.CommonResult;
import com.ustc.utils.CommonResultUtils;
import com.ustc.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2022/04/01
 */

@Api(tags = "文件通用操作")
@RestController
@RequestMapping("disk/fileCommon")
public class FileCommonController {

    @Autowired
    FileCommonService fileCommonService;

    @ApiOperation("查找文件夹")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "操作的用户名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "pid", value = "父文件夹id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "idJson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/findFolderList")
    public CommonResult<List<FileListBean>> findFolderList(@RequestParam("userName") String userName,
                                                           @RequestParam("pid") String pid,
                                                           @RequestParam("idJson") String idJson,
                                                           String token) throws JsonProcessingException {
        // 将idJson中的所有id存入列表中
        List<String> idList = FileUtils.idJsonToList(idJson);
        // 查询结果
        return CommonResultUtils.success(fileCommonService.findFolderList(userName, pid, idList));
    }

    @ApiOperation("移动文件到指定文件夹")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "操作的用户名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "pid", value = "目的父文件夹id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "idJson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/moveTo")
    public CommonResult<String> move(@RequestParam("userName") String userName, @RequestParam("toPid") String pid, @RequestParam("idJson") String idJson,
                                     String token) throws IOException {
        List<String> idList = FileUtils.idJsonToList(idJson);
        fileCommonService.move(userName, pid, idList);
        return CommonResultUtils.success("移动成功");
    }

    @ApiOperation("文件重命名")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "操作的用户名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "id", value = "文件id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "newName", value = "文件id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/rename")
    public CommonResult<String> rename(@RequestParam("userName") String userName, @RequestParam("id") String id, @RequestParam("filename") String newName,
                                       String token) throws IOException {
        fileCommonService.rename(userName, id, newName);
        return CommonResultUtils.success("重命名成功");
    }

    @PostMapping("/findOne")
    public CommonResult<FileListBean> findOne(@RequestParam("userName") String userName, @RequestParam("id") String id, String token) {
        return CommonResultUtils.success(fileCommonService.findOneRecord(userName, id));
    }

    @ApiOperation("文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "操作的用户名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "idJson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/delete")
    public CommonResult<String> delete(@RequestParam("userName") String userName, @RequestParam("idJson") String idJson,
                                       String token) throws IOException {
        List<String> idList = FileUtils.idJsonToList(idJson);
        fileCommonService.delete(userName, idList);
        return CommonResultUtils.success("文件已删除");
    }
}
