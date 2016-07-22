package com.example.administrator.androidmanager.mobiletest;

import android.content.Context;
import android.content.IntentFilter;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.androidmanager.R;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MobileTestAdapter extends BaseAdapter {
    Context mContext;
    int[] mIcons;
    String[] mTv1;
    String[] mTv2;

    public MobileTestAdapter(Context context, int[] icons, String[] tv1, String[] tv2) {
        mContext = context;
        mIcons = icons;
        mTv1 = tv1;
        mTv2 = tv2;
    }

    @Override
    public int getCount() {
        return mIcons.length;
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
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.mobiletest_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_mobiletext_icon);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.tv_mobiletest1);
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.tv_mobiletest2);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mImageView.setImageResource(mIcons[position]);
        viewHolder.mTextView1.setText(mTv1[position]);
        viewHolder.mTextView2.setText(mTv2[position]);
        return convertView;
    }
    public final class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
    }
}
