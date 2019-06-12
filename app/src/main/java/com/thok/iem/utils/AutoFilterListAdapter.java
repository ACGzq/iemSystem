package com.thok.iem.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.thok.iem.R;

import java.util.ArrayList;
import java.util.List;

public class AutoFilterListAdapter<T> extends BaseAdapter {
    private int resoureId;
    private Context context;
    private List<T> dataList; //这个数据是会改变的，所以要有个变量来备份一下原始数据
    private List<T> backDataList; //用来备份原始数据
    private MyFilter mFilter ;
    public AutoFilterListAdapter(Context context , List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
        backDataList = dataList;
    }
    public AutoFilterListAdapter(Context context , T[] objs) {
        this.context = context;
        this.dataList = new ArrayList<T>();
        for(T s:objs){
            dataList.add(s);
        }
        backDataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_with_title,null);
            holder = new ViewHolder();
            holder.value = convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (holder.value instanceof CharSequence) {
            holder.value.setText((CharSequence) dataList.get(position));
        } else {
            holder.value.setText(dataList.get(position).toString());
        }
        return convertView;
    }
    public Filter getFilter(){
        if(mFilter == null){
            mFilter = new MyFilter();
        }
        return mFilter;
    }
    class ViewHolder {
        TextView value;
    }
    //定义一个过滤器的类来定义过滤规则
    class MyFilter extends Filter {
        //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();
            List<T> list =  new ArrayList<>();
            if (!TextUtils.isEmpty(charSequence)){
                for (T value:backDataList){
                    if (value.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){ //要匹配的item中的view
                    list.add(value);
                    }
                }
                result.count = list.size();//将集合的大小保存到FilterResults的count变量中
            }else{
                result.count = -1;
            }
            result.values = list; //将得到的集合保存到FilterResults的value变量中

            return result;
        }
        //在publishResults方法中告诉适配器更新界面
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataList = (List<T>)filterResults.values;
            if (filterResults.count>0){
                notifyDataSetChanged();//通知数据发生了改变
            }else if(filterResults.count == 0){
                dataList.add((T)(charSequence));
                notifyDataSetChanged();//通知数据发生了改变
            }else {
                notifyDataSetInvalidated();//通知数据失效
            }
        }
    }

}
