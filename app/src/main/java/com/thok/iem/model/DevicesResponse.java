package com.thok.iem.model;

import java.util.List;

public class DevicesResponse extends BaseResponse{

    /**
     * code : 0
     * message : 成功
     * data : [{"id":"9f329642e5404a72bc4e63ac6275132a","deviceNo":"YCDEVICE2019061005242900005","deviceName":"堆垛机","specificationType":"{}","position":"{1,2}","imgUrl":"/","status":0,"createBy":null,"createTime":"2019-06-10T17:24:29.000+0000","updateTime":"2019-06-10T17:24:29.000+0000","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019061005242900005.jpg","buyingTime":"2019-05-10T07:44:15.000+0000"},{"id":"56208adf92f647feb93c52d6e96c5bf6","deviceNo":"YCDEVICE2019061005464900006","deviceName":"堆垛机","specificationType":"{}","position":"{1,2}","imgUrl":"/","status":0,"createBy":null,"createTime":"2019-06-10T17:46:49.000+0000","updateTime":"2019-06-10T17:46:49.000+0000","dr":0,"codeImgUrl":"http://192.168.1.102:8088/img/code/YCDEVICE2019061005464900006.jpg","buyingTime":"2019-05-10T07:44:15.000+0000"}]
     * pageSize : 20
     * pageNo : 1
     * totalPage : 1
     * totalCount : 2
     */

    private int pageSize;
    private int pageNo;
    private int totalPage;
    private int totalCount;
    private List<DeviceBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public List<DeviceBean> getData() {
        return data;
    }

    public void setData(List<DeviceBean> data) {
        this.data = data;
    }

}
