package com.thok.iem;

import android.app.Application;
import android.content.Context;

public class ThokApplication extends Application {
    public Context applicationContext;
    public Context getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void onCreate() {
        applicationContext = this;
        super.onCreate();
    }
}
