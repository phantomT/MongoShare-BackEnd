package com.ustc.upload.controller;

import com.ustc.entity.FileListBean;
import com.ustc.entity.PageInfo;
import com.ustc.upload.service.FileSearchService;
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
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 田宝宁 王博
 * @date 2022/06/11
 * 搜索功能未完善
 */
@ApiIgnore
@Api(tags = "文件搜索")
@RestController
@RequestMapping("disk/search")
public class FileSearchController {

    @Autowired
    private FileSearchService fileSearchService;

    @ApiOperation("搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数（从1开始）", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "分页记录数", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键词", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "searchField", value = "搜索域", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "orderField", value = "排序字段", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "orderType", value = "排序类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/fileSearch")
    public CommonResult<PageInfo<FileListBean>> search(PageInfo<FileListBean> pi, String keyWord, String searchField, String orderField, String orderType) throws Exception {

        PageInfo<FileListBean> pageList = fileSearchService.findPageList(pi.getPage(), pi.getLimit(), keyWord, searchField, orderField, orderType);

        return CommonResultUtils.success(pageList);
    }

}