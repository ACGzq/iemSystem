package com.thok.iem.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.BaseResponse;
import com.thok.iem.model.SparePickBean;
import com.thok.iem.model.SparePickAddRequest;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;

public class ApplyGoodsActivity extends BaseActivity implements View.OnClickListener {
   private static String Tag = "iem_ApplyActivity";
   private RecyclerView componentRecyclerView;
   private QuickAdapter componentAdapter;
   private TextView edit_order,edit_cause;
   private String reportId;
   private boolean delMode = false;
   private ArrayList<SparePickBean> componentList = new ArrayList<>();
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
        edit_order = findViewById(R.id.edit_order);
        edit_order.setOnClickListener(this);
        edit_cause = findViewById(R.id.edit_cause);
        componentRecyclerView = findViewById(R.id.list_view);
        componentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        componentAdapter = new QuickAdapter<SparePickBean>(componentList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item;
            }

            @Override
            public void convert(QuickVH holder, SparePickBean data, int position) {
                    holder.setText(R.id.item1,position+"");
                    holder.setText(R.id.item2,data.getEqiopmentName());
                    holder.setText(R.id.item3,data.getModelType());
                    holder.setText(R.id.item4,data.getNumber()+"");
                    View del_btn = holder.getView(R.id.del_btn);
                    del_btn.setOnClickListener((view)-> {componentList.remove(position);
                    printLog(tag,"OnClick_position=" + position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,getItemCount());});
                    del_btn.setVisibility(delMode?View.VISIBLE:View.GONE);
            }
        };
        componentRecyclerView.setAdapter(componentAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                SparePickAddRequest sparePickAddRequest = new SparePickAddRequest();
                sparePickAddRequest.setCreateBy(ThokApplication.userName);
                sparePickAddRequest.setRepairId(reportId);
                sparePickAddRequest.setSpareList(componentList);
                sparePickAddRequest.setToken(ThokApplication.requestToken);
                Toast.makeText(this,"提交",Toast.LENGTH_SHORT).show();
                showProgressDialog("");
                OkGo.<BaseResponse>post(RequestURLs.getUrlSparePickAdd())
                        .tag(this)
                        .upJson(sparePickAddRequest.toJsonString())
                        .execute(new OkGoJsonCallback<BaseResponse>() {
                            @Override
                            public void onErrorMessage(String str, int code) {
                                hidtProgressDialog();
                                Toast.makeText(ApplyGoodsActivity.this,str,Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onSuccess(Response<BaseResponse> response) {
                                hidtProgressDialog();
                                new AlertDialog.Builder(ApplyGoodsActivity.this)
                                        .setTitle("提交成功")
                                        .setNegativeButton("确定",(dialog, which)->ApplyGoodsActivity.this.finish()).show();
                            }
                        });
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
            case R.id.edit_order:
                Intent intent = new Intent(this,TaskInquiryActivity.class);
                intent.putExtra(TASK_TYPE,TASK_SEARCH_REPAIR);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        printLog(Tag,"resultCode="+resultCode);
        if(resultCode == TASK_DONE){
            SparePickBean sparePickBean = new SparePickBean();
            sparePickBean.setEqiopmentName(data.getStringExtra(INQUIRY_RESULT_DATA_NAME));
            sparePickBean.setModelType(data.getStringExtra(INQUIRY_RESULT_DATA_SPECIFICATIONS));
            sparePickBean.setNumber(data.getIntExtra(INQUIRY_RESULT_DATA_NUMBER,0));
            sparePickBean.setId(data.getStringExtra(INQUIRY_RESULT_DATA_ID));
            componentList.add(sparePickBean);
            componentAdapter.notifyItemRangeChanged(componentList.size()-1,1);
        }else if(resultCode == TASK_SEARCH_REPAIR){
            reportId = data.getStringExtra("ReportId");
            edit_order.setText(data.getStringExtra("ReportCode"));
            edit_cause.setText(data.getStringExtra("ReportContent"));
        }

        if(delMode){
            delMode = false;
            componentAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
