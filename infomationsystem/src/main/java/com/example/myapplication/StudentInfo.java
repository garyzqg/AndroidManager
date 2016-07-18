package com.example.myapplication;

import android.text.style.SuperscriptSpan;

import java.security.PrivateKey;

/**
 * Created by Administrator on 2016/7/18.
 */
public class StudentInfo {
    String number;
    String name;
    String phone;

    public StudentInfo(String number, String name, String phone) {
        this.number = number;
        this.name = name;
        this.phone = phone;
    }

    public StudentInfo() {
        super();
    }
}
