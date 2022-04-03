package com.ustc.filecommon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ustc.entity.FileListBean;
import com.ustc.filecommon.service.FileCommonService;
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
 * @date 2022/4/1
 */

@Api(tags = "文件通用操作")
@RestController
@RequestMapping("disk/filecommon")
public class FileCommonController {

    @Autowired
    FileCommonService fileCommonService;

    @ApiOperation("查找文件夹")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "操作的用户id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "pid", value = "父文件夹id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "idjson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/findFolderList")
    public CommonResult<List<FileListBean>> findFolderList(@RequestParam("userid") String userid,
                                                           @RequestParam("pid") String pid,
                                                           @RequestParam("idjson") String idJson,
                                                           String token) throws JsonProcessingException {
        // 将idJson中的所有id存入列表中
        List<String> idList = FileUtils.idJsonToList(idJson);
        // 查询结果
        return CommonResultUtils.success(fileCommonService.findFolderList(userid, pid, idList));
    }

    @ApiOperation("移动文件到指定文件夹")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "操作的用户id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "pid", value = "目的父文件夹id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "idjson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/moveTo")
    public CommonResult<String> move(@RequestParam("userid") String userid, @RequestParam("toid") String pid, @RequestParam("idjson") String idJson,
                                     String token) throws IOException {
        List<String> idList = FileUtils.idJsonToList(idJson);
        fileCommonService.move(userid, pid, idList);
        return CommonResultUtils.success("移动成功");
    }

    @ApiOperation("文件重命名")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userid", value = "操作的用户id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "id", value = "文件id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "newName", value = "文件id", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/rename")
    public CommonResult<String> rename(@RequestParam("userid") String userid, @RequestParam("id") String id, @RequestParam("filename") String newName,
                                       String token) throws IOException {
        fileCommonService.rename(userid, id, newName);
        return CommonResultUtils.success("重命名成功");
    }

    @PostMapping("/findOne")
    public CommonResult<FileListBean> findOne(@RequestParam("userid") String userid, @RequestParam("id") String id, String token) {
        return CommonResultUtils.success(fileCommonService.findOneRecord(userid, id));
    }

    @ApiOperation("文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "操作的用户id", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "idjson", value = "id的Json序列化格式, 含有id和文件名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "token", dataTypeClass = String.class)
    })
    @PostMapping("/delete")
    public CommonResult<String> delete(@RequestParam("userid") String userid, @RequestParam("idjson") String idJson,
                                       String token) throws IOException {
        List<String> idList = FileUtils.idJsonToList(idJson);
        fileCommonService.delete(userid, idList);
        return CommonResultUtils.success("文件已删除");
    }
}
