package com.example.administrator.androidmanager.softwaremanager;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.androidmanager.javabean.SoftwareInfo;
import com.example.administrator.androidmanager.R;

import java.util.ArrayList;
import java.util.List;

public class PackageActivity extends AppCompatActivity {
    ArrayList<SoftwareInfo> mArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        //通过PackageManager获取PackageInfo的集合,获取手机已安装应用的信息（应用图片、应用名、包名等）；
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> infoList = packageManager.getInstalledPackages(0);
        mArrayList = new ArrayList<>();

        //获取自己安装的软件的applicationName;icon;versionName;packageName
        for (int i = 0; i < infoList.size(); i++) {
            PackageInfo packageInfo = infoList.get(i);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //   <=0为自己安装软件,  >0为系统自带软件
            if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0){
                String packageName = packageInfo.packageName;//包名
                String versionName = packageInfo.versionName;//版本号
                Drawable drawable = applicationInfo.loadIcon(packageManager);//图标
                String applicationName = applicationInfo.loadLabel(packageManager).toString();//应用名
                //封装到javabean中
                SoftwareInfo software = new SoftwareInfo(applicationName,drawable,versionName,packageName);
                //添加至集合中
                mArrayList.add(software);
            }
        }

        //listview展示信息
        ListView listView = (ListView) findViewById(R.id.lv_softwareinfo);
        listView.setAdapter(new SoftwareAdapter(mArrayList,this));

        //设置点击事件,实现卸载
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取包名
                String packageName = mArrayList.get(position).getPackageName();
                //卸载
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        });


    }
}
