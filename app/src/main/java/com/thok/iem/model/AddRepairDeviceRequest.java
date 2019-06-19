package com.thok.iem.model;

public class AddRepairDeviceRequest extends BaseRequest {

    /**
     * content : 设备运行出现响声，时断时续
     * deviceId : asfdsajkkl
     * dicId : alsjfdljlaldsl21
     * reportTime : 2019-06-14 14:25:31
     * reportUser : 赵六
     */

    private String content;
    private String deviceId;
    private String dicId;
    private String reportTime;
    private String reportUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }
}
