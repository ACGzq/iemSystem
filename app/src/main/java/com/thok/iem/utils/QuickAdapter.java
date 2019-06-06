package com.thok.iem.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.QuickVH> {
    private List<T> mDatas;
    private OnItemClickListener onItemClickListener;
    public QuickAdapter(List<T> datas){
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);
    public abstract void convert(QuickVH holder, T data, int position);
    @NonNull
    @Override
    public QuickVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return QuickVH.createViewHolder(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(QuickVH holder, int position) {

        holder.itemView.setOnClickListener(v -> {
            Log.d("iem",position+"");
            if(onItemClickListener!=null)
            onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
        });
        holder.itemView.setOnLongClickListener(v -> {
            if(onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                return onItemClickListener.onItemLongClick(holder.itemView, pos);
            }
            //表示此事件未经消费，会触发单击事件
            return false;
         });

        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        boolean onItemLongClick(View view, int position);
    }
    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(QuickAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    /**
     * ViewHolder
     * 普片适配器
     * */
    public static class QuickVH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private QuickVH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }
        //实例化方法
        public static QuickVH createViewHolder(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new QuickVH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }

        public void setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
        }
        public View getConvertView(){
            return mConvertView;
        }
    }


}
