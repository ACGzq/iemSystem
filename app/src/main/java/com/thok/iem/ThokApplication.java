package com.thok.iem;


import android.app.Application;
import android.content.Context;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class ThokApplication extends Application {
    @Override
    public void onCreate() {

        Log.d("iem_ThokApplication","ThokApplication_start");
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("iem_OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.HEADERS);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30000,TimeUnit.MILLISECONDS)
            .writeTimeout(30000,TimeUnit.MILLISECONDS)
            .connectTimeout(30000,TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor);
        OkGo.getInstance().init(this)
            .setOkHttpClient(builder.build())
            .setRetryCount(1);//全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        super.onCreate();
    }

}
