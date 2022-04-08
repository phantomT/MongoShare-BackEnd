package com.ustc.download.controller;

import com.ustc.download.service.FileService;
import com.ustc.entity.FileListBean;
import com.ustc.entity.PageInfo;
import com.ustc.utils.CommonResult;
import com.ustc.utils.CommonResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 田宝宁
 * @date 2022/04/07
 */
@Api(tags = "列表展示")
@RestController
@RequestMapping("/disk/file")
public class FileListController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "全部-列表", notes = "全部-列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数（从1开始）", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "分页记录数", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "pid", value = "父节点ID（默认是0）", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "orderField", value = "排序字段", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orderType", value = "排序类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/findList")
    public CommonResult<PageInfo<FileListBean>> findList(PageInfo<FileListBean> pi, String pid, String orderField, String orderType) {

        PageInfo<FileListBean> pageList = fileService.findPageList(pi.getPage(), pi.getLimit(), pid, "all", orderField, orderType);

        return CommonResultUtils.success(pageList);
    }
}
