package com.example.administrator.androidmanager.mobiletest;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.androidmanager.R;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;

public class MobileTestActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_test);
        String brand = Build.BRAND;//设备名称
        String release = Build.VERSION.RELEASE;//系统版本号
        String cpuAbi = Build.CPU_ABI;//CPU类型
        //获取CPU核心数
        File dir= new File("/sys/devices/system/cpu/");
        File[] files = dir.listFiles(new CpuFilter());
        int cpuNum = files.length;

        String mobileSize = getMobileSize();//手机分辨率
        String cameraSize = getCameraSize();//相机分辨率
        String radio = Build.RADIO;//基带版本
        boolean isRoot = isRoot();//获取是否root
        String formatTotalSpace = Formatter.formatFileSize(this,getMemory());//手机运行内存
        String formatFreeSpace = Formatter.formatFileSize(this,getFreeSpace());//剩余运行内存

        //图标
        int[] icons = {R.mipmap.setting_info_icon_version,R.mipmap.setting_info_icon_space,
                R.mipmap.setting_info_icon_cpu,R.mipmap.setting_info_icon_camera,R.mipmap.setting_info_icon_root};
        //第一行textview
        String[] tv1 = {"设备名称:" + brand,"全部运行内存:" + formatTotalSpace,"cpu名称:" + cpuAbi,
        "手机分辨率:" + mobileSize,"基带版本:" + radio };
        //第二行textview
        String[] tv2 ={"系统版本:" + release,"剩余运行内存:" + formatFreeSpace,"cpu数量:" + cpuNum,
        "相机分辨率:" + cameraSize,"是否root:" + isRoot};

        //listview
        ListView listView = (ListView) findViewById(R.id.lv_mobiletest);
        listView.setAdapter(new MobileTestAdapter(this,icons,tv1,tv2));

        //电量
        BatteryBroadCast receiver = new BatteryBroadCast();
        //意图过滤器 制定一个特定的意图 给对象
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //不要忘记在manifest注册receiver
        registerReceiver(receiver,filter);

    }

    /*
    电量获取
     */
    //BroadcastReceiver 广播接收者-->给系统发送相关要求的广播
    public class BatteryBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                Bundle bundle = intent.getExtras();
                int maxPower = (int) bundle.get(BatteryManager.EXTRA_SCALE);//最大电量值
                int currentPower = (int) bundle.get(BatteryManager.EXTRA_LEVEL);//当前电量值
                int currentTemperature = (int) bundle.get(BatteryManager.EXTRA_TEMPERATURE);//当前电池温度
                double temperature = currentTemperature / 10.0;
                Log.d("电池", "onReceive: " + currentPower + "  " +maxPower+ "  " +  currentTemperature);
                mProgressBar = (ProgressBar) findViewById(R.id.pb_battery);
                mTextView = (TextView) findViewById(R.id.tv_battery);
                mProgressBar.setMax(maxPower);
                mProgressBar.setProgress(currentPower);
                mTextView.setText(currentPower+ "%");
            }
        }
    }
    /*
    获取是否获取root权限
     */
    private boolean isRoot() {
        boolean boo;
        if (!new File("system/bin/su").exists() && !new File("system/xbin/su").exists()){
            boo = false;
        }else{
            boo = true;
        }
        return boo;
    }
    /*
       获取手机分辨率
        */
    private String getMobileSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        return widthPixels + "*" + heightPixels;
    }
    /*
    获取相机分辨率
     */
    private String getCameraSize() {
        android.hardware.Camera camera = android.hardware.Camera.open();
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        List<android.hardware.Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        android.hardware.Camera.Size size = null;
        for (android.hardware.Camera.Size s : supportedPictureSizes){
            if (size == null){
                size = s;
            }else if (size.height*size.width < s.height*s.width){
                size = s;
            }

        }
        String maxCameraSize = size.height + "*" + size.width;
        camera.release();
        return maxCameraSize;


    }
    /*
    获取剩余运行内存
     */
    private long getFreeSpace() {
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.getMemoryInfo(info);
        return info.availMem;
    }
    /*
    获取总运行内存
     */
    private static long getMemory(){
        try {
            FileReader reader = new FileReader("/proc/meminfo");
            BufferedReader br = new BufferedReader(reader);
            String text = br.readLine();         //1745556 kB=1.66 GB
            String[] split = text.split("\\s+");
            //text = text.trim();
            return Long.valueOf(split[1]) * 1024;//1.66GB,必须乘以1024得到字节数返回
            //return Long.valueOf(text);//0B
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
    /*
    类:cpu核心数
     */
    class CpuFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                return true;
            }
            return false;
        }
    }
