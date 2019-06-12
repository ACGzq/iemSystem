package com.thok.iem.httpUtil;

import android.util.Log;

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
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class OkGoJsonCallback<T extends BaseResponse> extends AbsCallback<T> {
    private Type type;
    private Class clazz;
    public OkGoJsonCallback(){}
    public OkGoJsonCallback(Type type){
        this.type = type;
    }
    public OkGoJsonCallback(Class<T> clazz){
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
            throw new IllegalStateException("code = "+data.getCode()+";massage = "+ data.getMessage());
        }
        return data;
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if(exception != null) exception.printStackTrace();
        if(exception instanceof UnknownHostException){
            onErrorMessage("网络连接失败");
        }else if(exception instanceof SocketTimeoutException){
            onErrorMessage("网络请求超时");
        }else if(exception instanceof HttpException){
            onErrorMessage("服务端错误");
        }else if(exception instanceof IllegalStateException){
            onErrorMessage(exception.getMessage());
        }else if(exception instanceof JsonIOException){
            onErrorMessage("json 数据读取失败");
        }else if(exception instanceof JsonSyntaxException){
            onErrorMessage("Json 数据解析失败");
        }
    }
    abstract public void onErrorMessage(String str);
}
