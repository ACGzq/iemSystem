package com.thok.iem.model;

public class RepairTaskRequest extends BaseRequest {

    /**
     * keyword : 120
     * pageNo : 1
     * pageSize : 20
     */

    private String keyword;
    private String pageNo;
    private String pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
