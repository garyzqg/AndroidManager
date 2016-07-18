package com.example.administrator.androidmanager.telephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.androidmanager.R;
import com.example.administrator.androidmanager.JavaBean.TelclassInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    public static final String DATABASE_PATH = "data/data/com.example.administrator.androidmanager/database/";
    public static File mFile;
    private TelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //复制本地Assets中的db文件到指定目录中
        copyDatabase();
        //调用DBReader中的方法,读取数据库文件中表格的数据,得到一个集合(泛型为封装类)
        ArrayList<TelclassInfo> telclassInfos = DBReader.readTeldbClasslist(mFile);

        /*利用listView实现电话主界面

        //将集合的内容利用TelAdapter展示到ListView
        ListView listView = (ListView) findViewById(R.id.lv_tel);
        mAdapter = new TelAdapter(telclassInfos,this);
        listView.setAdapter(mAdapter);
        //给ListView条目设置点击事件
        listView.setOnItemClickListener(this);
        */

        //利用GridView实现电话主界面
        GridView gridView = (GridView) findViewById(R.id.gv_tel);
        mAdapter = new TelAdapter(telclassInfos,this);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 取出当前选择的选项实体内容
        TelclassInfo classInfo =  mAdapter.getItem(position);

        // 跳转至电话浏览页面,且传入idx,并且根据idx跳转
        Intent intent = new Intent(this, TellistActivity.class);
        //携带数据跳转
        intent.putExtra("idx", classInfo.idx);
        startActivity(intent);
    }



    private void copyDatabase() {
        //指定目录
        File f = new File(DATABASE_PATH);
        f.mkdir();
        mFile = new File(f,"copydatabase.db");
        //如果不存在,复制本地Assets中的db文件到指定目录中
        if(!mFile.exists()||mFile.length()<=0){
            //本地目录
            String path = "db/commonnum.db";
            //调用AssetsDBManager方法,复制本地Assets中的db文件到指定目录中
            AssetsDBManager assetsDBManager = new AssetsDBManager();
            try {
                assetsDBManager.copyAssetsFileToFile(this, mFile,path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
