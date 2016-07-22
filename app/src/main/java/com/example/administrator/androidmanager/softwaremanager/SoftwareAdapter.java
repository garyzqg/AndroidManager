package com.example.administrator.androidmanager.softwaremanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.androidmanager.javabean.SoftwareInfo;
import com.example.administrator.androidmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SoftwareAdapter extends BaseAdapter{
    ArrayList<SoftwareInfo> mArrayList;
    Context mContext;

    public SoftwareAdapter(ArrayList<SoftwareInfo> arrayList, Context context) {
        mArrayList = arrayList;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mArrayList!= null){
            return mArrayList.size();
        }
        return 0;
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
        ViewHolder holder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.software_item,null);
            //为空则进行holder初始化
            holder = new ViewHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.textView1 = (TextView) convertView.findViewById(R.id.tv_applicationname);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_versionname);
            holder.textView3 = (TextView) convertView.findViewById(R.id.tv_packagename);
            //给convertView加一个标记,表示已经给对象复制成功了
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //setImageDrawable接收drawable
        holder.mImageView.setImageDrawable(mArrayList.get(position).getIcon());
        //设置固定宽度setLayoutParams,参数必须是父布局的new LinearLayout.LayoutParams(100,100)
        holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(100,100));
        //控制图片如何resized/moved来匹对ImageView的size, CENTER_CROP:按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
        holder.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //设置内容距边框的举例
        holder.mImageView.setPadding(5,5,5,5);
        holder.textView1.setText(mArrayList.get(position).getApplicationName());
        holder.textView2.setText(mArrayList.get(position).getVersionName());
        holder.textView3.setText(mArrayList.get(position).getPackageName());
        return convertView;
    }

    //ViewHolder优化
    public final class ViewHolder{
        public ImageView mImageView;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
    }
}
