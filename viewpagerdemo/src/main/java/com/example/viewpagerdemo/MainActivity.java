package com.example.viewpagerdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    ArrayList<View> mArrayList;
    int[] pics = {R.drawable.adware_style_applist, R.drawable.adware_style_banner,R.drawable.adware_style_creditswall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_slidingpage);
        mArrayList = new ArrayList<>();
        //把图片资源放在list里
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(pics[i]);
            mArrayList.add(iv);
        }
        MyPagerAdapter adapter = new MyPagerAdapter(mArrayList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private static final String TAG = "MainActivity";
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当页面滚动时调用
                Log.d(TAG, "onPageScrolled: " + position + "positionOffset" +positionOffset + "positionOffsetPixels" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //当页面被选择时
                Log.d(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //1,开始滚动 2,松手 0,滚动结束
                Log.d(TAG, "onPageScrollStateChanged: " + state);
            }
        });
    }
}
