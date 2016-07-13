package com.example.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyPagerAdapter extends PagerAdapter{

    private ArrayList<View> mArrayList;

    public MyPagerAdapter(ArrayList<View> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        if(mArrayList != null){
            return mArrayList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //给容器里添加试图
        container.addView(mArrayList.get(position),0);
        //把具体的视图返回调用者
        return mArrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mArrayList.get(position));

    }
}
