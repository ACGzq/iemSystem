package com.thok.iem.ui.fragmentviewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thok.iem.R;
import com.thok.iem.ui.BaseActivity;
import com.thok.iem.ui.TaskInquiryActivity;
import com.thok.iem.ui.TaskSubmitActivity;

public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    View homeView;
    Activity homeActivity;
    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.home_page_fragment, container, false);
        homeView.findViewById(R.id.repair_request).setOnClickListener(this);
        homeView.findViewById(R.id.patrol).setOnClickListener(this);
        homeView.findViewById(R.id.repair_task).setOnClickListener(this);
        homeView.findViewById(R.id.maintain).setOnClickListener(this);
        homeView.findViewById(R.id.receiving_materials).setOnClickListener(this);

        return homeView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        homeActivity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.repair_request://报修
                 intent = new Intent(homeActivity,TaskSubmitActivity.class);
                intent.putExtra(BaseActivity.TASK_TYPE,BaseActivity.TASK_RPAIR_REQUEST);
                startActivity(intent);
                break;
            case R.id.patrol://巡检
                intent = new Intent(homeActivity,TaskSubmitActivity.class);
                intent.putExtra(BaseActivity.TASK_TYPE,BaseActivity.TASK_PATROL);
                startActivity(intent);
                break;
            case R.id.repair_task://维修任务
                intent = new Intent(homeActivity,TaskInquiryActivity.class);
                intent.putExtra(BaseActivity.TASK_TYPE,BaseActivity.TASK_RPAIR_REPORT);
                startActivity(intent);
                break;
            case R.id.maintain://保养任务
                intent = new Intent(homeActivity,TaskInquiryActivity.class);
                intent.putExtra(BaseActivity.TASK_TYPE,BaseActivity.TASK_MAINTAIN);
                startActivity(intent);
                break;
            case  R.id.receiving_materials://领料
                intent = new Intent(homeActivity,TaskInquiryActivity.class);
                intent.putExtra(BaseActivity.TASK_TYPE,BaseActivity.TASK_MATERIAL_APPLY);
                startActivity(intent);
                break;
            default:
        }
    }
}
