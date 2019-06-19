package com.thok.iem.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
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
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.AddRepairDeviceRequest;
import com.thok.iem.model.BaseResponse;
import com.thok.iem.model.DeviceBean;
import com.thok.iem.model.DicTypeRequest;
import com.thok.iem.model.DicTypeResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class TaskSubmitActivity extends BaseActivity implements View.OnClickListener {
    private static String tag = "TaskSubmitActivity";
    private TextView submitTitle,equipment_title,equipment_info,status_text,time_title,time_text,time_title2,time_text2,
        input_title,input_title2,input_title3,input_title4,input_text1,input_text2,input_text3,input_text4,status_title;
    private View equipment_scan,select_icon,repair_time,time_button,request_time,time_button2,input_layout2,input_layout3,input_layout4;
    private EditText equipmentEdit;
    private DateTimePicker dataPicker;
    private ArrayList<String> itemList = new ArrayList<>();
    private DeviceBean deviceBean;
    private HashMap<String,String> dicMap = new HashMap<>();
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View view)->this.finish());
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

        initUI(getIntent().getIntExtra(TASK_TYPE,1001));
        setListener();
     }

    private void search(String s) {
        Toast.makeText(this,"获取数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
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
                    this.dataPicker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (year, month, day, hour, minute) -> {
                        time_text.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":00");
                    });
                    dataPicker.show();
                break;
                case R.id.time_button2:
                    this.dataPicker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (year, month, day, hour, minute) -> {
                        time_text2.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":00");
                    });
                    dataPicker.show();
                break;
            case R.id.input_text1:
            case R.id.input_text2:
            case R.id.input_text3:
            case R.id.input_text4:
                showInputDialog((TextView) v);
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
    private void showInputDialog(final TextView view) {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(this);
        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);
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
    public void initData(String url,String jsonParams){
        showProgressDialog("请稍等");
        OkGo.<DicTypeResponse>post(url)
                .upJson(jsonParams)
                .execute(new OkGoJsonCallback<DicTypeResponse>() {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        hidtProgressDialog();
                    }
                    @Override
                    public void onSuccess(Response<DicTypeResponse> response) {
                        hidtProgressDialog();
                        DicTypeResponse dicTypeResponse = response.body();
                        DicTypeResponse.DataBean dataBean = dicTypeResponse.getData();
                        if(dataBean!=null){
                            List<DicTypeResponse.DataBean.ListBean> listBeans = dataBean.getList();
                            status_title.setText(dataBean.getTypeDic().getName());
                            if(listBeans!=null && listBeans.size()>0){
                                itemList.clear();
                                dicMap.clear();
                                listBeans.forEach((listBean ->  {
                                    itemList.add(listBean.getName());
                                    dicMap.put(listBean.getName(),listBean.getId());
                                }));
                            }
                        }
                    }
                });
    }
    public void initUI(int type){
        dataPicker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        dataPicker.setDateRangeStart(2015, 1, 1);
        dataPicker.setDateRangeEnd(2035, 11, 11);
        dataPicker.setTimeRangeStart(0, 0);
        dataPicker.setTimeRangeEnd(23, 59);
        switch (type){
            case TASK_RPAIR_REPORT:
                submitTitle.setText(getString(R.string.thok_rpair_report));
                equipment_title.setText("AGV000001");
                equipment_info.setText(" 设备位置：321 \n 故障原因：321 \n 报修内容：321  ");
                equipmentEdit.setVisibility(View.GONE);
                equipment_scan.setVisibility(View.GONE);
                request_time.setVisibility(View.GONE);
                input_title.setText("维修结果");
                input_title2.setText("维修步骤");
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                break;
            case TASK_RPAIR_REQUEST:
                submitTitle.setText(getString(R.string.thok_equipment_repair_request));
                equipment_info.setText(" 设备编号：123 \n 设备名称：123 \n 规格型号：123 \n 存放位置：123 ");
                repair_time.setVisibility(View.GONE);
                time_text2.setText(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                input_title.setText("报修原因");
                input_layout2.setVisibility(View.GONE);
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                DicTypeRequest dicTypeRequest = new DicTypeRequest();
                dicTypeRequest.setToken("1");
                dicTypeRequest.setDicType("SBZT0001");
                initData(RequestURLs.URL_DIC_TYPE_LIST,dicTypeRequest.toJsonString());
                break;
            case TASK_MAINTAIN:
                submitTitle.setText(getString(R.string.thok_maintain_start));
                equipment_info.setText(" 设备编号：233 \n 保养项目：233 \n 操作人：2233 ");
                equipmentEdit.setVisibility(View.GONE);
                equipment_scan.setVisibility(View.GONE);
                equipment_title.setText("AGV000001");
                status_title.setText("操作类型");
                time_title.setText("操作时间");
                time_title2.setText("下次操作时间");
                input_title.setText("操作内容");
                input_layout2.setVisibility(View.GONE);
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                break;
            case TASK_PATROL:
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_submit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
            case R.id.action_submit:
                showProgressDialog("= =");
                AddRepairDeviceRequest addRepairDeviceRequest = new AddRepairDeviceRequest();
                addRepairDeviceRequest.setToken("cvy54y314hj");
                addRepairDeviceRequest.setReportUser("user_name");
                addRepairDeviceRequest.setReportTime(time_text2.getText().toString());
                addRepairDeviceRequest.setDeviceId(deviceBean.getId());
                addRepairDeviceRequest.setDicId(dicMap.getOrDefault(status_text.getText().toString(),""));
                addRepairDeviceRequest.setContent(input_text1.getText().toString());
                OkGo.<BaseResponse>post(RequestURLs.URL_ADD_REPAIR_DEVICE)
                        .tag(this)
                        .upJson(addRepairDeviceRequest.toJsonString())
                        .execute(new OkGoJsonCallback<BaseResponse>() {
                            @Override
                            public void onErrorMessage(String str, int code) {
                                hidtProgressDialog();
                            }

                            @Override
                            public void onSuccess(Response<BaseResponse> response) {
                                hidtProgressDialog();
                                Toast.makeText(TaskSubmitActivity.this,"提交",Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
                default:
        }
        return super.onOptionsItemSelected(item);
    }
    public void startSoftScan(){
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

    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle b = intent.getExtras();

            if(INTENT_FILTER_ACTION.equals(action)) {
                //  Received a barcode scan
                try {
                    // displayScanResult(intent, "via Broadcast");
                    printLog("iem_homeActivity","decodedData =" + intent.getStringExtra("com.symbol.datawedge.data_string"));
                    String str = intent.getStringExtra("com.symbol.datawedge.data_string");
                    str = "{\"buyingTime\":1557474255000,\"deviceName\":\"堆垛机\",\"deviceNo\":\"YCDEVICE2019061410435400002\",\"id\":\"c03fbae6c7ba4add8282adc0190983fa\",\"imgUrl\":\"/\",\"position\":\"{1,2}\",\"specificationType\":\"{}\",\"status\":0}";
                    deviceBean = new Gson().fromJson(str,DeviceBean.class);
                    equipmentEdit.setText(deviceBean.getId());
                    equipment_info.setText(String.format("设备编号：%s %n设备名称：%s %n规格型号： %s %n存放位置：%s %n ",
                            deviceBean.getDeviceNo(),deviceBean.getDeviceName(),deviceBean.getSpecificationType(),deviceBean.getPosition()));
                } catch (Exception e) {
                    //  Catch if the UI does not exist when we receive the broadcast... this is not designed to be a production app
                    e.printStackTrace();
                    Toast.makeText(TaskSubmitActivity.this,"数据解析异常",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
