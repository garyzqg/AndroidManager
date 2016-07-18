package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    //有参构造
    public MyOpenHelper(Context context) {
        super(context, "studentsInfo.db", null, 1);
    }
    //数据库第一次创建时调用,用于数据库表格初始化
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info (_id integer,name varchar(20),phone varchar(20))");

    }
    //数据库版本需要升级时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
