package com.thok.iem.model;

import java.util.List;

public class MaintenanceResponse extends BaseResponse {

    /**
     * data : {}
     * pageNo : 0
     * pageSize : 0
     * totalCount : 0
     * totalPage : 0
     */

    private List<MaintenanceBean> data;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPage;

    public List<MaintenanceBean> getData() {
        return data;
    }

    public void setData(List<MaintenanceBean> data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
}
