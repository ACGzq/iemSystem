package com.thok.iem.ui;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thok.iem.R;

public class ForgetPassWordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener((View view)->ForgetPassWordActivity.this.finish());
    }
}
