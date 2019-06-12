package com.thok.iem.model;

public class SearchDeviceRequestBean extends BaseRequest {

    /**
     * deviceName : 堆垛机
     * deviceNum :
     * maintenancer :
     * token : 1
     */

    private String deviceName;
    private String deviceNum;
    private String maintenancer;

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


}
