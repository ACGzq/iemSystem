package com.thok.iem.model;

public class MaintenanceBean {

    /**
     * id : 1cd73fe143d64117a98889e02b67b001
     * userId : null
     * userName : null
     * createBy : null
     * createTime : 2019-07-18 17:29:23
     * lastModifyBy : null
     * lastModifyTime : 2019-07-18 17:29:23
     * deviceNum : 45612345
     * deviceName : 堆垛机
     * progrem : 设备项目
     * days : 1年
     * changeDays : 2周
     * maintenancer : 小白
     * dr : null
     * status : 0
     */

    private String id;
    private Object userId;
    private Object userName;
    private Object createBy;
    private String createTime;
    private Object lastModifyBy;
    private String lastModifyTime;
    private String deviceNum;
    private String deviceName;
    private String progrem;
    private String days;
    private String changeDays;
    private String maintenancer;
    private Object dr;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
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

    public Object getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(Object lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProgrem() {
        return progrem;
    }

    public void setProgrem(String progrem) {
        this.progrem = progrem;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getChangeDays() {
        return changeDays;
    }

    public void setChangeDays(String changeDays) {
        this.changeDays = changeDays;
    }

    public String getMaintenancer() {
        return maintenancer;
    }

    public void setMaintenancer(String maintenancer) {
        this.maintenancer = maintenancer;
    }

    public Object getDr() {
        return dr;
    }

    public void setDr(Object dr) {
        this.dr = dr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
