package com.thok.iem.ui;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.thok.iem.R;

public class MyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        findViewById(R.id.alter_password).setOnClickListener((View view)->startActivity(new Intent(MyActivity.this,AlterPassActivity.class)));
    }
}
