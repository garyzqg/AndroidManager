package com.example.administrator.androidmanager.telephone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.androidmanager.JavaBean.TelclassInfo;
import com.example.administrator.androidmanager.JavaBean.TelnumberInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DBReader {
    /*
    此方法为读取数据库文件中的classlist这个表内的数据
    */
    public static ArrayList<TelclassInfo> readTeldbClasslist(File file){

        //SQLiteDatabase里的openOrCreateDatabase方法返回一个SQLiteDatabase对象
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file,null);
        //创建ArrayList集合存储 封装了数据库每一条新信息的 TelclassInfo对象
        ArrayList<TelclassInfo> classlistInfos = new ArrayList<>();
        //查询,读取数据库classlist表中数据
        Cursor cursor = db.rawQuery("select * from classlist",null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));//查询遍历"name"列
                String idx = cursor.getString(cursor.getColumnIndex("idx"));//查询遍历"idx"列
                //利用TelclassInfo将name和idx列封装,并存在list集合中
                TelclassInfo classListInfo = new TelclassInfo(name,idx);
                classlistInfos.add(classListInfo);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return classlistInfos;
    }
    /*
    此方法为读取数据库文件中的tableN(N个表)这个表内的数据(商家名称和电话号码)
    */
    public static ArrayList<TelnumberInfo> readTeldbTable(String idx, File file){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file,null);
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
        //读取tableN的数据
        Cursor cursor = db.rawQuery("select * from table" + idx,null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                //利用TelnumberInfo类将name和number列封装,并存在list集合中
                TelnumberInfo numberInfo = new TelnumberInfo(name,number);
                numberInfos.add(numberInfo);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return numberInfos;
    }

}
