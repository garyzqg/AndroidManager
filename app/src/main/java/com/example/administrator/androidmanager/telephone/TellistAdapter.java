package com.example.administrator.androidmanager.telephone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.androidmanager.javabean.TelnumberInfo;
import com.example.administrator.androidmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TellistAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<TelnumberInfo> mArrayList;

    public TellistAdapter(Context context, ArrayList<TelnumberInfo> arrayList) {
        mContext = context;
        mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        if(mArrayList!=null){
            return mArrayList.size();
        }
        return 0;
    }

    @Override
    public TelnumberInfo getItem(int position) {
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
            convertView = View.inflate(mContext, R.layout.num_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_numname);
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.tv_item_number);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mArrayList.get(position).name);
        viewHolder.mTextView2.setText(mArrayList.get(position).number);
        return convertView;
    }
    public static class ViewHolder{
        public TextView mTextView;
        public TextView mTextView2;
    }
}
