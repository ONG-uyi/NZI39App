package com.nzy39.nzi39app.Net;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;



public class ImageLoader {
    private static  final ImageLoader inatance = new ImageLoader();
    private Handler mHandler;
    private ImageLoader(){
        mHandler = new Handler(Looper.getMainLooper());
    }
    public static ImageLoader getInstance(){
        return inatance;
    }

    public void load(final ImageView iconIV, final String thumbPath) {
        iconIV.setTag(thumbPath);
        iconIV.setBackgroundColor(Color.GRAY);
        HttpProxy.getInstance().load(thumbPath, new HttpProxy.NetInputCallback() {
            @Override
            public void onSuccess(InputStream inputStream) {

                final Bitmap bitmap = NetInputUtils.readImg(inputStream);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String tag = (String) iconIV.getTag();
                        if(tag.equals(thumbPath)) {
                            iconIV.setImageBitmap(bitmap);
                        }else {

                        }
                    }
                });

            }
        });
    }
}
