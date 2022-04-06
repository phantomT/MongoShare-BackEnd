package com.ustc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 田宝宁
 * @date 2021/6/27
 */
public class PageInfo<T> implements Serializable {
    /**
     * 记录列表
     */
    private List<T> rows;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总记录数
     */
    private Long totalElements;
    /**
     * 每页记录数
     */
    private Integer limit;
    /**
     * 当前页数
     */
    private Integer page;

    public List<T> getRows() {
        return rows;
    }

    public PageInfo<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public PageInfo<T> setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public PageInfo<T> setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public PageInfo<T> setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public PageInfo<T> setPage(Integer page) {
        this.page = page;
        return this;
    }
}
