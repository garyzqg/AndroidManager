package com.example.administrator.androidmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String SP_CONFIG = "sp_config";
    public static final String IS_FIRST_RUN = "isfirstrun";
    ViewPager mViewPager;
    ArrayList<View> mArrayList;
    int[] pics = {R.mipmap.adware_style_applist,R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};
    Button mBtnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成shared Preferences
        //MODE_PRIVATE只读
        SharedPreferences preferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
        //从sp文件中读取key,是否为true,判断是否第一次运行
        boolean isFirstRun = preferences.getBoolean(IS_FIRST_RUN,true);

        //如果不是第一次运行
        if (!isFirstRun){
            startActivity(new Intent(this,SplashActivity.class));
        }else{
            setContentView(R.layout.activity_view_pager);
            //将图片存在集合中,传给适配器
            mViewPager = (ViewPager) findViewById(R.id.vp_slidingPager);
            mArrayList = new ArrayList<>();
            for (int i = 0; i < pics.length; i++) {
                ImageView iv = new ImageView(this);
                iv.setImageResource(pics[i]);
                mArrayList.add(iv);
            }
            mViewPager.setAdapter(new MyPaperAdapter(mArrayList));

            //动画实现
            mViewPager.setPageTransformer(true,new ZoomOutPageTransformer() );

            //第三张图时才显示跳转
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position == 2){
                        mBtnSkip.setVisibility(View.VISIBLE);
                    }else{
                        mBtnSkip.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            mBtnSkip = (Button) findViewById(R.id.btn_skip);
            mBtnSkip.setOnClickListener(this);
        }




    }

    @Override
    public void onClick(View v) {
        //对shared Preferences进行修改
        SharedPreferences preferences = getSharedPreferences(SP_CONFIG, MODE_PRIVATE);
        //让其从只读状态变成可编辑状态
        SharedPreferences.Editor editor =preferences.edit();
        editor.putBoolean(IS_FIRST_RUN,false);
        editor.apply();
        startActivity(new Intent(this,SplashActivity.class));
        finish();

    }

    //动画(类)
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}
