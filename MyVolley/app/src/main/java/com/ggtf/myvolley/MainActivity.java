package com.ggtf.myvolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private NetworkImageView imageView;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        启动Volley的线程请求队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        imageView = (NetworkImageView) findViewById(R.id.network_image_view);

//        图片加载器
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache = new LruCache<>(30);
            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });
    }

    public void sendRequest(View view) {
        /**
         * 参数1:网址
         * 参数2:网络请求成功,接收数据的接口
         * 参数3:网络请求失败,处理错误的接口
         */
        StringRequest request = new StringRequest(
                "http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
//                     TODO 处理数据
//                        Log.i("Info",s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
//                     TODO 处理错误

                    }
                }
        );
//        Volley 发起网络请求,全部都是通过requestQueue.add(Request);
        requestQueue.add(request);
//        设置网络图片,自动加载
        String url = "http://10.2.156.34:8080/heart.png";
        imageView.setImageUrl(url,imageLoader);
    }
}
