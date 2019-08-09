package com.thok.iem.ui;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.thok.iem.R;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.utils.SharedPreferencesUtil;

import java.util.Date;

public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener {
    private long onClickBefore = 0;
    private int onClickTime = 0;
    private View alter_host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener((View view)->ForgetPassWordActivity.this.finish());
        findViewById(R.id.text_view).setOnClickListener(this);
        alter_host = findViewById(R.id.alter_host);
        alter_host.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_view:
            case R.id.title_label:
                long ClickTime = new Date().getTime();
                if(ClickTime - onClickBefore<500){
                    onClickTime++;
                }else{
                    onClickTime = 0;
                }
                onClickBefore = ClickTime;
                if(onClickTime>5){
                    alter_host.setVisibility(View.VISIBLE);
                    onClickTime = 0;
                }
                break;
            case R.id.alter_host:
                showInputDialog();
        }
    }
    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(this);
        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);
        editText.setGravity(Gravity.TOP);
        editText.setText(RequestURLs.getHostUrl());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle("修改接口ip地址").setView(editText);
        inputDialog.setPositiveButton("确定",
                (dialog, which) -> {
                    RequestURLs.setHostUrl(editText.getText().toString());
                    SharedPreferencesUtil.getInstance(this).setString("APIHost",RequestURLs.getHostUrl());
                    this.hideKeyboard(alter_host.getWindowToken());})
                .setNegativeButton("取消",((dialog, which) ->this.hideKeyboard(alter_host.getWindowToken()))).show();
    }
}
