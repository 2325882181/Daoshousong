package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.daoshousong.R;

public class WelcomeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //两秒延迟进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();//关闭当前页面
            }
        }, 2000);
    }
}
