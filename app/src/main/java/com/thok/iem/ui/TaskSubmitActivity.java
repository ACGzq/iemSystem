package com.thok.iem.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.ErrCode;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.AddRepairDeviceRequest;
import com.thok.iem.model.BaseResponse;
import com.thok.iem.model.DeviceBean;
import com.thok.iem.model.DicTypeRequest;
import com.thok.iem.model.DicTypeResponse;
import com.thok.iem.model.MaintenancerHistoryAddRequest;
import com.thok.iem.model.MaintenancerHistoryAddResponse;
import com.thok.iem.model.PollingAddRequest;
import com.thok.iem.model.PollingAddResponse;
import com.thok.iem.model.RepairFinishRequest;
import com.thok.iem.model.RepairFinishResponse;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class TaskSubmitActivity extends BaseActivity implements View.OnClickListener {
    private static String tag = "TaskSubmitActivity";
    private TextView submitTitle,equipment_title,equipment_info,status_text,time_title,time_text,time_title2,time_text2,
        input_title,input_title2,input_title3,input_title4,input_text1,input_text2,input_text3,input_text4,status_title;
    private View status_item,equipment_scan,select_icon,repair_time,time_button,request_time,time_button2,input_layout2,input_layout3,input_layout4;
    private EditText equipmentEdit;
    private DateTimePicker dataPicker;
    private ArrayList<String> itemList = new ArrayList<>();
    private DeviceBean deviceBean;
    private HashMap<String,String> operationMap = new HashMap<>();
    private boolean flag;
    private  int taskType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
        taskType = getIntent().getIntExtra(TASK_TYPE,1001);
        status_title = findViewById(R.id.status_title);
        submitTitle = findViewById(R.id.submit_title);
        equipment_title = findViewById(R.id.equipment_title);
        equipmentEdit = findViewById(R.id.equipment_edit);
        equipmentEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                search(equipmentEdit.getText().toString());
                return true;
            }
            return false;
        });
        status_item = findViewById(R.id.status_item);
        equipment_scan = findViewById(R.id.equipment_scan);
        equipment_info = findViewById(R.id.equipment_info);
        select_icon = findViewById(R.id.select_icon);
        status_text = findViewById(R.id.status_text);
        repair_time = findViewById(R.id.repair_time);
        time_title = findViewById(R.id.time_title);
        time_text = findViewById(R.id.time_text);
        time_button = findViewById(R.id.time_button);
        request_time = findViewById(R.id.request_time);
        time_title2 = findViewById(R.id.time_title2);
        time_text2 = findViewById(R.id.time_text2);
        time_button2 = findViewById(R.id.time_button2);
        input_layout2 = findViewById(R.id.input_layout2);
        input_layout3 = findViewById(R.id.input_layout3);
        input_layout4 = findViewById(R.id.input_layout4);
        input_title = findViewById(R.id.input_title);
        input_title2 = findViewById(R.id.input_title2);
        input_title3 = findViewById(R.id.input_title3);
        input_title4 = findViewById(R.id.input_title4);
        input_text1 = findViewById(R.id.input_text1);
        input_text2 = findViewById(R.id.input_text2);
        input_text3 = findViewById(R.id.input_text3);
        input_text4 = findViewById(R.id.input_text4);
        initUI(taskType);
        setListener();
     }

    private void search(String s) {
        Toast.makeText(this,"获取数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        boolean isInputNumber = false;
        switch (v.getId()){
            case R.id.select_icon:
                OptionPicker picker = new OptionPicker(this,itemList);
                picker.setTitleText("状态选择");
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        status_text.setText(item);
                    }
                });
                picker.show();
                break;
                case R.id.time_button:
                    this.dataPicker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (year, month, day, hour, minute) -> time_text.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":00"));
                    dataPicker.show();
                break;
                case R.id.time_button2:
                    this.dataPicker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (year, month, day, hour, minute) -> time_text2.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":00"));
                    dataPicker.show();
                break;
            case R.id.input_text2:
            case R.id.input_text3:
                isInputNumber = true;
            case R.id.input_text1:
            case R.id.input_text4:
                showInputDialog((TextView) v,isInputNumber);
                break;
            case R.id.equipment_scan:
                startSoftScan();
            default:
        }
    }

    private void setListener() {
        select_icon.setOnClickListener(this);
        time_button.setOnClickListener(this);
        time_button2.setOnClickListener(this);
        input_text1.setOnClickListener(this);
        input_text2.setOnClickListener(this);
        input_text3.setOnClickListener(this);
        input_text4.setOnClickListener(this);
        equipment_scan.setOnClickListener(this);
    }
    public void initDicData(){
        if(dicMap.isEmpty()){
            itemList.add("无数据");
        }else{
            Set<String> keys = dicMap.keySet();
            itemList.clear();
            keys.forEach((key)->itemList.add(key));
            //status_text.setText("维修");

        }
    }
    public void initUI(int type){
        dataPicker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        dataPicker.setDateRangeStart(2019, 1, 1);
        dataPicker.setDateRangeEnd(2049, 11, 11);
        dataPicker.setTimeRangeStart(0, 0);
        dataPicker.setTimeRangeEnd(23, 59);
        // DicTypeRequest dicTypeRequest = new DicTypeRequest();
       //dicTypeRequest.setToken(ThokApplication.requestToken);
       // dicTypeRequest.setDicType("SBZT0001");
        switch (type){
            case TASK_REPAIR_REPORT://维修上报
                submitTitle.setText(getString(R.string.thok_rpair_report));
                equipment_title.setText(getIntent().getStringExtra("ReportCode"));
                equipment_info.setText(String.format(" 设备位置：%s %n 设备名称：%s %n 报修内容：%s %n ",
                        getIntent().getStringExtra("DevicePosition"),getIntent().getStringExtra("DeviceName"),getIntent().getStringExtra("ReportContent")));
                status_item.setVisibility(View.GONE);
                equipmentEdit.setVisibility(View.GONE);
                equipment_scan.setVisibility(View.GONE);
                request_time.setVisibility(View.GONE);
                input_title.setText("维修结果");
                input_title4.setText("维修步骤");
                input_layout3.setVisibility(View.GONE);
                input_layout2.setVisibility(View.GONE);
                return;
            case TASK_REPAIR_REQUEST://设备报修
                submitTitle.setText(getString(R.string.thok_equipment_repair_request));
                equipment_info.setText(" 设备编号： \n 设备名称： \n 规格型号： \n 存放位置： ");
                repair_time.setVisibility(View.GONE);
                time_text2.setText(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                input_title.setText("报修原因");
                input_layout2.setVisibility(View.GONE);
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                break;
            case TASK_MAINTAIN://保养实施
                submitTitle.setText(getString(R.string.thok_maintain_start));
                equipment_info.setText(String.format(" 设备编号：%s %n 保养项目：%s %n 操作人：%s %n ",
                        getIntent().getStringExtra("DeviceNum"),getIntent().getStringExtra("Progrem"),ThokApplication.realName));
                equipmentEdit.setVisibility(View.GONE);
                equipment_scan.setVisibility(View.GONE);
                equipment_title.setText(getIntent().getStringExtra("DeviceName"));
                status_title.setText("操作类型");
                time_title.setText("操作时间");
                time_title2.setText("下次操作时间");
                input_title.setText("操作内容");
                input_layout2.setVisibility(View.GONE);
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                itemList.clear();
                operationMap.clear();
                itemList.add("保养");
                operationMap.put("保养","0");
                itemList.add("更换");
                operationMap.put("更换","1");
                return;
            case TASK_PATROL://设备巡检
                submitTitle.setText(getString(R.string.thok_patrol));
                equipment_info.setText(" 设备编号：123 \n 设备名称：123 \n 存放位置：123 ");
                repair_time.setVisibility(View.GONE);
                request_time.setVisibility(View.GONE);
                input_title.setText("检查要求");
                input_title2.setText("正常上限");
                input_title3.setText("正常下限");
                input_title4.setText("计量单位");
                break;
            default:
        }
        initDicData();
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(INTENT_FILTER_ACTION);
        registerReceiver(myBroadcastReceiver,filter);
        super.onStart();
    }

    @Override
    protected void onResume() {
        printLog(tag,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        printLog(tag,"onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(INTENT_FILTER_ACTION);
        registerReceiver(myBroadcastReceiver,filter);
        super.onRestart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(myBroadcastReceiver);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_submit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String dicId = dicMap.getOrDefault(status_text.getText().toString(),"");
        String content = input_text1.getText().toString().trim();
        String content2 = input_text2.getText().toString().trim();
        String content3 = input_text3.getText().toString().trim();
        String content4 = input_text4.getText().toString().trim();
        switch (item.getItemId()) {
            case R.id.action_submit:
            if(deviceBean == null && (taskType == TASK_REPAIR_REQUEST || taskType == TASK_PATROL)){
                new AlertDialog.Builder(this)
                        .setTitle("无设备")
                        .setNegativeButton("确定",(dialog, which)->{}).show();
            }else if(TextUtils.isEmpty(status_text.getText().toString()) && taskType!=TASK_REPAIR_REPORT){
                new AlertDialog.Builder(this)
                        .setTitle("设备状态未选择")
                        .setNegativeButton("确定",(dialog, which)->{}).show();
            }else{
                switch (taskType){
                    case TASK_REPAIR_REQUEST:
                        if(TextUtils.isEmpty(content)){
                            new AlertDialog.Builder(this)
                                    .setTitle("报修原因为空")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else{
                            new AlertDialog.Builder(this)
                                    .setTitle("确认提交吗")
                                    .setNegativeButton("提交",(dialog, which)-> addRepairDevice(status_text.getText().toString(),dicId,time_text2.getText().toString(),content))
                                    .setPositiveButton("取消",(dialog, which)->{}).show();
                        }
                        break;
                    case TASK_PATROL://巡检
                        if(TextUtils.isEmpty(content)){
                            new AlertDialog.Builder(this)
                                    .setTitle("检查要求未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else if(TextUtils.isEmpty(content2)){
                            new AlertDialog.Builder(this)
                                    .setTitle("正常上限未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else if(TextUtils.isEmpty(content3)){
                            new AlertDialog.Builder(this)
                                    .setTitle("正常下限未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else if(TextUtils.isEmpty(content4)){
                            new AlertDialog.Builder(this)
                                    .setTitle("计量单位未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else{
                            new AlertDialog.Builder(this)
                                    .setTitle("确认提交吗")
                                    .setNegativeButton("提交",(dialog, which)->  pollingAdd(dicId,content,content2.replaceAll("\n|\r",""),
                                            content3.replaceAll("\n|\r",""),content4.replaceAll("\n|\r","")))
                                    .setPositiveButton("取消",(dialog, which)->{}).show();
                        }
                        break;
                    case TASK_REPAIR_REPORT:
                        if(TextUtils.isEmpty(content)){
                            new AlertDialog.Builder(this)
                                    .setTitle("维修结果未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else if(TextUtils.isEmpty(content4)){
                            new AlertDialog.Builder(this)
                                    .setTitle("维修步骤未填写")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }else{
                            new AlertDialog.Builder(this)
                                    .setTitle("确认提交吗")
                                    .setNegativeButton("提交",(dialog, which)->  RepairFinish(content,time_text.getText().toString(),content4))
                                    .setPositiveButton("取消",(dialog, which)->{}).show();
                        }
                    break;
                    case TASK_MAINTAIN:
                        String data1 = time_text.getText().toString().trim();
                        String data2 = time_text2.getText().toString().trim();
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date d1 = sf.parse(data1);// 日期转换为时间戳
                            Date d2 = sf.parse(data2);// 日期转换为时间戳
                            if(d1.getTime()>=d2.getTime()){
                                new AlertDialog.Builder(this)
                                        .setTitle("下次操作时间不得小于当前操作时间")
                                        .setNegativeButton("确定",(dialog, which)->{}).show();
                            }else if(TextUtils.isEmpty(content)){
                                new AlertDialog.Builder(this)
                                        .setTitle("操作内容未填写")
                                        .setNegativeButton("确定",(dialog, which)->{}).show();
                            }else{
                                new AlertDialog.Builder(this)
                                        .setTitle("确认提交吗")
                                        .setNegativeButton("提交",(dialog, which)->  maintenancerHistoryAdd(operationMap.getOrDefault(status_text.getText().toString(),""),data1,data2,content))
                                        .setPositiveButton("取消",(dialog, which)->{}).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            new AlertDialog.Builder(this)
                                    .setTitle("日期格式错误")
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }

                }
            }
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void maintenancerHistoryAdd(String operationType,String time,String nextTime,String content){

        showProgressDialog("通信中");
        String url = RequestURLs.getUrlMaintenanceHistoryAdd();
        MaintenancerHistoryAddRequest maintenancerHistoryAddRequest = new MaintenancerHistoryAddRequest();
        maintenancerHistoryAddRequest.setToken(ThokApplication.requestToken);
        maintenancerHistoryAddRequest.setDicId(dicMap.get("保养"));
        maintenancerHistoryAddRequest.setUpdateTime(time);
        maintenancerHistoryAddRequest.setNextOperationTime(nextTime);
        maintenancerHistoryAddRequest.setOperation(operationType);
        maintenancerHistoryAddRequest.setOperationContent(content);
        maintenancerHistoryAddRequest.setMaintenancerId(getIntent().getStringExtra("MaintainId"));
        maintenancerHistoryAddRequest.setDeviceName(getIntent().getStringExtra("DeviceName"));
        maintenancerHistoryAddRequest.setDeviceNum(getIntent().getStringExtra("DeviceNum"));
        maintenancerHistoryAddRequest.setMaintenancer(ThokApplication.realName);
        maintenancerHistoryAddRequest.setProgrem(getIntent().getStringExtra("Progrem"));
        maintenancerHistoryAddRequest.setDays(getIntent().getStringExtra("Days"));
        maintenancerHistoryAddRequest.setChangeDays(getIntent().getStringExtra("ChangeDays"));
        OkGo.<MaintenancerHistoryAddResponse>post(url)
                .tag(this)
                .upJson(maintenancerHistoryAddRequest.toJsonString())
                .execute(new OkGoJsonCallback<MaintenancerHistoryAddResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        hidtProgressDialog();
                        if(code == ErrCode.tokenExpired){
                            compelLogOut();
                        }else{
                            new AlertDialog.Builder(TaskSubmitActivity.this)
                                    .setTitle("提交失败"+str)
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }
                    }

                    @Override
                    public void onSuccess(Response<MaintenancerHistoryAddResponse> response) {
                        hidtProgressDialog();
                        new AlertDialog.Builder(TaskSubmitActivity.this)
                                .setTitle("提交成功")
                                .setPositiveButton("返回上一页",(dialog, which)->{
                                    setResult(TASK_DONE);
                                    finish();
                                })
                                .setNegativeButton("确定",(dialog, which)->{}).show();
                    }
                });
    }
    private void RepairFinish(String content ,String time,String result){
        showProgressDialog("通信中");
        String url = RequestURLs.getUrlDeviceRepairFinish();
        RepairFinishRequest repairFinishRequest = new RepairFinishRequest();
        repairFinishRequest.setToken(ThokApplication.requestToken);
        repairFinishRequest.setId(getIntent().getStringExtra("ReportId"));
        repairFinishRequest.setRepairProcess(content);
        repairFinishRequest.setRepairResult(result);
        repairFinishRequest.setRepairUser(ThokApplication.realName);
        repairFinishRequest.setRepairTime(time);
        OkGo.<RepairFinishResponse>post(url)
                .tag(this)
                .upJson(repairFinishRequest.toJsonString())
                .execute(new OkGoJsonCallback<RepairFinishResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        hidtProgressDialog();
                        if(code == ErrCode.tokenExpired){
                            compelLogOut();
                        }else{
                            new AlertDialog.Builder(TaskSubmitActivity.this)
                                    .setTitle("提交失败"+str)
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }
                    }

                    @Override
                    public void onSuccess(Response<RepairFinishResponse> response) {
                        hidtProgressDialog();
                        new AlertDialog.Builder(TaskSubmitActivity.this)
                                .setTitle("提交成功")
                                .setPositiveButton("返回上一页",(dialog, which)->{
                                    setResult(TASK_DONE);
                                    finish();
                                })
                                .setNegativeButton("确定",(dialog, which)->TaskSubmitActivity.this.finish()).show();
                    }
                });
    }
    private void pollingAdd(String dicId,String content,String upLimit,String downLimit,String meterUnit){
        showProgressDialog("通信中");
        PollingAddRequest pollingAddRequest = new PollingAddRequest();
        pollingAddRequest.setContent(content);
        pollingAddRequest.setDeviceId(deviceBean.getId());
        pollingAddRequest.setToken(ThokApplication.requestToken);
        pollingAddRequest.setPollingUser(ThokApplication.realName);
        pollingAddRequest.setDownLimit(downLimit);
        pollingAddRequest.setUpLimit(upLimit);
        pollingAddRequest.setMeterUnit(meterUnit);
        OkGo.<PollingAddResponse>post(RequestURLs.getUrlDevicePollingAdd())
                .tag(this)
                .upJson(pollingAddRequest.toJsonString())
                .execute(new OkGoJsonCallback<PollingAddResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        hidtProgressDialog();
                        if(code == ErrCode.tokenExpired){
                            compelLogOut();
                        }else{
                            new AlertDialog.Builder(TaskSubmitActivity.this)
                                    .setTitle("提交失败"+str)
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }
                    }
                    @Override
                    public void onSuccess(Response<PollingAddResponse> response) {
                        hidtProgressDialog();
                        new AlertDialog.Builder(TaskSubmitActivity.this)
                                .setTitle("提交成功")
                                .setPositiveButton("返回上一页",(dialog, which)->{
                                    setResult(TASK_DONE);
                                    finish();
                                })
                                .setNegativeButton("确定",(dialog, which)->{}).show();
                    }
                });

    }
    private void addRepairDevice(String status,String dicId,String time,String content){
        showProgressDialog("通信中");
        AddRepairDeviceRequest addRepairDeviceRequest = new AddRepairDeviceRequest();
        addRepairDeviceRequest.setToken(ThokApplication.requestToken);
        addRepairDeviceRequest.setReportUser(ThokApplication.userName);
        addRepairDeviceRequest.setReportTime(time_text2.getText().toString());
        addRepairDeviceRequest.setDeviceId(deviceBean.getId());
        addRepairDeviceRequest.setDicId(dicId);
        addRepairDeviceRequest.setDicName(status);
        addRepairDeviceRequest.setContent(content);
        addRepairDeviceRequest.setReportTime(time);
        OkGo.<BaseResponse>post(RequestURLs.getUrlAddRepairDevice())
                .tag(this)
                .upJson(addRepairDeviceRequest.toJsonString())
                .execute(new OkGoJsonCallback<BaseResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        hidtProgressDialog();
                        if(code == ErrCode.tokenExpired){
                            compelLogOut();
                        }else{
                            new AlertDialog.Builder(TaskSubmitActivity.this)
                                    .setTitle("报修失败"+str)
                                    .setNegativeButton("确定",(dialog, which)->{}).show();
                        }

                    }

                    @Override
                    public void onSuccess(Response<BaseResponse> response) {
                        hidtProgressDialog();
                        new AlertDialog.Builder(TaskSubmitActivity.this)
                                .setTitle("报修成功")
                                .setPositiveButton("返回上一页",(dialog, which)->{
                                    setResult(TASK_DONE);
                                    finish();
                                })
                                .setNegativeButton("确定",(dialog, which)->{}).show();
                    }
                });
    }


    private void showInputDialog(final TextView view,boolean isOnlyNumber) {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(this);
        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);
        if(isOnlyNumber)
            editText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
            else
            editText.setHeight(metric.heightPixels/2);
        //editText.setWidth(metric.widthPixels/10*9);
        editText.setGravity(Gravity.TOP);
        editText.setText(view.getText());
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle("请输入").setView(editText);
        inputDialog.setPositiveButton("确定",
                (dialog, which) -> {view.setText(editText.getText());
                    hideKeyboard(select_icon.getWindowToken());})
                .setNegativeButton("取消",((dialog, which) ->hideKeyboard(select_icon.getWindowToken()))).show();
    }
    public void startSoftScan(){
        if(ThokApplication.isPhone){
            if(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)){
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, TASK_QR_SCANER);
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},TASK_PERMISSION_REQUEST);
            }

        }else{
            flag = true;
            Intent i = new Intent();
            String softScanTrigger = "com.symbol.datawedge.api.ACTION_SOFTSCANTRIGGER";
            String extraData = "com.symbol.datawedge.api.EXTRA_PARAMETER";
            // set the action to perform
            i.setAction(softScanTrigger);
            // add additional info
            i.putExtra(extraData, flag?"START_SCANNING":"TOGGLE_SCANNING");
            printLog(tag,"startSoftScan");
            this.sendBroadcast(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == TASK_PERMISSION_REQUEST){
            boolean flag = true;
            int i = 0;
            while (flag && i<grantResults.length){
                flag = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                i++;
            }
            if(flag){
                startSoftScan();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == TASK_QR_SCANER && data!=null){
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                printLog(tag,"nothing");
                return;
            }
            createDeviceBean(bundle.getString(CodeUtils.RESULT_STRING));
        }else{
            printLog(tag,"nothing");
        }
    }
    public void createDeviceBean(String str){
        printLog(tag,str);
        try {
            deviceBean = new Gson().fromJson(str,DeviceBean.class);
            equipmentEdit.setText(deviceBean.getId());
            equipment_info.setText(String.format("设备编号：%s %n设备名称：%s %n规格型号： %s %n存放位置：%s %n ",
                    deviceBean.getDeviceNo(),deviceBean.getDeviceName(),deviceBean.getSpecificationType(),deviceBean.getPositionName()));
        } catch (Exception e) {
            //  Catch if the UI does not exist when we receive the broadcast... this is not designed to be a production app
            e.printStackTrace();
            Toast.makeText(TaskSubmitActivity.this,"数据解析异常",Toast.LENGTH_SHORT).show();
        }
    }
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(INTENT_FILTER_ACTION.equals(action)) {
                //  Received a barcode scan
                printLog("iem_homeActivity","decodedData =" + intent.getStringExtra("com.symbol.datawedge.data_string"));
                String str = intent.getStringExtra("com.symbol.datawedge.data_string");
                // str = "{\"buyingTime\":1557474255000,\"deviceName\":\"高达独角兽\",\"deviceNo\":\"YCDEVICE2019072203232900001\",\"id\":\"1febc9135c6b41199bedadae4e7833f6\",\"imgUrl\":\"/\",\"position\":\"{9,3}\",\"specificationType\":\"{}\",\"status\":0}";
                createDeviceBean(str);
            }
        }
    };
}
