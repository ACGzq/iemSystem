package com.thok.iem.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.thok.iem.R;

public class GoodsInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView goods_info_text,title_text;
    private View detailed_list_view;
    private boolean showDetailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationOnClickListener((View view)->{if(showDetailed){
            detailed_list_view.setVisibility(View.GONE);
        }else{
            this.finish();
        }});
        goods_info_text = findViewById(R.id.goods_info_text);
        findViewById(R.id.item_detailed).setOnClickListener(this);
        title_text = findViewById(R.id.title_text);
        detailed_list_view = findViewById(R.id.detailed_list_view);
        goods_info_text.setText("领料单号：334343434\n申请时间：2029/2/3\n申报人：施瓦辛格\n报修编号：007" +
                "\n报修设备：阿姆斯特朗回旋加速喷气式阿姆斯特朗炮\n报修人：托尼史塔克\n报修时间：2012/12/12\n报修内容：");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_detailed:
                detailed_list_view.setVisibility(View.VISIBLE);


        }
    }
}
