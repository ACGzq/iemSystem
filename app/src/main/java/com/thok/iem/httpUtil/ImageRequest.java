package com.thok.iem.httpUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;

public class ImageRequest {
    private String defImageUrl = "http://img3.imgtn.bdimg.com/it/u=2871975485,322242574&fm=26&gp=0.jpg";
    private Context context;
    static ImageRequest imageRequest;
    private ImageRequest(Context context){
        this.context = context;
    }
    public static ImageRequest getRequest(Context context){
        if(imageRequest == null){
            imageRequest = new ImageRequest(context);
        }
        return imageRequest;
    }
    public void getImage(String url, ImageView view){
        if(TextUtils.isEmpty(url) || "/".equals(url))
            url = defImageUrl;
        OkGo.<Bitmap>get(url)
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .cacheTime(3600*8)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        if(view!=null || response.body()!=null)
                            view.setImageBitmap(response.body());
                    }
                });
    }

}
