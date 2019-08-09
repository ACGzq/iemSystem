package com.thok.iem.model;

import java.util.List;

public class RepairTaskResponse extends BaseResponse {

    /**
     * data : [{"id":"7e1fe0502f6a4601a01eeb9a2c3ea34f","repairCode":"WX2019070402224400001","deviceId":"fbd64ecf8af048b186dfdda0b2101d59","createBy":null,"createTime":"2019-07-04 14:22:45","dicId":"fd529e2cecd64f4497c27646176f95ad","dicName":"运行中","content":"设备运行出现响声，时断时续","imgUrl":null,"status":"REPAIR_REPORT","delFlag":"0","repairUser":null,"repairResult":null,"repairProcess":null,"reportUser":"赵六","reportTime":"2019-06-14 22:25:31","repairTime":null,"acceptTime":null,"deviceVo":{"id":"fbd64ecf8af048b186dfdda0b2101d59","deviceNo":"YCDEVICE2019070402074600001","deviceName":"堆垛机","specificationType":"{}","position":"{1,2}","imgUrl":null,"statusId":"f48a18e15bf64802a723722fa2147275","buyTime":"2019-07-04 22:07:47","createBy":null,"createTime":"2019-07-04 14:22:45","updateTime":"2019-07-04 22:07:47","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019070402074600001.jpg","positionName":"位置名称","statusName":"运行","repairCount":0,"pollingCount":0}}
     * ,{"id":"4ad4bfeb815841cd9e9c84e7ccf4de34","repairCode":"WX2019061902163600001","deviceId":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","createBy":null,"createTime":"2019-06-19 14:16:37","dicId":null,"dicName":null,"content":"打印机            ","imgUrl":null,"status":"REPAIR_REPORT","delFlag":"0","repairUser":null,"repairResult":null,"repairProcess":null,"reportUser":"打印机","reportTime":"2019-06-23 08:00:00","repairTime":null,"acceptTime":null,"deviceVo":{"id":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","deviceNo":"YCDEVICE2019061705071100002","deviceName":"打印机","specificationType":"qeqwewq","position":"d2c36752e4ff49c8950649295908822f","imgUrl":null,"statusId":"f48a18e15bf64802a723722fa2147275","buyTime":"2019-06-18 01:07:11","createBy":null,"createTime":"2019-06-19 14:16:37","updateTime":"2019-06-18 01:07:11","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019061705071100002.jpg","positionName":"一层11号11","statusName":"运行","repairCount":0,"pollingCount":0}},
     * {"id":"46d5a6138dbe439e91f70e00e4cdee06","repairCode":"WX2019061703200100051","deviceId":"f7191dfca6c04d15bc52550a235c015b","createBy":null,"createTime":"2019-06-17 15:20:02","dicId":null,"dicName":null,"content":"风扇不可用","imgUrl":null,"status":"REPAIR_REPORT","delFlag":"0","repairUser":null,"repairResult":null,"repairProcess":null,"reportUser":"赵六","reportTime":"2019-06-14 22:25:31","repairTime":null,"acceptTime":null,"deviceVo":{"id":"f7191dfca6c04d15bc52550a235c015b","deviceNo":"YCDEVICE2019061705073300003","deviceName":"传送带1","specificationType":"sfse111","position":"6d907e5c55fa410bb47ba1b02209f4c3","imgUrl":null,"statusId":"f48a18e15bf64802a723722fa2147275","buyTime":"2019-06-18 01:10:43","createBy":null,"createTime":"2019-06-17 15:20:02","updateTime":"2019-06-18 01:10:43","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019061705073300003.jpg","positionName":"一层11号","statusName":"运行","repairCount":0,"pollingCount":0}}]
     * pageSize : 20
     * pageNo : 1
     * totalPage : 3
     * totalCount : 55
     */

    private int pageSize;
    private int pageNo;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7e1fe0502f6a4601a01eeb9a2c3ea34f
         * repairCode : WX2019070402224400001
         * deviceId : fbd64ecf8af048b186dfdda0b2101d59
         * createBy : null
         * createTime : 2019-07-04 14:22:45
         * dicId : fd529e2cecd64f4497c27646176f95ad
         * dicName : 运行中
         * content : 设备运行出现响声，时断时续
         * imgUrl : null
         * status : REPAIR_REPORT
         * delFlag : 0
         * repairUser : null
         * repairResult : null
         * repairProcess : null
         * reportUser : 赵六
         * reportTime : 2019-06-14 22:25:31
         * repairTime : null
         * acceptTime : null
         * deviceVo : {"id":"fbd64ecf8af048b186dfdda0b2101d59","deviceNo":"YCDEVICE2019070402074600001","deviceName":"堆垛机","specificationType":"{}","position":"{1,2}","imgUrl":null,"statusId":"f48a18e15bf64802a723722fa2147275","buyTime":"2019-07-04 22:07:47","createBy":null,"createTime":"2019-07-04 14:22:45","updateTime":"2019-07-04 22:07:47","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019070402074600001.jpg","positionName":"位置名称","statusName":"运行","repairCount":0,"pollingCount":0}
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
        private Object repairUser;
        private Object repairResult;
        private Object repairProcess;
        private String reportUser;
        private String reportTime;
        private Object repairTime;
        private Object acceptTime;
        private DeviceVoBean deviceVo;

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

        public Object getRepairUser() {
            return repairUser;
        }

        public void setRepairUser(Object repairUser) {
            this.repairUser = repairUser;
        }

        public Object getRepairResult() {
            return repairResult;
        }

        public void setRepairResult(Object repairResult) {
            this.repairResult = repairResult;
        }

        public Object getRepairProcess() {
            return repairProcess;
        }

        public void setRepairProcess(Object repairProcess) {
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

        public Object getRepairTime() {
            return repairTime;
        }

        public void setRepairTime(Object repairTime) {
            this.repairTime = repairTime;
        }

        public Object getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(Object acceptTime) {
            this.acceptTime = acceptTime;
        }

        public DeviceVoBean getDeviceVo() {
            return deviceVo;
        }

        public void setDeviceVo(DeviceVoBean deviceVo) {
            this.deviceVo = deviceVo;
        }

        public static class DeviceVoBean {
            /**
             * id : fbd64ecf8af048b186dfdda0b2101d59
             * deviceNo : YCDEVICE2019070402074600001
             * deviceName : 堆垛机
             * specificationType : {}
             * position : {1,2}
             * imgUrl : null
             * statusId : f48a18e15bf64802a723722fa2147275
             * buyTime : 2019-07-04 22:07:47
             * createBy : null
             * createTime : 2019-07-04 14:22:45
             * updateTime : 2019-07-04 22:07:47
             * dr : 0
             * codeImgUrl : http://192.168.1.102:8088/img/code/YCDEVICE2019070402074600001.jpg
             * positionName : 位置名称
             * statusName : 运行
             * repairCount : 0
             * pollingCount : 0
             */

            private String id;
            private String deviceNo;
            private String deviceName;
            private String specificationType;
            private String position;
            private Object imgUrl;
            private String statusId;
            private String buyTime;
            private Object createBy;
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

            public Object getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(Object imgUrl) {
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
    }
}
