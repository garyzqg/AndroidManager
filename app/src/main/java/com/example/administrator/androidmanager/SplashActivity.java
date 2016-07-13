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
        iv.setBackgroundResource(R.drawable.draw);
        AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
        anim.start();
        new Thread(this).start();
        mHandler = new MyHandler();
    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                Intent in = new Intent(SplashActivity.this,MainActivity.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
