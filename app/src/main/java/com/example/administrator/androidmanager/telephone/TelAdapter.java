package com.example.administrator.androidmanager.telephone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.androidmanager.R;
import com.example.administrator.androidmanager.javabean.TelclassInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class TelAdapter extends BaseAdapter {
    ArrayList<TelclassInfo> mArrayList;
    Context mContext;

    public TelAdapter(ArrayList<TelclassInfo> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @Override
    public int getCount() {
        if(mArrayList != null){
           return mArrayList.size();
        }
        return 0;
    }

    @Override
    public TelclassInfo getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.tel_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_name);
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.tv_item_idx);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView.setText(mArrayList.get(position).name);
        viewHolder.mTextView2.setText(mArrayList.get(position).idx);
        return convertView;
    }
    public final class ViewHolder{
        public TextView mTextView;
        public TextView mTextView2;

    }
}
