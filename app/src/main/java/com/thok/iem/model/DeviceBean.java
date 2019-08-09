package com.thok.iem.model;

public class DeviceBean {

    /**
     * id : 23666baabf88496892300d487d863719
     * deviceNo : YCDEVICE2019072305054100001
     * deviceName : 堆垛机
     * specificationType : 暂无型号
     * position : d2c36752e4ff49c8950649295908822f
     * imgUrl : /
     * statusId : f48a18e15bf64802a723722fa2147275
     * buyTime : 2019-07-30 17:19:39
     * createBy : xiaoming
     * createTime : 2019-07-24 01:05:42
     * updateTime : 2019-07-30 17:19:39
     * dr : 0
     * codeImgUrl : http://192.168.1.102:8088/img/code/YCDEVICE2019072305054100001.jpg
     * positionName : 一层11号11
     * statusName : 运行
     * repairCount : 0
     * pollingCount : 0
     */

    private String id;
    private String deviceNo;
    private String deviceName;
    private String specificationType;
    private String position;
    private String imgUrl;
    private String statusId;
    private String buyTime;
    private String createBy;
    private String createTime;
    private String updateTime;
    private int dr;
    private String codeImgUrl;
    private String positionName;
    private String statusName;
    private int repairCount;
    private int pollingCount;

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

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    public int getPollingCount() {
        return pollingCount;
    }

    public void setPollingCount(int pollingCount) {
        this.pollingCount = pollingCount;
    }
}
