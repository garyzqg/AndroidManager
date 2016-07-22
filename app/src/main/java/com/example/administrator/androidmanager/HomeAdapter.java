package com.example.administrator.androidmanager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.androidmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/18.
 */
public class HomeAdapter extends BaseAdapter{
    Context mContext;
    int[] draws;
    String[] texts;

    public HomeAdapter(Context context, int[] draws, String[] texts) {
        mContext = context;
        this.draws = draws;
        this.texts = texts;
    }

    @Override
    public int getCount() {
        return draws.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.home_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_home);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_home);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mImageView.setImageResource(draws[position]);
        viewHolder.mTextView.setText(texts[position]);

        return convertView;
    }
    public static class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
    }
}
