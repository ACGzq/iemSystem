package com.thok.iem.model;

public class BaseResponse {

    /**
     * code : -1
     * message : 该设备已经存在
     * data : {}
     */

    protected int code;
    protected String message;

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
}
