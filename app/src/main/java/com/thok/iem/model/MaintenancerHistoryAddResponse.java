package com.thok.iem.model;

public class MaintenancerHistoryAddResponse extends BaseResponse {

    /**
     * data : {"id":"38aeca37bff149c38e73a10deb04aca8","createBy":"xiaoming","createTime":null,"updateTime":"2019-07-10 12:20:30","deviceNum":"45612345","deviceName":"堆垛机","progrem":"保养项目","days":"1年","changeDays":"2周","maintenancer":"小白","dr":null,"nextOperationTime":"2019-07-10 12:20:30","operation":0}
     * pageSize : null
     * pageNo : null
     * totalPage : null
     * totalCount : null
     */

    private DataBean data;
    private Object pageSize;
    private Object pageNo;
    private Object totalPage;
    private Object totalCount;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getPageSize() {
        return pageSize;
    }

    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
    }

    public Object getPageNo() {
        return pageNo;
    }

    public void setPageNo(Object pageNo) {
        this.pageNo = pageNo;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    public static class DataBean {
        /**
         * id : 38aeca37bff149c38e73a10deb04aca8
         * createBy : xiaoming
         * createTime : null
         * updateTime : 2019-07-10 12:20:30
         * deviceNum : 45612345
         * deviceName : 堆垛机
         * progrem : 保养项目
         * days : 1年
         * changeDays : 2周
         * maintenancer : 小白
         * dr : null
         * nextOperationTime : 2019-07-10 12:20:30
         * operation : 0
         */

        private String id;
        private String createBy;
        private Object createTime;
        private String updateTime;
        private String deviceNum;
        private String deviceName;
        private String progrem;
        private String days;
        private String changeDays;
        private String maintenancer;
        private Object dr;
        private String nextOperationTime;
        private int operation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public String getNextOperationTime() {
            return nextOperationTime;
        }

        public void setNextOperationTime(String nextOperationTime) {
            this.nextOperationTime = nextOperationTime;
        }

        public int getOperation() {
            return operation;
        }

        public void setOperation(int operation) {
            this.operation = operation;
        }
    }
}
