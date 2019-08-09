package com.thok.iem.model;

public class RepairFinishResponse extends BaseResponse {

    /**
     * data : {"id":"7e1fe0502f6a4601a01eeb9a2c3ea34f","repairCode":"WX2019070402224400001","deviceId":"fbd64ecf8af048b186dfdda0b2101d59","createBy":null,"createTime":"2019-07-04 14:22:45","dicId":"赵六","dicName":"赵六","content":"设备运行出现响声，时断时续","imgUrl":null,"status":"REPAIRED","delFlag":"0","repairUser":"老李","repairResult":"修复完成，1小时候可以启动","repairProcess":"线路出错，有漏电现象，导致主控主板过载烧坏","reportUser":"赵六","reportTime":"2019-06-14 22:25:31","repairTime":"2019-06-19 22:25:31","acceptTime":null,"deviceVo":null}
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
         * id : 7e1fe0502f6a4601a01eeb9a2c3ea34f
         * repairCode : WX2019070402224400001
         * deviceId : fbd64ecf8af048b186dfdda0b2101d59
         * createBy : null
         * createTime : 2019-07-04 14:22:45
         * dicId : 赵六
         * dicName : 赵六
         * content : 设备运行出现响声，时断时续
         * imgUrl : null
         * status : REPAIRED
         * delFlag : 0
         * repairUser : 老李
         * repairResult : 修复完成，1小时候可以启动
         * repairProcess : 线路出错，有漏电现象，导致主控主板过载烧坏
         * reportUser : 赵六
         * reportTime : 2019-06-14 22:25:31
         * repairTime : 2019-06-19 22:25:31
         * acceptTime : null
         * deviceVo : null
         */

        private String id;
        private String repairCode;
        private String deviceId;
        private Object createBy;
        private String createTime;
        private String dicId;
        private String dicName;
        private String content;
        private Object imgUrl;
        private String status;
        private String delFlag;
        private String repairUser;
        private String repairResult;
        private String repairProcess;
        private String reportUser;
        private String reportTime;
        private String repairTime;
        private Object acceptTime;
        private Object deviceVo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRepairCode() {
            return repairCode;
        }

        public void setRepairCode(String repairCode) {
            this.repairCode = repairCode;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
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

        public String getDicId() {
            return dicId;
        }

        public void setDicId(String dicId) {
            this.dicId = dicId;
        }

        public String getDicName() {
            return dicName;
        }

        public void setDicName(String dicName) {
            this.dicName = dicName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
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

        public String getRepairUser() {
            return repairUser;
        }

        public void setRepairUser(String repairUser) {
            this.repairUser = repairUser;
        }

        public String getRepairResult() {
            return repairResult;
        }

        public void setRepairResult(String repairResult) {
            this.repairResult = repairResult;
        }

        public String getRepairProcess() {
            return repairProcess;
        }

        public void setRepairProcess(String repairProcess) {
            this.repairProcess = repairProcess;
        }

        public String getReportUser() {
            return reportUser;
        }

        public void setReportUser(String reportUser) {
            this.reportUser = reportUser;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public String getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(String repairTime) {
            this.repairTime = repairTime;
        }

        public Object getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(Object acceptTime) {
            this.acceptTime = acceptTime;
        }

        public Object getDeviceVo() {
            return deviceVo;
        }

        public void setDeviceVo(Object deviceVo) {
            this.deviceVo = deviceVo;
        }
    }
}
