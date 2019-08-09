package com.thok.iem.ui.fragmentviewmodel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import com.lzy.okgo.model.Response;
import com.thok.iem.R;
import com.thok.iem.ThokApplication;
import com.thok.iem.httpUtil.ErrCode;
import com.thok.iem.httpUtil.ImageRequest;
import com.thok.iem.httpUtil.OkGoJsonCallback;
import com.thok.iem.httpUtil.RequestURLs;
import com.thok.iem.model.DeviceBean;
import com.thok.iem.model.DevicesResponse;
import com.thok.iem.model.DicTypeRequest;
import com.thok.iem.model.DicTypeResponse;
import com.thok.iem.model.SearchDeviceRequest;
import com.thok.iem.ui.BaseActivity;
import com.thok.iem.ui.HomeActivity;
import com.thok.iem.utils.QuickAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class EquipmentInfoFragment extends BaseFragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private ArrayList<DeviceBean> list = new ArrayList<>();
    QuickAdapter adapter;
    Context context;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    boolean flag;
    private SearchView input_edit;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HashMap<String,String> dicMap = new HashMap<>();
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
        im.setImageResource(R.drawable.saoma);
        im.setOnClickListener(this);
        input_edit.setOnQueryTextListener(this);
        swipeRefreshLayout = rootView.findViewById(R.id.info_list_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> search(input_edit.getQuery().toString()));
        context = rootView.getContext();
        recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuickAdapter<DeviceBean>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return viewType==1?R.layout.list_item_with_image:R.layout.list_item_with_title;
            }

            @Override
            public int getItemViewType(int position) {
                return list.get(position).getDr() == -100?0:1;
            }

            @Override
            public void convert(QuickVH holder, DeviceBean data, int position) {
                if(data.getDr() == -100){
                    holder.setText(R.id.item_text,"点击获取更多");
                    holder.getView(R.id.item_title).setVisibility(View.GONE);
                    holder.getConvertView().setTag("get_more");
                }else{
                    holder.getConvertView().setTag("default");
                    holder.setText(R.id.item_title,data.getDeviceName());
                    holder.setText(R.id.item_text,String.format(equipmentInfoString,data.getDeviceNo(),data.getSpecificationType(),data.getPositionName(),dicMap.get(data.getStatusId())));
                    holder.setText(R.id.state_text,dicMap.get(data.getStatusId()));
                    ImageRequest.getRequest(context).loadImage(data.getImgUrl(),holder.getView(R.id.img_view),3);
                }


            }


            @Override
            public void onBindViewHolder(@NonNull QuickVH viewHolder, int i) {
                super.onBindViewHolder(viewHolder,i);
            }
        };
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(context,"第"+position+"项",Toast.LENGTH_SHORT).show();
                printLog("iem_EqInfoFragment","position="+position + "Tag ="+view.getTag());
                if("get_more".equals(view.getTag())){
                    list.remove(list.size()-1);
                    search(input_edit.getQuery().toString(),list.size()/10+1);
                }
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
        return rootView;
    }
    public void initDicDataAndSearch(String keyWord,int page){
        DicTypeRequest dicTypeRequest = new DicTypeRequest();
        dicTypeRequest.setToken(ThokApplication.requestToken);
        dicTypeRequest.setDicType("SBZT0001");

        OkGo.<DicTypeResponse>post(RequestURLs.getUrlDicTypeList())
                .upJson(dicTypeRequest.toJsonString())
                .execute(new OkGoJsonCallback<DicTypeResponse>(swipeRefreshLayout) {
                    @Override
                    public void onErrorMessage(String str, int code) {
                        printLog(getBaseTag(),str);
                        search(keyWord,page);
                    }
                    @Override
                    public void onSuccess(Response<DicTypeResponse> response) {
                        DicTypeResponse dicTypeResponse = response.body();
                        DicTypeResponse.DataBean dataBean = dicTypeResponse.getData();
                        if(dataBean!=null){
                            List<DicTypeResponse.DataBean.ListBean> listBeans = dataBean.getList();
                            if(listBeans!=null && listBeans.size()>0){
                                dicMap.clear();
                                listBeans.forEach((listBean ->  {
                                    dicMap.put(listBean.getId(),listBean.getName());
                                }));
                                printLog("dicMap",dicMap.toString());
                                search(keyWord,page);
                            }
                        }
                    }
                });
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
        printLog("iem_EqInfoFragment","id="+v.getId()+
        "R.id.search_button="+R.id.search_bt);
        switch (v.getId()){
            case R.id.search_go_btn:
            //case R.id.screen_image:
                printLog("iem_EqInfoFragment","screen_image");
                // define action and data strings
                startSoftScan();
                break;
            case R.id.search_bt:
                search(input_edit.getQuery().toString());
                break;

        }
    }
    public void search(String keyWord){
        search(keyWord,0);
    }
    public void search(String keyWord,int page){
        if(dicMap == null || dicMap.size()<1){
            initDicDataAndSearch(keyWord,page);
            return;
        }
       // list.clear();
        printLog("iem_EqInfoFragment","search");
        String jsonParamsStr;
        String url = RequestURLs.getUrlSearchpageDevice();
        SearchDeviceRequest searchDeviceRequest = new SearchDeviceRequest();
        searchDeviceRequest.setToken(ThokApplication.requestToken);
        if(!TextUtils.isEmpty(keyWord)){
            searchDeviceRequest.setDeviceNo(keyWord);
        }
        if(page > 0){
            searchDeviceRequest.setPageNo(String.valueOf(page));
        }
       searchDeviceRequest.setPageSize("10");
        jsonParamsStr = searchDeviceRequest.toJsonString();
        OkGo.<DevicesResponse>post(url)
                .tag(this)
                .upJson(jsonParamsStr)
                .execute(new OkGoJsonCallback<DevicesResponse>(swipeRefreshLayout) {
                    @Override
                    public void onSuccess(Response<DevicesResponse> response) {
                        DevicesResponse devices = response.body();
                        printLog("iem_EqInfoFragment", "onSuccess: "+response.body());
                        List<DeviceBean> deviceList = devices.getData();
                        if(deviceList!=null){
                            if(page<1){
                                list.clear();
                            }
                            for(DeviceBean deviceBean:deviceList){
                                list.add(deviceBean);
                            }
                            if(response.body().getTotalCount()>10 && page<response.body().getTotalPage()){
                                DeviceBean deviceBean = new DeviceBean();
                                deviceBean.setDr(-100);
                                list.add(deviceBean);
                            }
                            adapter.notifyItemRangeChanged(list.size()-deviceList.size(),deviceList.size());
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<DevicesResponse> response) {
                        printLog("iem_EqInfoFragment", "onSuccess: "+response.body());
                    }

                    @Override
                    public void onErrorMessage(String str,int code) {
                        if(code == ErrCode.tokenExpired){
                            ((HomeActivity)getActivity()).compelLogOut();
                        }else{
                            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
                        }
                    }

                   /* @Override
                    public DevicesResponse convertResponse(okhttp3.Response response) throws Throwable {
                        String jsonObjString = response.body().string();
                        printLog("iem_EqInfoFragment", "convertResponse:"+ jsonObjString);
                       return super.convertResponse(response);
                    }*/
        });

    }
    public void startSoftScan(){
        if(ThokApplication.isPhone){
            ((HomeActivity)getActivity()).startQrScan();
        }else{
            flag = true;
            Intent i = new Intent();
            String softScanTrigger = "com.symbol.datawedge.api.ACTION_SOFTSCANTRIGGER";
            String extraData = "com.symbol.datawedge.api.EXTRA_PARAMETER";
            // set the action to perform
            i.setAction(softScanTrigger);
            // add additional info
            i.putExtra(extraData, flag?"START_SCANNING":"TOGGLE_SCANNING");
            printLog(getTag(),"startSoftScan");
            getActivity().sendBroadcast(i);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        printLog("iem_EqInfoFragment",s);
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
        DeviceBean deviceBean = new Gson().fromJson(str,DeviceBean.class);
        if(deviceBean!=null){
            input_edit.setQuery(deviceBean.getDeviceNo(),true);
        }else{
            input_edit.setQuery(str,true);
        }

    }
    
}
