package com.freesheep.web.vo;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


public class PageVO<T> implements Serializable {

    private List<T> list;
    private long total;
    private int totalPages;
    private int pageSize;

    public List getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageVO() {
    }

    public PageVO(Page<T> page) {
        if (page == null) {
            new PageVO<T>();
        }
        list = page.getContent();
        total = page.getTotalElements();
        totalPages = page.getTotalPages();
    }

}
