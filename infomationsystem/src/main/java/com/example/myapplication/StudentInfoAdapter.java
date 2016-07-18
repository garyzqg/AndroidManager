package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/18.
 */
public class StudentInfoAdapter extends BaseAdapter{
    ArrayList<StudentInfo> mArrayList;
    Context mContext;

    public StudentInfoAdapter(ArrayList<StudentInfo> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = View.inflate(mContext,R.layout.item,null);
        }else{
            view = convertView;
        }
        TextView tv1 = (TextView) view.findViewById(R.id.tv_id);
        tv1.setText(mArrayList.get(position).number);
        TextView tv2 = (TextView) view.findViewById(R.id.tv_name);
        tv2.setText(mArrayList.get(position).name);
        TextView tv3 = (TextView) view.findViewById(R.id.tv_Phone);
        tv3.setText(mArrayList.get(position).phone);
        return view;
    }
}
