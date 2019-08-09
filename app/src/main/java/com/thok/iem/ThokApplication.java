package com.thok.iem;

import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.utils.SharedPreferencesUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class ThokApplication extends Application {
    public static String requestToken= "";
    public static String userName= "";
    public static String realName;
    public static boolean isPhone;

    @Override
    public void onCreate() {
        Log.d("iem_ThokApplication","ThokApplication_start");
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("iem_OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20000,TimeUnit.MILLISECONDS)
            .writeTimeout(20000,TimeUnit.MILLISECONDS)
            .connectTimeout(20000,TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor);
        OkGo.getInstance().init(this)
            .setOkHttpClient(builder.build())
            .setRetryCount(1);//全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        ZXingLibrary.initDisplayOpinion(this);
        isPhone = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
        RequestURLs.setHostUrl(SharedPreferencesUtil.getInstance(this).getString("APIHost",RequestURLs.getHostUrl()));
        super.onCreate();
    }

}
