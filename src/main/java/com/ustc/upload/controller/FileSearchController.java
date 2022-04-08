package com.ustc.upload.controller;

import com.ustc.entity.FileSearchBean;
import com.ustc.entity.PageInfo;
import com.ustc.upload.service.FileSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 王博
 * @date 2022/03/07
 * 搜索功能有bug, 无法完成搜索
 */
@ApiIgnore
@Api(tags = "文件搜索, 有bug暂时不用")
@RestController
@RequestMapping("disk/fileSearch")
public class FileSearchController {

    @Autowired
    private FileSearchService fileSearchService;

    @ApiOperation("搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前分页数", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "limit", value = "每页记录数", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "filename", value = "文件名", dataTypeClass = String.class, required = true),
    })
    @PostMapping("/search")
    public PageInfo<FileSearchBean> search(PageInfo<FileSearchBean> pageInfo, String filename, HttpServletRequest request) throws Exception {
        return fileSearchService.search(filename, "test", pageInfo.getPage(), pageInfo.getLimit());
    }

}