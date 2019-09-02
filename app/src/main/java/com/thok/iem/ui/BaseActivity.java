package com.thok.iem.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.thok.iem.BuildConfig;
import com.thok.iem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseActivity extends AppCompatActivity {
public static ArrayList<Activity> activityArrayList = new ArrayList<>();
    public static final  String TASK_TYPE = "TASK_TYPE";
    public static final  String KEY_WORD_SEEK = "KEY_WORD_SEEK";
    public static final  String INQUIRY_RESULT_DATA_NAME = "INQUIRY_RESULT_DATA_NAME";
    public static final  String INQUIRY_RESULT_DATA_SPECIFICATIONS = "INQUIRY_RESULT_DATA_NAME_SPECIFICATIONS";
    public static final  String INQUIRY_RESULT_DATA_NUMBER = "INQUIRY_RESULT_DATA_NAME_NUMBER";
    public static final  String INQUIRY_RESULT_DATA_ID = "INQUIRY_RESULT_DATA_NAME_ID";
    public static final String INTENT_FILTER_ACTION = "com.thok.iem.action";
    public static final String LAST_LOGIN_USER_ID = "LAST_LOGIN_USER_ID";
    public static final String RESULT_STRING = "+";
    public static final int TASK_PERMISSION_REQUEST = 1314;
    public static final int TASK_QR_SCANER = 10;
    public static final int TASK_DONE = 6666;
    public static final int TASK_CANCEL = 7777;
    public static final int FINISH_APP = 4321;
    public static final  int TASK_REPAIR_REQUEST = 1001;
    public static final  int TASK_REPAIR_REPORT = 1002;
    public static final  int TASK_MAINTAIN = 1003;
    public static final  int TASK_PATROL = 1004;
    public static final  int TASK_MATERIAL_APPLY = 1005;
    public static final  int TASK_SEEK_GOODS = 1006;
    public static final  int ON_ITEM_SELECTED = 1007;
    public static final  int TASK_SEARCH_REPAIR = 1008;
    protected static HashMap<String,String> dicMap;
    protected   String tag = "";
    protected PopupWindow popupWindow;
    protected ProgressDialog mProgressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = "iem"+ getClass().getName();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popup_list_layout, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        //popupWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        activityArrayList.add(this);
    }

    @Override
    protected void onDestroy() {
        activityArrayList.remove(this);
        super.onDestroy();
    }
    public static void removeAllActivity(){
        for(Activity a:activityArrayList){
            a.finish();
        }
    }
    protected void showProgressDialog(String msg){
       mProgressDialog = ProgressDialog.show(this,"",msg,true,false);
    }
    protected void hidtProgressDialog(){
        if(mProgressDialog!=null)
            mProgressDialog.cancel();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){//把操作放在用户点击的时候
            View v = getCurrentFocus();                 //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if(isShouldHideKeyboard(v,ev)){//判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    protected boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }
    protected void compelLogOut(){
        new AlertDialog.Builder(this).setTitle("token过期，必须重新登录").setNegativeButton("确定",(dialog,witch)->removeAllActivity()).show();
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    protected void hideKeyboard(IBinder token) {
        printLog(tag,"token="+token);
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void printLog(String tag,String log){
        if(BuildConfig.DEBUG){
            Log.d(tag,log);
        }
    }
}
