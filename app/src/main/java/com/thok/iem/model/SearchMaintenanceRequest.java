package com.thok.iem.model;

public class SearchMaintenanceRequest extends BaseRequest {

    /**
     * deviceName : 堆垛机
     * deviceNum : 45612345
     * maintenancer : 陈智斌
     */

    private String deviceName;
    private String deviceNum;
    private String maintenancer;

    public SearchMaintenanceRequest() {}
    public SearchMaintenanceRequest(String token) {
        super(token);
    }
    public SearchMaintenanceRequest(String deviceName, String deviceNum, String maintenancer,String token) {
        super(token);
        this.deviceName = deviceName;
        this.deviceNum = deviceNum;
        this.maintenancer = maintenancer;
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
}
