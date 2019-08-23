package com.thok.iem.httpUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;
import com.thok.iem.BuildConfig;
import com.thok.iem.R;

public class ImageRequest {
    private String defImageUrl = "http://img3.imgtn.bdimg.com/it/u=2871975485,322242574&fm=26&gp=0.jpg";
    private Context context;
    int hight = 0;
    int width = 0;
    static ImageRequest imageRequest;
    private ImageRequest(Context context){
        this.context = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        hight = dm.heightPixels;
    }
    public static ImageRequest getRequest(Context context){
        if(imageRequest == null){
            imageRequest = new ImageRequest(context);
        }
        return imageRequest;
    }
    public void loadImage(String url, ImageView view){
        loadImage(url,view,1);
    }
    public void loadImage(String url, ImageView view, int rate){
        if(!TextUtils.isEmpty(url) && (url.toLowerCase().contains("jpg") || url.toLowerCase().contains("png"))){
            if(!url.contains("http")){
                url = RequestURLs.getHostUrl() + url;
            }
            Log.d(context.getClass().getName(),"starLoadImg=");
            OkGo.<Bitmap>get(url)
                    .tag(this)
                    .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                    .cacheTime(3600*8)
                    .execute(new BitmapCallback(width/rate,width*9/(rate*16)) {
                        @Override
                        public void onSuccess(Response<Bitmap> response) {
                            if(view!=null && response.body()!=null)
                                view.setImageBitmap(response.body());
                        }
                    });
        }else{
            view.setImageResource(R.mipmap.ic_default_img);
        }

    }

}
