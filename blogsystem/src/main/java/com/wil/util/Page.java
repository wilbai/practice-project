package com.wil.util;

import java.util.List;

/**
 * Created by wil on 2018/8/17.
 */
public class Page<T> {

    private int pageNo;
    private int start;
    private int total;
    private int pageSize = 5;
    private int pageTotal;
    private List<T> items;

    public Page(int pageNo, int total) {
        this.total = total;

        pageTotal = total/pageSize;
        if(total % pageSize !=0 || total < pageSize) {
            pageTotal++;
        }

        if(pageNo < 1) {
            pageNo = 1;
        }

        if(pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
        start = (pageNo - 1) * pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
