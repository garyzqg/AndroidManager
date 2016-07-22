package com.example.administrator.androidmanager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyPaperAdapter extends PagerAdapter{
    private ArrayList<View> mArrayList;

    public MyPaperAdapter(ArrayList<View> arrayList) {
        mArrayList = arrayList;
    }
    //以下两方法为抽象方法,必须重写
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
    //以下两个方法为非抽象方法,为自己重写
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //给容器里添加试图
        container.addView(mArrayList.get(position),0);
        //把具体的视图返回调用者
        return mArrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //从容器里移除试图
        container.removeView(mArrayList.get(position));
    }
}
