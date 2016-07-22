package com.example.administrator.androidmanager.javabean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SoftwareInfo {

    private String applicationName;
    private Drawable icon;
    private String versionName;
    private String packageName;

    public SoftwareInfo(String applicationName, Drawable icon, String versionName, String packageName) {
        this.applicationName = applicationName;
        this.icon = icon;
        this.versionName = versionName;
        this.packageName = packageName;
    }

    public SoftwareInfo() {
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getPackageName() {
        return packageName;
    }
}
