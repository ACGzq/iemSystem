package com.thok.iem.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.TextView;

import com.thok.iem.R;
import com.thok.iem.model.Goods;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView goods_info_text,title_text;
    private RecyclerView detailed_list_view;
    private View info_detailed;
    private boolean showDetailed;
    private QuickAdapter quickAdapter;
    private List<Goods> list = new ArrayList<>();

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
        quickAdapter = new QuickAdapter<Goods>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item_edging;
            }

            @Override
            public void convert(QuickVH holder, Goods data, int position) {
                holder.setText(R.id.item1,position+1+"");
                holder.setText(R.id.item2,data.getSpareName());
                holder.setText(R.id.item3,data.getSpecifications());
                holder.setText(R.id.item4,data.getNumber());
            }

        };
        detailed_list_view.setAdapter(quickAdapter);
        //detailed_list_view.setAdapter(new QuickAdapter<String>());
        goods_info_text.setText("领料单号：334343434\n申请时间：2029/2/3\n申报人：施瓦辛格\n报修编号：007" +
                "\n报修设备：阿姆斯特朗回旋加速喷气式阿姆斯特朗炮\n报修人：托尼史塔克\n报修时间：2012/12/12\n报修内容：");
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
        list.clear();
        for(int i=0;i<10;i++){
            Goods goods = new Goods();
            goods.setNumber("2");
            goods.setSpareName("神奇四维空间兜");
            goods.setSpecifications("白色");
            list.add(goods);
        }
       quickAdapter.notifyDataSetChanged();
    }
}
