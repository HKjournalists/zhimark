package com.spring.mvc.model.gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/6/26.
 */
public class PaginationGson {
    private long totalCount;
    private long totalPage;
    private long currentPage;
    private long prePage;
    private long nextPage;
    private long pageSize;
    private List data = new ArrayList();

    public PaginationGson(long totalNum, int currentPage, int pageSize, List data) {
        this.pageSize = pageSize;
        this.totalPage = totalNum % pageSize > 0 ? totalNum / pageSize + 1 : totalNum / pageSize;
        this.currentPage = currentPage;
        this.prePage = currentPage == 1 ? currentPage : currentPage - 1;
        this.nextPage = currentPage == totalPage ? totalPage : currentPage + 1;
        this.data = data;
        this.totalCount = totalNum;
    }

    public PaginationGson() {
    }


    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPrePage() {
        return prePage;
    }

    public void setPrePage(long prePage) {
        this.prePage = prePage;
    }

    public long getNextPage() {
        return nextPage;
    }

    public void setNextPage(long nextPage) {
        this.nextPage = nextPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPage() {

        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
