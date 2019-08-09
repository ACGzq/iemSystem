package com.thok.iem.model;

import java.util.List;

public class PickListResponse extends BaseResponse {

    /**
     * data : [{"id":"554eb9e5655542dd8100551644f3964b","pickNo":"BJSQ2019072311581600001","repairId":"4ad4bfeb815841cd9e9c84e7ccf4de34","pickUser":"打印机","createTime":"2019-07-23 11:58:17","updateTime":"2019-07-23 11:58:17","status":"VALID","delFlag":"0","details":null,"repair":{"id":"4ad4bfeb815841cd9e9c84e7ccf4de34","repairCode":"WX2019061902163600001","deviceId":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","createBy":null,"createTime":"2019-07-23 11:58:17","dicId":null,"dicName":null,"content":"打印机            ","imgUrl":null,"status":"VALID","delFlag":"0","repairUser":"张三","repairResult":"砸了","repairProcess":"修不了","reportUser":"打印机","reportTime":"2019-06-23 08:00:00","repairTime":"2016-01-01 08:00:00","acceptTime":null,"deviceVo":null}},
     * {"id":"31f8be61dda743cc9810f166e43d42b6","pickNo":"BJSQ2019062802483700001","repairId":"2780e0065f524c6a90020ec6bcd11ab1","pickUser":"智斌","createTime":"2019-06-28 14:48:38","updateTime":"2019-06-28 14:48:38","status":"VALID","delFlag":"0","details":null,"repair":{"id":"2780e0065f524c6a90020ec6bcd11ab1","repairCode":"WX2019061903115600001","deviceId":"d97bc0609a234d90891df185d2f3e987","createBy":null,"createTime":"2019-06-28 14:48:38","dicId":null,"dicName":null,"content":"             安徽段华师大","imgUrl":null,"status":"VALID","delFlag":"0","repairUser":null,"repairResult":null,"repairProcess":null,"reportUser":"智斌","reportTime":"2019-06-22 08:00:00","repairTime":null,"acceptTime":"2019-07-17 22:15:54","deviceVo":null}},
     * {"id":"f2c9f31e89634a378f1d9f7f123f1295","pickNo":"BJSQ2019062703434500001","repairId":"340bdc65c37f40659c88e4aec85684fb","pickUser":null,"createTime":"2019-06-27 15:43:45","updateTime":null,"status":null,"delFlag":"0","details":null,"repair":{"id":"340bdc65c37f40659c88e4aec85684fb","repairCode":"WX2019061703195000009","deviceId":"f7191dfca6c04d15bc52550a235c015b","createBy":null,"createTime":"2019-06-27 15:43:45","dicId":null,"dicName":null,"content":"风扇不可用","imgUrl":null,"status":null,"delFlag":"0","repairUser":null,"repairResult":null,"repairProcess":null,"reportUser":"赵六","reportTime":"2019-06-14 22:25:31","repairTime":null,"acceptTime":null,"deviceVo":null}}]
     * pageSize : 20
     * pageNo : 1
     * totalPage : 1
     * totalCount : 5
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
         * id : 554eb9e5655542dd8100551644f3964b
         * pickNo : BJSQ2019072311581600001
         * repairId : 4ad4bfeb815841cd9e9c84e7ccf4de34
         * pickUser : 打印机
         * createTime : 2019-07-23 11:58:17
         * updateTime : 2019-07-23 11:58:17
         * status : VALID
         * delFlag : 0
         * details : null
         * repair : {"id":"4ad4bfeb815841cd9e9c84e7ccf4de34","repairCode":"WX2019061902163600001","deviceId":"e5d4bcd31d2f4f7eb653e3b7d72efdc8","createBy":null,"createTime":"2019-07-23 11:58:17","dicId":null,"dicName":null,"content":"打印机            ","imgUrl":null,"status":"VALID","delFlag":"0","repairUser":"张三","repairResult":"砸了","repairProcess":"修不了","reportUser":"打印机","reportTime":"2019-06-23 08:00:00","repairTime":"2016-01-01 08:00:00","acceptTime":null,"deviceVo":null}
         */

        private String id;
        private String pickNo;
        private String repairId;
        private String pickUser;
        private String createTime;
        private String updateTime;
        private String status;
        private String delFlag;
        private Object details;
        private RepairBean repair;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPickNo() {
            return pickNo;
        }

        public void setPickNo(String pickNo) {
            this.pickNo = pickNo;
        }

        public String getRepairId() {
            return repairId;
        }

        public void setRepairId(String repairId) {
            this.repairId = repairId;
        }

        public String getPickUser() {
            return pickUser;
        }

        public void setPickUser(String pickUser) {
            this.pickUser = pickUser;
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

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }

        public RepairBean getRepair() {
            return repair;
        }

        public void setRepair(RepairBean repair) {
            this.repair = repair;
        }

        public static class RepairBean {
            /**
             * id : 4ad4bfeb815841cd9e9c84e7ccf4de34
             * repairCode : WX2019061902163600001
             * deviceId : e5d4bcd31d2f4f7eb653e3b7d72efdc8
             * createBy : null
             * createTime : 2019-07-23 11:58:17
             * dicId : null
             * dicName : null
             * content : 打印机
             * imgUrl : null
             * status : VALID
             * delFlag : 0
             * repairUser : 张三
             * repairResult : 砸了
             * repairProcess : 修不了
             * reportUser : 打印机
             * reportTime : 2019-06-23 08:00:00
             * repairTime : 2016-01-01 08:00:00
             * acceptTime : null
             * deviceVo : null
             */

            private String id;
            private String repairCode;
            private String deviceId;
            private Object createBy;
            private String createTime;
            private Object dicId;
            private Object dicName;
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

            public Object getDicId() {
                return dicId;
            }

            public void setDicId(Object dicId) {
                this.dicId = dicId;
            }

            public Object getDicName() {
                return dicName;
            }

            public void setDicName(Object dicName) {
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
}
