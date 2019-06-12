package com.thok.iem.model;

public class LoginResponse extends BaseResponse{
    private UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
