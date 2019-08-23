package com.thok.iem.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.GoodsBean;
import com.thok.iem.model.PickDetailRequest;
import com.thok.iem.model.PickDetailResponse;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView goods_info_text,title_text;
    private RecyclerView detailed_list_view;
    private View info_detailed;
    private boolean showDetailed;
    private QuickAdapter quickAdapter;
    private List<PickDetailResponse.DataBean.DetailsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationOnClickListener((View view)->changeView());
        goods_info_text = findViewById(R.id.goods_info_text);
        findViewById(R.id.item_detailed).setOnClickListener(this);
        title_text = findViewById(R.id.title_text);
        info_detailed = findViewById(R.id.info_detailed);
        detailed_list_view = findViewById(R.id.detailed_list_view);
        detailed_list_view.setLayoutManager(new LinearLayoutManager(this));
        quickAdapter = new QuickAdapter<PickDetailResponse.DataBean.DetailsBean>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item_edging;
            }

            @Override
            public void convert(QuickVH holder, PickDetailResponse.DataBean.DetailsBean data, int position) {
                holder.setText(R.id.item1,position+1+"");
                holder.setText(R.id.item2,data.getSpare().getSpareName());
                holder.setText(R.id.item3,data.getSpare().getSpecifications());
                holder.setText(R.id.item4,String.valueOf(data.getAmount()));
            }

        };
        detailed_list_view.setAdapter(quickAdapter);
        //detailed_list_view.setAdapter(new QuickAdapter<String>());
        /*
        * intent.putExtra("PickNo",dataBean.getPickNo());
                intent.putExtra("CreateTime",dataBean.getCreateTime());
                intent.putExtra("PickUser",dataBean.getPickUser());
                intent.putExtra("RepairId",dataBean.getRepairId());
                intent.putExtra("RepairUser",dataBean.getRepair().getRepairUser());
                intent.putExtra("RepairCreateTime",dataBean.getRepair().getCreateTime());
                intent.putExtra("RepairContent",dataBean.getRepair().getContent());*/
        goods_info_text.setText(String.format("领料单号：%s %n申请时间：%s %n申报人：%s %n报修编号：%s %n设备ID：%s %n报修人：%s %n报修时间：%s %n报修内容：%s %n",
                getIntent().getStringExtra("PickNo"),getIntent().getStringExtra("CreateTime"),getIntent().getStringExtra("PickUser"),
                getIntent().getStringExtra("RepairId"),getIntent().getStringExtra("RepairDeviceId"),getIntent().getStringExtra("RepairUser"),
                getIntent().getStringExtra("RepairCreateTime"),getIntent().getStringExtra("RepairContent")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_detailed:
                info_detailed.setVisibility(View.VISIBLE);
                showDetailed = true;
                getData();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        changeView();
    }
    private void changeView(){
        if(showDetailed){
            info_detailed.setVisibility(View.GONE);
            showDetailed = false;
        }else{
            this.finish();
        }
    }

    private void getData() {
        showProgressDialog("通信中");
        PickDetailRequest pickDetailRequest = new PickDetailRequest();
        pickDetailRequest.setPickId(getIntent().getStringExtra("PickId"));
        pickDetailRequest.setToken(ThokApplication.requestToken);
        OkGo.<PickDetailResponse>post(RequestURLs.getUrlSparePickDetail())
                .tag(this)
                .upJson(pickDetailRequest.toJsonString())
                .execute(new OkGoJsonCallback<PickDetailResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                       hidtProgressDialog();
                        Toast.makeText(GoodsInfoActivity.this,str,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(Response<PickDetailResponse> response) {
                        hidtProgressDialog();
                        list.clear();
                        response.body().getData().getDetails().forEach((obj)-> list.add(obj));
                        quickAdapter.notifyDataSetChanged();
                    }
                });

    }
}
