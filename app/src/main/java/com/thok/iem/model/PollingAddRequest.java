package com.thok.iem.model;

public class PollingAddRequest extends BaseRequest {

    /**
     * content : 电源接触，电压，漏电预防
     * deviceId : asfdsajkkl
     * downLimit : 电压210V
     * meterUnit : V
     * pollingUser : 赵六
     * upLimit : 电压250V
     */

    private String content;
    private String deviceId;
    private String downLimit;
    private String meterUnit;
    private String pollingUser;
    private String upLimit;

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

    public String getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(String downLimit) {
        this.downLimit = downLimit;
    }

    public String getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(String meterUnit) {
        this.meterUnit = meterUnit;
    }

    public String getPollingUser() {
        return pollingUser;
    }

    public void setPollingUser(String pollingUser) {
        this.pollingUser = pollingUser;
    }

    public String getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(String upLimit) {
        this.upLimit = upLimit;
    }
}
