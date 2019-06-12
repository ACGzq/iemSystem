package com.thok.iem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thok.iem.R;
import com.thok.iem.model.Component;

import java.util.regex.Pattern;

public class AddGoodsActivity extends BaseActivity implements View.OnClickListener {
    private TextView name_text,model_text,quantity_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        Toolbar toolbar = findViewById(R.id.add_goods_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        findViewById(R.id.search_bt).setOnClickListener(this);
        name_text = findViewById(R.id.name_text);
        model_text = findViewById(R.id.model_text);
        quantity_text = findViewById(R.id.quantity_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_submit,menu);
        menu.getItem(0).setTitle("确定");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                if(TextUtils.isEmpty(name_text.getText())){
                    Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
                }else{
                    getIntent().putExtra(Component.KEY_WORD_COMPONENT_NAME,name_text.getText().toString());
                    getIntent().putExtra(Component.KEY_WORD_MODEL_TYPE,model_text.getText().toString());
                    String mumber = quantity_text.getText().toString();
                    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                    mumber = pattern.matcher(mumber).matches()?mumber:"0";
                    getIntent().putExtra(Component.KEY_WORD_AMOUNT,Integer.parseInt(mumber));
                    setResult(TASK_DONE,getIntent());
                    finish();
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_bt:
                Intent intent = new Intent(this,TaskInquiryActivity.class);
                intent.putExtra(TASK_TYPE, TASK_SEEK_GOODS);
                intent.putExtra(KEY_WORD_SEEK, name_text.getText().toString());
                startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == ON_ITEM_SELECTED){
            name_text.setText(data.getStringExtra(INQUIRY_RESULT_DATA));
            model_text.setText("dL");
            quantity_text.setText("2");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
