package com.thok.iem.model;

public class MaintenanceBean {

    /**
     * changeDays : 2周
     * days : 1年
     * deviceName : 堆垛机
     * deviceNum : 45612345
     * maintenancer : 陈智斌
     * progrem : 治病项目
     */

    private String changeDays;
    private String days;
    private String deviceName;
    private String deviceNum;
    private String maintenancer;
    private String progrem;

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

    public String getProgrem() {
        return progrem;
    }

    public void setProgrem(String progrem) {
        this.progrem = progrem;
    }
}
