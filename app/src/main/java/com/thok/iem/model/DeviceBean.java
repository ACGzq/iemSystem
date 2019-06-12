package com.thok.iem.model;

public class DeviceBean {


    /**
     * id : 9f329642e5404a72bc4e63ac6275132a
     * deviceNo : YCDEVICE2019061005242900005
     * deviceName : 堆垛机
     * specificationType : {}
     * position : {1,2}
     * imgUrl : /
     * status : 0
     * createBy : null
     * createTime : 2019-06-10T17:24:29.000+0000
     * updateTime : 2019-06-10T17:24:29.000+0000
     * dr : 0
     * codeImgUrl : http://192.168.1.102:8088/img/code/YCDEVICE2019061005242900005.jpg
     * buyingTime : 2019-05-10T07:44:15.000+0000
     */

    private String id;
    private String deviceNo;
    private String deviceName;
    private String specificationType;
    private String position;
    private String imgUrl;
    private int status;
    private Object createBy;
    private String createTime;
    private String updateTime;
    private int dr;
    private String codeImgUrl;
    private String buyingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public String getCodeImgUrl() {
        return codeImgUrl;
    }

    public void setCodeImgUrl(String codeImgUrl) {
        this.codeImgUrl = codeImgUrl;
    }

    public String getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(String buyingTime) {
        this.buyingTime = buyingTime;
    }
}
