package com.thok.iem.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.thok.iem.R;
import com.thok.iem.model.Component;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;

public class ApplyActivity extends BaseActivity implements View.OnClickListener {
   private static String Tag = "iem_ApplyActivity";
   private RecyclerView componentRecyclerView;
   private QuickAdapter componentAdapter;
   private boolean delMode = false;
   private ArrayList<Component> componentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        findViewById(R.id.image_add).setOnClickListener(this);
        findViewById(R.id.image_remove).setOnClickListener(this);
        componentRecyclerView = findViewById(R.id.list_view);
        componentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        componentAdapter = new QuickAdapter<Component>(componentList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item;
            }

            @Override
            public void convert(QuickVH holder, Component data, int position) {
                    holder.setText(R.id.item1,position+"");
                    holder.setText(R.id.item2,data.getEqiopmentName());
                    holder.setText(R.id.item3,data.getModelType());
                    holder.setText(R.id.item4,data.getAmount()+"");
                    View del_btn = holder.getView(R.id.del_btn);
                    del_btn.setOnClickListener((view)-> {componentList.remove(position);
                        componentAdapter.notifyDataSetChanged();});
                    del_btn.setVisibility(delMode?View.VISIBLE:View.GONE);
            }
        };
        componentRecyclerView.setAdapter(componentAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                Toast.makeText(this,"提交",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_submit, menu);
        menu.getItem(0).setTitle("提交");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_add:
                startActivityForResult(new Intent(this,AddGoodsActivity.class),0);
                break;
            case R.id.image_remove:
                delMode = !delMode;
                componentAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(Tag,"resultCode="+resultCode);
        if(resultCode == TASK_DONE){
            Component component = new Component();
            component.setEqiopmentName(data.getStringExtra(Component.KEY_WORD_COMPONENT_NAME));
            component.setModelType(data.getStringExtra(Component.KEY_WORD_MODEL_TYPE));
            component.setAmount(data.getIntExtra(Component.KEY_WORD_AMOUNT,0));
            componentList.add(component);
            componentAdapter.notifyDataSetChanged();
        }
        delMode = false;
        componentAdapter.notifyDataSetChanged();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
