package com.thok.iem.model;

import com.google.gson.Gson;

public class BaseRequest {

    /**
     * token : 1
     */

    private String token;
    private String id;

    public BaseRequest() {
    }

    public BaseRequest(String token) {
        this.token = token;
    }

    public BaseRequest(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public String toJsonString(){
       return new Gson().toJson(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
