package com.thok.iem.model;

import com.google.gson.Gson;

public abstract class BaseRequest {

    /**
     * token : 1
     */

    private String token;

    public String toJsonString(){
       return new Gson().toJson(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
