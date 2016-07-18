package com.example.administrator.androidmanager.telephone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
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
        View view;
        if (convertView == null){
            view = View.inflate(mContext, R.layout.home_item,null);
        }else{
            view = convertView;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_home);
        imageView.setImageResource(draws[position]);
        TextView textView = (TextView) view.findViewById(R.id.tv_home);
        textView.setText(texts[position]);

        return view;
    }
}
