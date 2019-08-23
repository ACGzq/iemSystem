package com.thok.iem.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.thok.iem.LoginActivity;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.ErrCode;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.FilterHistory;
import com.thok.iem.model.GoodsBean;
import com.thok.iem.model.MaintenanceBean;
import com.thok.iem.model.MaintenanceResponse;
import com.thok.iem.model.PickListRequest;
import com.thok.iem.model.PickListResponse;
import com.thok.iem.model.RepairTaskRequest;
import com.thok.iem.model.RepairTaskResponse;
import com.thok.iem.model.SearchMaintenanceRequest;
import com.thok.iem.model.SearchPageRequest;
import com.thok.iem.model.SpareBean;
import com.thok.iem.model.SpareResponse;
import com.thok.iem.utils.AutoFilterListAdapter;
import com.thok.iem.utils.DataBaseHelp;
import com.thok.iem.utils.QuickAdapter;
import com.thok.iem.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskInquiryActivity extends BaseActivity implements QuickAdapter.OnItemClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    private TextView titleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SearchView input_edit;
    private ArrayList<Object> list = new ArrayList<>();
    private ArrayList<MaintenanceBean> maintenanceBeanArrayList = new ArrayList<>();
    private ArrayList<GoodsBean> goodsBeanArrayList = new ArrayList<>();
    //private ArrayList<GoodsBean> goodsBeanArrayList = new ArrayList<>();
    private QuickAdapter recycleAdapter;
    private AutoFilterListAdapter historyAdapter;
    private Intent intent;
    private String[] mStrs = {"thok"};
    private ListView mListView;
    private String queryString;
    private int presentPage = 0;
    private int taskType = 0;
    private View example_show;

    //private boolean needHint = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_inquiry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        //引入依附的布局
        taskType = getIntent().getIntExtra(TASK_TYPE,0);
        titleView = findViewById(R.id.inquiry_title);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(()-> getData(input_edit.getQuery().toString()));
        example_show = findViewById(R.id.example_show);
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
        recycleAdapter = new QuickAdapter<Object>(list){
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item_with_title;
            }

            @Override
            public void convert(QuickAdapter.QuickVH holder, Object data, int position) {
                holder.getConvertView().setTag("default");
                if(data instanceof String){
                    holder.setText(R.id.item_text,(String)data);
                    holder.getView(R.id.item_title).setVisibility(View.GONE);
                    holder.getConvertView().setTag("get_more");
                }else if(data instanceof MaintenanceBean){
                    holder.setText(R.id.item_text,String.format("设备编号:%s %n保养项目: %s %n保养周期: %s %n更换周期: %s %n养护人: %s %n",
                           getVaule(((MaintenanceBean)data).getDeviceNum()),getVaule(((MaintenanceBean)data).getProgrem()),getVaule(((MaintenanceBean)data).getDays()),
                            getVaule(((MaintenanceBean)data).getChangeDays()),getVaule(((MaintenanceBean)data).getMaintenancer())));
                    holder.getView(R.id.item_title).setVisibility(View.VISIBLE);
                    holder.setText(R.id.item_title,((MaintenanceBean)data).getDeviceName());
                }else if(data instanceof SpareBean){
                    holder.setText(R.id.item_text,String.format("备件编号:%s %n备件数量: %s %n规格型号: %s %n计量单位: %s %n供应商: %s %n",
                            getVaule(((SpareBean)data).getSpareNo()),((SpareBean)data).getNumber(),getVaule(((SpareBean)data).getSpecifications()),
                            getVaule(((SpareBean)data).getUnit()),getVaule(((SpareBean)data).getSupplier())));
                    holder.getView(R.id.item_title).setVisibility(View.VISIBLE);
                    holder.setText(R.id.item_title,((SpareBean)data).getSpareName());
                }else if(data instanceof RepairTaskResponse.DataBean){
                    RepairTaskResponse.DataBean dataBean = (RepairTaskResponse.DataBean) data;
                    holder.getView(R.id.item_title).setVisibility(View.VISIBLE);
                    holder.setText(R.id.item_title,dataBean.getRepairCode());
                    holder.setText(R.id.item_text,String.format("设备编号:%s %n设备位置: %s %n设备名称: %s %n报修人: %s %n报修时间: %s %n报修内容: %s %n",
                            dataBean.getDeviceVo().getDeviceNo(),dataBean.getDeviceVo().getPosition(),dataBean.getDeviceVo().getDeviceName(),dataBean.getReportUser(),dataBean.getReportTime(),dataBean.getContent()));
                }else if(data instanceof PickListResponse.DataBean){
                    PickListResponse.DataBean dataBean = (PickListResponse.DataBean) data;
                   // holder.getView(R.id.item_title).setVisibility(View.VISIBLE);
                   // holder.setText(R.id.item_title,dataBean.getPickNo());
                    holder.setText(R.id.item_text,String.format("领料单号：%s %n申请时间：%s %n申请人：%s %n",dataBean.getPickNo(),dataBean.getCreateTime(),dataBean.getPickUser()));
                }else{
                    holder.setText(R.id.item_text,"position = "+position);
                }

            }

            @Override
            public int getItemViewType(int position) { return super.getItemViewType(position);}
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
        initUi(getIntent().getIntExtra(TASK_TYPE,0));

    }

    @Override
    protected void onDestroy() {
        historyAdapter.destiry();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        input_edit.clearFocus();
        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                startActivity(new Intent(this,ApplyGoodsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(taskType != TASK_SEEK_GOODS && taskType != TASK_MAINTAIN && taskType != TASK_SEARCH_REPAIR && taskType != TASK_REPAIR_REPORT){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.action_menu_submit, menu);
            if(getIntent().getIntExtra(TASK_TYPE,0) == TASK_MATERIAL_APPLY){
                menu.getItem(0).setTitle("申请");
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
    private void getData(String str){
        getData(str,0);
    }
    private void getData(String str,int page) {
        queryString = str;
        String url,jsonParamsStr;
        switch (getIntent().getIntExtra(TASK_TYPE,0)){
            case TASK_REPAIR_REPORT://维修任务
            case TASK_SEARCH_REPAIR:
                url = RequestURLs.getUrlDeviceRepairTask();
                RepairTaskRequest repairTaskRequest = new RepairTaskRequest();
                repairTaskRequest.setKeyword(str);
                repairTaskRequest.setToken(ThokApplication.requestToken);
                if(page>0){
                    repairTaskRequest.setPageNo(String.valueOf(page));
                }
                repairTaskRequest.setPageSize("10");
                OkGo.<RepairTaskResponse>post(url)
                        .tag(this)
                        .upJson(repairTaskRequest.toJsonString())
                        .execute(new OkGoJsonCallback<RepairTaskResponse>(swipeRefreshLayout) {
                            @Override
                            public void onErrorMessage(String str, int code) {
                                if(code == ErrCode.tokenExpired){
                                    compelLogOut();
                                }else{
                                    Toast.makeText(TaskInquiryActivity.this,str,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccess(Response<RepairTaskResponse> response) {
                                    List<RepairTaskResponse.DataBean> dataBeans = response.body().getData();
                                    saveHistory(str);
                                    if(page<1){
                                        list.clear();
                                    }
                                    presentPage = response.body().getPageNo();
                                    dataBeans.forEach(dataBean -> list.add(dataBean));

                                if(response.body().getTotalCount()>10 && page<response.body().getTotalPage()){

                                    list.add("点击获得更多");
                                }

                                recycleAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case TASK_MAINTAIN://保养任务
                 url = RequestURLs.getUrlSearchMaintenance();
                SearchMaintenanceRequest searchMaintenanceRequest = new SearchMaintenanceRequest();
                if(page>0){
                    searchMaintenanceRequest.setPageNo(String.valueOf(page));
                }
                searchMaintenanceRequest.setPageSize("10");
                searchMaintenanceRequest.setToken(ThokApplication.requestToken);
              //  Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");//是否是数字
                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");//是否有汉字
                if(TextUtils.isEmpty(str)){
                    Toast.makeText(this,"未检测搜索内容，启用默认搜索",Toast.LENGTH_SHORT).show();
                }else if(p.matcher(str).find()){
                    searchMaintenanceRequest.setDeviceName(str);
                }else{
                    searchMaintenanceRequest.setDeviceNo(str);
                }
                jsonParamsStr = searchMaintenanceRequest.toJsonString();

                OkGo.<MaintenanceResponse>post(url)
                        .tag(this)
                        .upJson(jsonParamsStr)
                        .execute(new OkGoJsonCallback<MaintenanceResponse>(swipeRefreshLayout) {
                            @Override
                            public void onErrorMessage(String str,int code) {
                                if(code == ErrCode.tokenExpired){
                                    compelLogOut();
                                }else{
                                    Toast.makeText(TaskInquiryActivity.this,str,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccess(Response<MaintenanceResponse> response) {
                                saveHistory(str);
                                List<MaintenanceBean> maintenanceList = response.body().getData();
                                if(maintenanceList!=null){
                                    if(page<1){
                                        list.clear();
                                    }
                                    for (MaintenanceBean maintenanceBean:maintenanceList){
                                        list.add(maintenanceBean);
                                    }
                                    if(response.body().getTotalCount()>10){
                                        list.add("点击获得更多");
                                    }
                                }
                                recycleAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case TASK_SEEK_GOODS://设备选择
                SearchPageRequest searchPageRequest = new SearchPageRequest();
                searchPageRequest.setToken(ThokApplication.requestToken);
                if(TextUtils.isEmpty(str)){
                    searchPageRequest.setStatus("0");
                }else if (str.getBytes().length == str.length()) {//无汉字
                    searchPageRequest.setSpareNo(str);
                } else {//有汉字
                    searchPageRequest.setSpareName(str);
                }

                if(page>0){
                    searchPageRequest.setPageNo(String.valueOf(page));
                }
                searchPageRequest.setPageSize("10");
                OkGo.<SpareResponse>post(RequestURLs.getUrlSpareSearch())
                        .tag(this)
                        .upJson(searchPageRequest.toJsonString())
                        .execute(new OkGoJsonCallback<SpareResponse>(swipeRefreshLayout) {
                            @Override
                            public void onErrorMessage(String str, int code) {
                                if(code == ErrCode.tokenExpired){
                                    compelLogOut();
                                }else{
                                    Toast.makeText(TaskInquiryActivity.this,str,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccess(Response<SpareResponse> response) {
                                saveHistory(str);
                                List<SpareBean> spareList = response.body().getData();
                                if(spareList!=null){
                                    if(page<1){
                                        list.clear();
                                    }
                                    for (SpareBean spareBean:spareList){
                                        list.add(spareBean);
                                    }
                                    if(response.body().getTotalCount()>10){
                                        list.add("点击获得更多");
                                    }
                                }
                                recycleAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case TASK_MATERIAL_APPLY://领料申请
                PickListRequest pickListRequest = new PickListRequest();
                pickListRequest.setToken(ThokApplication.requestToken);
                if(str != null){
                    pickListRequest.setPickNo(str);
                }
                if(page>0){
                    pickListRequest.setPageNo(String.valueOf(page));
                }
                pickListRequest.setPageSize("10");
                OkGo.<PickListResponse>post(RequestURLs.getUrlSparePickList())
                        .tag(this)
                        .upJson(pickListRequest.toJsonString())
                        .execute(new OkGoJsonCallback<PickListResponse>(swipeRefreshLayout) {
                            @Override
                            public void onErrorMessage(String str, int code) {
                                if(code == ErrCode.tokenExpired){
                                    compelLogOut();
                                }else{
                                    Toast.makeText(TaskInquiryActivity.this,str,Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccess(Response<PickListResponse> response) {
                                saveHistory(str);
                                if(page<1){
                                    list.clear();
                                }
                                response.body().getData().forEach((obj)->list.add(obj));
                                if(response.body().getTotalCount()>10){
                                    list.add("点击获得更多");
                                }
                                recycleAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            default:
                list.clear();
                for(int i=0;i<10;i++){
                    MaintenanceBean maintenanceBean = new MaintenanceBean();
                    maintenanceBean.setChangeDays("72");
                    maintenanceBean.setDays("36");
                    maintenanceBean.setDeviceName("领料单");
                    maintenanceBean.setMaintenancer("坂田银时");
                    maintenanceBean.setProgrem("万事屋");
                    maintenanceBean.setDeviceNum("9543");
                    list.add(maintenanceBean);
                }
                recycleAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
        }
    }
    public void initUi(int type){
        switch (type){
            case TASK_PATROL://设备巡检
            titleView.setText(R.string.thok_patrol);
            input_edit.setQueryHint("输入设备名称或编号");
            intent = new Intent(this,TaskSubmitActivity.class);
            intent.putExtra(TASK_TYPE,type);
            break;
            case TASK_MATERIAL_APPLY://领料申请
                titleView.setText(R.string.title_receiving_materials);
                getData("");
                input_edit.setQueryHint("输入完整领料单号");
               intent = new Intent(this,GoodsInfoActivity.class);
               intent.putExtra(TASK_TYPE,type);
                if(SharedPreferencesUtil.getInstance(this).getBoolean("is_first_apply"+ThokApplication.userName,true)){
                    example_show.setVisibility(View.VISIBLE);
                    findViewById(R.id.example_show).setOnClickListener(v -> {example_show.setVisibility(View.GONE);
                    SharedPreferencesUtil.getInstance(this).setBoolean("is_first_apply"+ThokApplication.userName,false);});
                }
                break;

            case TASK_REPAIR_REPORT://维修任务
                intent = new Intent(this,TaskSubmitActivity.class);
                intent.putExtra(TASK_TYPE,type);
            case TASK_SEARCH_REPAIR:
                input_edit.setQueryHint("输入设备名称或编号");
                titleView.setText(R.string.thok_repair_task);
                getData("");
                break;
            case TASK_MAINTAIN://保养任务
                input_edit.setQueryHint("输入设备名称或编号");
                intent = new Intent(this,TaskSubmitActivity.class);
                intent.putExtra(TASK_TYPE,type);
                titleView.setText(R.string.thok_maintain_task);
                getData("");
                break;
            case TASK_SEEK_GOODS://备件选择
                input_edit.setQueryHint("输入备件名称或编号");
                titleView.setText("备件选择");
                findViewById(R.id.input_title).setVisibility(View.VISIBLE);
                String seek = getIntent().getStringExtra(KEY_WORD_SEEK);
                printLog(tag,"seek = "+seek);
                input_edit.setQuery(seek,true);
                break;
             default:
        }
    }
    /**
     * 展示列表的item点击
     * */
    @Override
    public void onItemClick(View view, int position) {
        if(list.get(position) instanceof String){
            list.remove(list.size()-1);
            getData(queryString,presentPage+1);
        }else if(intent != null){
            intent.putExtra("item",position);
            if(taskType == TASK_REPAIR_REPORT){
                RepairTaskResponse.DataBean dataBean = (RepairTaskResponse.DataBean) list.get(position);
                intent.putExtra("DeviceName",dataBean.getDeviceVo().getDeviceName());
                intent.putExtra("DevicePosition",dataBean.getDeviceVo().getPosition());
                intent.putExtra("ReportContent",dataBean.getContent());
                intent.putExtra("ReportCode",dataBean.getRepairCode());
                intent.putExtra("ReportId",dataBean.getId());
            }else if(taskType == TASK_MAINTAIN){
                MaintenanceBean maintenanceBean = (MaintenanceBean) list.get(position);
                intent.putExtra("DeviceNum",maintenanceBean.getDeviceNum());
                intent.putExtra("Progrem",maintenanceBean.getProgrem());
                intent.putExtra("DeviceName",maintenanceBean.getDeviceName());
                intent.putExtra("MaintainId",maintenanceBean.getId());
                intent.putExtra("Days",maintenanceBean.getDays());
                intent.putExtra("ChangeDays",maintenanceBean.getChangeDays());
            }else if(taskType == TASK_MATERIAL_APPLY){
                PickListResponse.DataBean dataBean = (PickListResponse.DataBean) list.get(position);
                intent.putExtra("PickId",getVaule(dataBean.getId()));
                intent.putExtra("PickNo",getVaule(dataBean.getPickNo()));
                intent.putExtra("CreateTime",getVaule(dataBean.getCreateTime()));
                intent.putExtra("PickUser",getVaule(dataBean.getPickUser()));
                intent.putExtra("RepairId",getVaule(dataBean.getRepairId()));
                intent.putExtra("RepairDeviceId",getVaule(dataBean.getRepair().getDeviceId()));
                intent.putExtra("RepairUser",getVaule(dataBean.getRepair().getRepairUser()));
                intent.putExtra("RepairCreateTime",getVaule(dataBean.getRepair().getCreateTime()));
                intent.putExtra("RepairContent",getVaule(dataBean.getRepair().getContent()));
            }
            startActivityForResult(intent,0);
        }else if(taskType == TASK_SEARCH_REPAIR){
            RepairTaskResponse.DataBean dataBean = (RepairTaskResponse.DataBean) list.get(position);
            Intent searchiResultIntent = new Intent();
            searchiResultIntent.putExtra("ReportCode",dataBean.getRepairCode());
            searchiResultIntent.putExtra("ReportId",dataBean.getId());
            searchiResultIntent.putExtra("ReportContent",dataBean.getContent());
            setResult(TASK_SEARCH_REPAIR,searchiResultIntent);
            finish();
        }else{
            mListView.clearTextFilter();
            historyAdapter.getFilter().filter("");
            mListView.setVisibility(View.GONE);
            new AlertDialog.Builder(this)
                    .setTitle("确定所选吗")
                    .setNegativeButton("确定",((dialog, which) ->{
                        Intent intent = getIntent();
                        SpareBean spareBean = (SpareBean)list.get(position);
                        intent.putExtra(INQUIRY_RESULT_DATA_NAME,spareBean.getSpareName());
                        intent.putExtra(INQUIRY_RESULT_DATA_SPECIFICATIONS,spareBean.getSpecifications());
                        intent.putExtra(INQUIRY_RESULT_DATA_NUMBER,spareBean.getNumber());
                        intent.putExtra(INQUIRY_RESULT_DATA_ID,spareBean.getId());
                        setResult(ON_ITEM_SELECTED,intent);
                        finish();} ))
                    .setPositiveButton("取消",(dialog, which)->{})
                    .show();
        }


    }
    private String getVaule(String str){
        if(TextUtils.isEmpty(str)){
            str = "(空)";
        }
        return str;
    }
    /**
     * 展示列表的item长点击
     * */
    @Override
    public boolean onItemLongClick(View view, int position) {
        return false;
    }
    /*
     *  搜索框输入监听
     **/
    @Override
    public boolean onQueryTextSubmit(String s) {
        //queryString = null;
        //queryString.equals("ss");
        getData(s);
        mListView.clearTextFilter();
        historyAdapter.getFilter().filter("");
        mListView.setVisibility(View.GONE);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        printLog(tag,newText);
        if(newText!=null && !newText.isEmpty()){
            historyAdapter.getFilter().filter(newText);
            mListView.setVisibility(View.VISIBLE);
        }else{
            mListView.clearTextFilter();
            historyAdapter.getFilter().filter("");
            mListView.setVisibility(View.GONE);
        }
        return false;
    }

    /**
     * 自动匹配列表的item点击
     * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        input_edit.setQuery((CharSequence) historyAdapter.getItem(position),false);
        mListView.clearTextFilter();
        historyAdapter.getFilter().filter("");
        printLog(tag,"onItemClick");
        mListView.setVisibility(View.GONE);
    }
    /**
     * 自动匹配列表的item长点击
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){//把操作放在用户点击的时候
            View v = getCurrentFocus();                 //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if(isShouldHideKeyboard(v,ev)){//判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
              /*  mListView.clearTextFilter();
                historyAdapter.getFilter().filter("");
                mListView.setVisibility(View.GONE);*/
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public void saveHistory(String str){
        if(TextUtils.isEmpty(str))
            return;
        DataBaseHelp dbHelp = new DataBaseHelp(this, FilterHistory.class);
        SQLiteDatabase dataBase = dbHelp.getWritableDatabase();
        dataBase.delete(FilterHistory.class.getSimpleName(),"historyStr like ?",new String[]{str});
        dataBase.delete(FilterHistory.class.getSimpleName(),"id = ?",new String[]{"10"});
        ContentValues values = new ContentValues();
        values.put("historyStr",str);
        dataBase.insert(FilterHistory.class.getSimpleName(),null,values);
        SQLiteDatabase readable = dbHelp.getReadableDatabase();
        Cursor cursor = readable.rawQuery("select * from FilterHistory",new String[]{""});
        cursor.getCount();
        if(cursor.moveToFirst()){
            ArrayList<String> list = new ArrayList<>();
            list.add(cursor.getString(cursor.getColumnIndex("historyStr")));
           while (cursor.moveToNext()){
               list.add(cursor.getString(cursor.getColumnIndex("historyStr")));
           }
           historyAdapter.setDataList(list);
        }

    }
}
