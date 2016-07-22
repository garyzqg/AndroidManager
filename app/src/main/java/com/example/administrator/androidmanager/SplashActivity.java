package com.example.administrator.androidmanager;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private static final String TAG = "SplashActivity";
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView iv = (ImageView) findViewById(R.id.tv_splash);
        //imageView加载帧动画
        iv.setBackgroundResource(R.drawable.draw);
        //开启帧动画
        AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
        anim.start();
        //开启一个线程,动画展示3秒后跳转
        new Thread(this).start();
        mHandler = new MyHandler();
    }
    /*
    Handler
     */
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                Intent in = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(in);
                finish();
            }
        }
    }
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            mHandler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
