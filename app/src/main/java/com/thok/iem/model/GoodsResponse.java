package com.thok.iem.model;

import java.util.List;

public class GoodsResponse extends BaseResponse {

    /**
     * data : {}
     * pageSize : 1
     * pageNo : 1ll
     * totalPage : 1
     * totalCount : 1
     */

    private List<GoodsBean> data;
    private int pageSize;
    private String pageNo;
    private int totalPage;
    private int totalCount;

    public List<GoodsBean> getData() {
        return data;
    }

    public void setData(List<GoodsBean> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class DataBean {
    }
}
