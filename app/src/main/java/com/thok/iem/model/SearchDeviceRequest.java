package com.thok.iem.model;

public class SearchDeviceRequest extends BaseRequest {

    /**
     * deviceName : 堆垛机
     * deviceNo : ddsfsafaf
     * imgUrl : /
     * pageNo : 1
     * pageSize : 20
     * position : {1,2}
     * specificationType : {}
     * statusId : 0
     */

    private String deviceName;
    private String deviceNo;
    private String imgUrl;
    private String pageNo;
    private String pageSize;
    private String position;
    private String specificationType;
    private String statusId;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
