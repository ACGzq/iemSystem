package com.thok.iem.model;

public class MaintenancerHistoryAddRequest extends BaseRequest {

    /**
     * changeDays : 2周
     * days : 1年
     * deviceName : 堆垛机
     * deviceNum : 45612345
     * maintenancer : 小白
     * nextOperationTime : 2019-7-10 12:20:30
     * operation : 0
     * dicId:0
     * operationContent : 0
     * progrem : 保养项目
     * updateTime : 2019-7-10 12:20:30
     */

    private String changeDays;
    private String days;
    private String deviceName;
    private String deviceNum;
    private String maintenancer;
    private String nextOperationTime;
    private String operation;
    private String operationContent;
    private String progrem;
    private String updateTime;
    private String maintenancerId;
    private String dicId;

    public String getMaintenancerId() {
        return maintenancerId;
    }

    public void setMaintenancerId(String maintenancerId) {
        this.maintenancerId = maintenancerId;
    }

    public String getChangeDays() {
        return changeDays;
    }

    public void setChangeDays(String changeDays) {
        this.changeDays = changeDays;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getMaintenancer() {
        return maintenancer;
    }

    public void setMaintenancer(String maintenancer) {
        this.maintenancer = maintenancer;
    }

    public String getNextOperationTime() {
        return nextOperationTime;
    }

    public void setNextOperationTime(String nextOperationTime) {
        this.nextOperationTime = nextOperationTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getProgrem() {
        return progrem;
    }

    public void setProgrem(String progrem) {
        this.progrem = progrem;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }
}
