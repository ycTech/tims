package com.tims.common.db.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页返回对象
 * Created by chenp on 2017/9/9.
 */
public class Page<T> implements Serializable {
    private Integer start = 0; // 开始范围
    private Integer limit = 15; // 记录限制
    private Long total = 0L; // 记录总数
    private Integer currentPage = 1;//当前页
    private Integer pageSize = 10;//分页大小
    private List<T> list;

    public Page() {

    }

    public Page(List<T> list,  Integer pageSize, Long total, Integer currentPage) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
