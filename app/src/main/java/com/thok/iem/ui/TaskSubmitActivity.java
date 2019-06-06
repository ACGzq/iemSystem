package com.thok.iem.ui;


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thok.iem.R;

import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class TaskSubmitActivity extends BaseActivity implements View.OnClickListener {
    private static String tag = "TaskSubmitActivity";
    private TextView submitTitle,equipment_title,equipment_info,status_text,time_title,time_text,time_title2,time_text2,
        input_title,input_title2,input_title3,input_title4,input_text1,input_text2,input_text3,input_text4,status_title;
    private View equipment_scan,select_icon,repair_time,time_button,request_time,time_button2,input_layout2,input_layout3,input_layout4;
    private EditText equipmentEdit;
    private DateTimePicker dataPicker;

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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_icon:
                OptionPicker picker = new OptionPicker(this,new String[]{"停机","运行","损坏"});
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
                        time_text.setText(year+"/"+month+"/"+day+"-"+hour+":"+minute);
                    });
                    dataPicker.show();
                break;
                case R.id.time_button2:
                    this.dataPicker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (year, month, day, hour, minute) -> {
                        time_text2.setText(year+"/"+month+"/"+day+"-"+hour+":"+minute);
                    });
                    dataPicker.show();
                break;
            case R.id.input_text1:
            case R.id.input_text2:
            case R.id.input_text3:
            case R.id.input_text4:
                showInputDialog((TextView) v);
                break;
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
                (dialog, which) -> view.setText(editText.getText())).setNegativeButton("取消",((dialog, which) -> Log.d(tag,"COLSE"))).show();
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
                equipment_info.setText(" 设备位置：321 \n 故障原因：321 \n 报修内容：321  ");
                request_time.setVisibility(View.GONE);
                input_title.setText("维修结果");
                input_title2.setText("维修步骤");
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
                break;
            case TASK_RPAIR_REQUEST:
                submitTitle.setText(getString(R.string.thok_equipment_repair_request));
                equipment_info.setText(" 设备编号：123 \n 设备名称：123 \n 规格型号：123 \n 存放位置：123 ");
                equipmentEdit.setVisibility(View.GONE);
                equipment_scan.setVisibility(View.GONE);
                equipment_title.setText("AGV000001");
                repair_time.setVisibility(View.GONE);
                input_title.setText("报修原因");
                input_layout2.setVisibility(View.GONE);
                input_layout3.setVisibility(View.GONE);
                input_layout4.setVisibility(View.GONE);
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
                input_title.setText("操作类型");
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
                Toast.makeText(this,"提交",Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return super.onOptionsItemSelected(item);
    }
}
