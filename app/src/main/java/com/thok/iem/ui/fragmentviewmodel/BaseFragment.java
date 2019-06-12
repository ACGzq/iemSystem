package com.thok.iem.ui.fragmentviewmodel;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.thok.iem.BuildConfig;

public class BaseFragment extends Fragment {
    protected void printLog(String tag,String log){
        if(BuildConfig.DEBUG){
            Log.d(tag,log);
        }
    }
}
