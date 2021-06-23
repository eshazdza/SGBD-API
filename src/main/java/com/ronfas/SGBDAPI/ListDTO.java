package com.ronfas.SGBDAPI;

public class ListDTO {
    private FilterDTO filter;
    private int page;
    private int pageSize;

    public FilterDTO getFilter() {
        return filter;
    }

    public void setFilter(FilterDTO filter) {
        this.filter = filter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
