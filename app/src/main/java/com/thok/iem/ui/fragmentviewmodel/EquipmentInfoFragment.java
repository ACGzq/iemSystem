package com.thok.iem.ui.fragmentviewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.thok.iem.R;
import com.thok.iem.httpUtil.ImageRequest;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.DeviceBean;
import com.thok.iem.model.DevicesResponse;
import com.thok.iem.model.SearchDeviceRequestBean;
import com.thok.iem.utils.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EquipmentInfoFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private ArrayList<DeviceBean> list = new ArrayList<>();
    QuickAdapter adapter;
    Context context;
    private OnListFragmentInteractionListener mListener;
    boolean flag;
    private SearchView input_edit;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String equipmentInfoString = "设备编号:%s %n规格型号: %s %n存放位置:%s %n设备状态:%s";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EquipmentInfoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EquipmentInfoFragment newInstance(int columnCount) {
        EquipmentInfoFragment fragment = new EquipmentInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        flag = false;
        View rootView = inflater.inflate(R.layout.fragment_equiment_info_list, container, false);
        rootView.findViewById(R.id.search_bt).setOnClickListener(this);
        input_edit = rootView.findViewById(R.id.edit_text);
        input_edit.onActionViewExpanded();
        input_edit.setSubmitButtonEnabled(true);
        ImageView im = input_edit.findViewById(R.id.search_go_btn);
        im.setImageResource(R.drawable.ic_center_focus_weak_black_24dp);
        im.setOnClickListener(this);
        input_edit.setOnQueryTextListener(this);
        swipeRefreshLayout = rootView.findViewById(R.id.info_list_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> search(input_edit.getQuery().toString()));
        context = rootView.getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuickAdapter<DeviceBean>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.list_item_with_image;
            }

            @Override
            public void convert(QuickVH holder, DeviceBean data, int position) {
                holder.setText(R.id.item_title,data.getDeviceName());
                holder.setText(R.id.item_text,String.format(equipmentInfoString,data.getDeviceNo(),data.getSpecificationType(),data.getPosition(),data.getStatus()==0?"运行":"停机"));
                holder.setText(R.id.state_text,data.getStatus()==0?"运行":"停机");
                ImageRequest.getRequest(context).getImage(data.getImgUrl(),holder.getView(R.id.img_view));

            }

            @Override
            public void onBindViewHolder(@NonNull QuickVH viewHolder, int i) {
                super.onBindViewHolder(viewHolder,i);
            }
        };
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context,"第"+position+"项",Toast.LENGTH_SHORT).show();
                Log.d("iem_EqInfoFragment","position="+position);
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        Log.d("iem_EqInfoFragment","id="+v.getId()+
        "R.id.search_button="+R.id.search_bt);
        switch (v.getId()){
            case R.id.search_go_btn:
            //case R.id.screen_image:
                Log.d("iem_EqInfoFragment","screen_image");
                // define action and data strings
                startSoftScan();
                break;
            case R.id.search_bt:
                search(input_edit.getQuery().toString());
                break;

        }
    }

    public void search(String keyWord){
        list.clear();
        Log.d("iem_EqInfoFragment","search");
        SearchDeviceRequestBean sdrBean = new SearchDeviceRequestBean();
        sdrBean.setDeviceName("堆垛机");
        sdrBean.setDeviceNum(keyWord);
        sdrBean.setToken("1");
        Log.d("iem_EqInfoFragment",sdrBean.toJsonString());
        OkGo.<DevicesResponse>post(RequestURLs.URL_SEARCH_DEVICE)
                .tag(this)
                .upJson(sdrBean.toJsonString())
                .execute(new OkGoJsonCallback<DevicesResponse>() {
                    @Override
                    public void onStart(Request<DevicesResponse, ? extends Request> request) {
                        Log.d("iem_EqInfoFragment", "onStart");
                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                    }

                    @Override
                    public void onSuccess(Response<DevicesResponse> response) {
                        DevicesResponse devices = response.body();
                        Log.d("iem_EqInfoFragment", "onSuccess: "+response.body());
                        if(devices!=null){
                            List<DeviceBean> deviceList = devices.getData();
                            list.clear();
                            for(DeviceBean deviceBean:deviceList){
                                list.add(deviceBean);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<DevicesResponse> response) {
                        Log.d("iem_EqInfoFragment", "onSuccess: "+response.body());
                    }

                    @Override
                    public void onErrorMessage(String str) {
                        if (swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        Log.d("iem_EqInfoFragment", "onFinish");
                        if (swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                   /* @Override
                    public DevicesResponse convertResponse(okhttp3.Response response) throws Throwable {
                        String jsonObjString = response.body().string();
                        Log.d("iem_EqInfoFragment", "convertResponse:"+ jsonObjString);
                       return super.convertResponse(response);
                    }*/
        });

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
        Log.d(getTag(),"startSoftScan");
        getActivity().sendBroadcast(i);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("iem_EqInfoFragment",s);
        search(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }

    public void updataUi(String str){
        input_edit.setQuery(str,true);
    }
}
