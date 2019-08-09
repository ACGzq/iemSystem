package com.thok.iem.model;

public class PickListRequest extends BaseRequest {

    /**
     * endTime : 2019-06-14 14:25:31
     * pageNo : 1
     * pageSize : 20
     * pickNo : 123213
     * startTime : 2019-06-14 14:25:31
     */

    private String endTime;
    private String pageNo;
    private String pageSize;
    private String pickNo;
    private String startTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getPickNo() {
        return pickNo;
    }

    public void setPickNo(String pickNo) {
        this.pickNo = pickNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
