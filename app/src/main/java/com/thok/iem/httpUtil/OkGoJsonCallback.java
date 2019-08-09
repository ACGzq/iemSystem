package com.thok.iem.httpUtil;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import com.thok.iem.model.BaseResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class OkGoJsonCallback<T extends BaseResponse> extends AbsCallback<T> {
    private Type type;
    private Class clazz;
    private SwipeRefreshLayout swipeRefreshLayout;
    public OkGoJsonCallback(){}
    public OkGoJsonCallback(SwipeRefreshLayout swipeRefreshLayout){
        this.swipeRefreshLayout = swipeRefreshLayout;
    }
    public OkGoJsonCallback(Type type){
        this.type = type;
    }
    public OkGoJsonCallback(Class<T> clazz){
        this.clazz = clazz;
    }
    public OkGoJsonCallback(Type type,SwipeRefreshLayout swipeRefreshLayout){
        this(swipeRefreshLayout);
        this.type = type;
    }
    public OkGoJsonCallback(Class<T> clazz,SwipeRefreshLayout swipeRefreshLayout){
        this(swipeRefreshLayout);
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response){
        ResponseBody body = response.body();
        if(body == null)
            throw new IllegalStateException("响应为空");
        T data = null;
        body.charStream();
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if(type != null){
            data = gson.fromJson(jsonReader,type);
        }else if(clazz != null){
            data = gson.fromJson(jsonReader,clazz);
        }else{
            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType)genType).getActualTypeArguments()[0];
            data = gson.fromJson(jsonReader,type);
        }
        if(data.getCode()!= 0){
            if(data.getCode() == -100400){
                throw new WindowManager.BadTokenException(data.getCode()+"_"+ data.getMessage());
            }else{
                throw new IllegalStateException(data.getCode()+"_"+ data.getMessage());
            }
        }
        return data;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if(swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }
        super.onStart(request);
    }

    @Override
    public void onFinish() {
        if(swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        super.onFinish();
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if(exception != null) exception.printStackTrace();
        if(exception instanceof UnknownHostException){
            onErrorMessage("网络连接失败",ErrCode.UnknownHostErr);
        }else if(exception instanceof SocketTimeoutException){
            onErrorMessage("网络请求超时",ErrCode.NetworkTimeOut);
        }else if(exception instanceof HttpException){
            onErrorMessage("服务器异常",ErrCode.NetworkErr);
        }else if(exception instanceof ConnectException){
            onErrorMessage("网络连接失败",ErrCode.ConnectException);
        }else if(exception instanceof WindowManager.BadTokenException){
            onErrorMessage(exception.getMessage(),ErrCode.tokenExpired);
        }else if(exception instanceof IllegalStateException){
            onErrorMessage(exception.getMessage(),ErrCode.IllegalStateErr);
        }else if(exception instanceof JsonIOException){
            onErrorMessage("json 数据读取失败",ErrCode.JsonIOErr);
        }else if(exception instanceof JsonSyntaxException){
            onErrorMessage("Json 数据解析失败",ErrCode.JsonSyntaxErr);
        }else{
            onErrorMessage("unKnow Err",ErrCode.UnknowErr);
        }
    }
    abstract public void onErrorMessage(String str,int code);
}
