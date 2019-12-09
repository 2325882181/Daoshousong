package com.example.main;

import android.app.Application;
import com.zhy.http.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpClient();     //  初始化OkhttpUtils
    }

    private void initOkhttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
