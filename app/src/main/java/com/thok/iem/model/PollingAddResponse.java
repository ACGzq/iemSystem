package com.thok.iem.model;

public class PollingAddResponse extends BaseResponse {

    /**
     * data : {"id":"a76ce44a17d04afbb42e4f8c31cd58a7","deviceId":"1febc9135c6b41199bedadae4e7833f6","createTime":"2019-07-22 17:46:39","status":"VALID","delFlag":"0","content":"电源接触，电压，漏电预防","upLimit":"电压250V","downLimit":"电压210V","meterUnit":"V","pollingUser":"赵六","deviceVo":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : a76ce44a17d04afbb42e4f8c31cd58a7
         * deviceId : 1febc9135c6b41199bedadae4e7833f6
         * createTime : 2019-07-22 17:46:39
         * status : VALID
         * delFlag : 0
         * content : 电源接触，电压，漏电预防
         * upLimit : 电压250V
         * downLimit : 电压210V
         * meterUnit : V
         * pollingUser : 赵六
         * deviceVo : null
         */

        private String id;
        private String deviceId;
        private String createTime;
        private String status;
        private String delFlag;
        private String content;
        private String upLimit;
        private String downLimit;
        private String meterUnit;
        private String pollingUser;
        private Object deviceVo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpLimit() {
            return upLimit;
        }

        public void setUpLimit(String upLimit) {
            this.upLimit = upLimit;
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

        public Object getDeviceVo() {
            return deviceVo;
        }

        public void setDeviceVo(Object deviceVo) {
            this.deviceVo = deviceVo;
        }
    }
}
