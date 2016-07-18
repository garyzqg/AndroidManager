package com.example.administrator.androidmanager.JavaBean;

/**
 * Created by Administrator on 2016/7/14.
 */
public class TelclassInfo {
    //电话名称
    public String name;
    //唯一ID
    public String idx;
    //重写构造方法
    public TelclassInfo(String name, String idx) {
        super();
        this.name = name;
        this.idx = idx;
    }
    //系统默认构造方法
    public TelclassInfo() {
        super();
    }
}
