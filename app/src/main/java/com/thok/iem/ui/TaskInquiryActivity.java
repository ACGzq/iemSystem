package com.thok.iem.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thok.iem.R;
import com.thok.iem.utils.AutoFilterListAdapter;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;

public class TaskInquiryActivity extends BaseActivity implements QuickAdapter.OnItemClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    private TextView titleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SearchView input_edit;
    private ArrayList<String> list = new ArrayList<>();
    private QuickAdapter recycleAdapter;
    private AutoFilterListAdapter historyAdapter;
    private Intent intent;
    private String[] mStrs = {"ACG00011", "AVG00011", "AVG00112", "BBC12345","TNT23333","FBI WARRING"};
    private ListView mListView;
    private boolean needHint = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_inquiry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        //引入依附的布局

        titleView = findViewById(R.id.inquiry_title);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(()->{Toast.makeText(this,"下来刷新",Toast.LENGTH_SHORT).show();
        getData("匡扶汉室是倒车文化的一部分。\n" +
                "倒车文化反映到欧陆就是意大利再造罗马帝国/希腊复兴东罗马（他将如闪电般归来）\n" +
                "/德意志再造帝国（第三帝国）\n" +
                "/英格兰收回英联邦诸国一方面，对于过去的美好岁月，人都是怀旧的。\n" +
                "作者：他化自在-鸠格米西\n" +
                "链接：https://www.zhihu.com/question/326410259/answer/698447314\n" +
                "来源：知乎\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。");});
        recyclerView = findViewById(R.id.recycler_view);
        findViewById(R.id.inquiry_button).setOnClickListener((view)->onQueryTextSubmit(input_edit.getQuery().toString()));
        input_edit = findViewById(R.id.input_edit);
        input_edit.onActionViewExpanded();
        input_edit.setOnQueryTextListener(this);
        //input_edit.setSubmitButtonEnabled(true);
        //((ImageView)input_edit.findViewById(R.id.search_go_btn)).setImageResource(R.drawable.text_bg);
        mListView = findViewById(R.id.list_view);
        historyAdapter = new AutoFilterListAdapter(this, mStrs);
        mListView.setAdapter(historyAdapter);
        mListView.setOnItemClickListener(this);
        //listview启动过滤
        mListView.setTextFilterEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置Adapter
        recycleAdapter = new QuickAdapter<String>(list){
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item_one_text;
            }

            @Override
            public void convert(QuickAdapter.QuickVH holder, String data, int position) {
                holder.setText(R.id.item_text,data);
                holder.getView(R.id.item_title);
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }
        };
        recycleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recycleAdapter);
        //设置分隔线
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private int dividerHeight;
            private Paint dividerPaint;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                dividerHeight = 1;
                outRect.bottom =  dividerHeight;
            }


            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                dividerPaint = new Paint();
                dividerPaint.setColor(Color.GRAY);
                int childCount = parent.getChildCount();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + dividerHeight;
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }
            }
        });
    //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        getData("");
        initUi(getIntent().getIntExtra(TASK_TYPE,0));

    }

    private void getData(String str) {
        list.clear();

        for(int i=0;i<20;i++){
            list.add("备件0000"+i+str);
        }
        recycleAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
               startActivity(new Intent(this,ApplyActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getIntent().getIntExtra(TASK_TYPE,0) == TASK_MATERIAL_APPLY){
            getMenuInflater().inflate(R.menu.action_menu_submit, menu);
            menu.getItem(0).setTitle("申请");
        }
       return super.onCreateOptionsMenu(menu);
    }
    public void initUi(int type){
        switch (type){
            case TASK_MATERIAL_APPLY:
                titleView.setText(R.string.title_receiving_materials);
                getData("领料单号：32323232\n" +
                        "申请人：史蒂夫瑟\n" +
                        "申请时间：2019-05-09");
               intent = new Intent(this,GoodsInfoActivity.class);
               intent.putExtra(TASK_TYPE,type);
                break;
            case TASK_PATROL:
                titleView.setText(R.string.thok_patrol);
                intent = new Intent(this,TaskSubmitActivity.class);
                intent.putExtra(TASK_TYPE,type);
                break;
            case TASK_RPAIR_REPORT:
                intent = new Intent(this,TaskSubmitActivity.class);
                intent.putExtra(TASK_TYPE,type);
                titleView.setText(R.string.thok_repair_task);
                break;
            case TASK_MAINTAIN:
                intent = new Intent(this,TaskSubmitActivity.class);
                intent.putExtra(TASK_TYPE,type);
                titleView.setText(R.string.thok_maintain_task);
                break;
            case TASK_SEEK_GOODS:
                titleView.setText(R.string.thok_equipment_select);
                findViewById(R.id.input_title).setVisibility(View.VISIBLE);
             default:
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if(intent != null){
            intent.putExtra("item",position);
            startActivityForResult(intent,0);
        }else {

            new AlertDialog.Builder(this)
                    .setTitle("确定所选吗")
                    .setNegativeButton("确定",((dialog, which) ->{setResult(ON_ITEM_SELECTED,getIntent().putExtra(INQUIRY_RESULT_DATA,position));
                        finish();} ))
                    .setPositiveButton("取消",(dialog, which)->{})
                    .show();
        }


    }

    @Override
    public boolean onItemLongClick(View view, int position) {
        return false;
    }
    /*
     *  搜索框输入监听
     **/
    @Override
    public boolean onQueryTextSubmit(String s) {
        if(s == null || s.isEmpty()){
            Toast.makeText(this,"请输入查找内容",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(tag,newText);
        if(newText!=null && !newText.isEmpty() && needHint){
            historyAdapter.getFilter().filter(newText);
            mListView.setVisibility(View.VISIBLE);
        }else{
            needHint = true;
            mListView.clearTextFilter();
            historyAdapter.getFilter().filter(newText);
            mListView.setVisibility(View.GONE);
        }
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        needHint = false;
        CharSequence cs = (CharSequence) historyAdapter.getItem(position);
        if(!cs.toString().contains("未发现"))
            input_edit.setQuery((CharSequence) historyAdapter.getItem(position),false);
    }
}
