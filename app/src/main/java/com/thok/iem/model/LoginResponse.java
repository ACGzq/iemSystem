package com.thok.iem.model;

public class LoginResponse extends BaseResponse{
    private Goods data;

    public Goods getData() {
        return data;
    }

    public void setData(Goods data) {
        this.data = data;
    }
}
