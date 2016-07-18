package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mName;
    private EditText mNumber;
    private EditText mPhone;
    private SQLiteDatabase mDb;
    StudentInfo mStudentInfo;
    ArrayList<StudentInfo> mArrayList;
    ListView mListView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化View,创建/打开数据库
        initView();
        //初始化mArrayList!!!!!!!!!!!!!
        mArrayList = new ArrayList<>();
        //读数据库
        readDatabase();
        //listview显示-->先读取数据库里存在的内容,显示到listView
        mListView.setAdapter(new StudentInfoAdapter(mArrayList,MainActivity.this));
        //点击按键时插入数据库
        mButton.setOnClickListener(this);


    }

    private void initView() {
        mName = (EditText) findViewById(R.id.et_name);
        mNumber = (EditText) findViewById(R.id.et_number);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mButton = (Button) findViewById(R.id.btn_insert);
        mListView = (ListView) findViewById(R.id.lv_show);
        //创建数据库
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        mDb = myOpenHelper.getReadableDatabase();
    }

    /*
    读取数据库
     */
    private void readDatabase() {
        Cursor cursor = mDb.query("info",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                mStudentInfo = new StudentInfo(id,name,phone);
                mArrayList.add(mStudentInfo);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        boolean isRepeat = isRepeat();
        //将eidttext内容插入数据库
        if(mNumber.getText()!=null && mName.getText()!=null && mPhone!=null && !isRepeat){
            //将editText输入内容包装至javaBean中
            mStudentInfo=new StudentInfo(mNumber.getText().toString().trim(),mName.getText().toString().trim(),mPhone.getText().toString().trim());

            //存入集合中
            mArrayList.add(mStudentInfo);

            //listview显示-->每次点击添加成功后更新到listView
            mListView.setAdapter(new StudentInfoAdapter(mArrayList,MainActivity.this));

            //插入数据到数据库
            ContentValues values = new ContentValues();
            values.put("_id",mStudentInfo.number);
            values.put("name",mStudentInfo.name);
            values.put("phone",mStudentInfo.phone);
            long insert = mDb.insert("info",null,values);
            if(insert > 0){
                Toast.makeText(this,"插入成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"插入失败",Toast.LENGTH_LONG).show();
            }
        }


    }
    /*
    判断插入数据id是否重复,重复返回true
     */
    public boolean isRepeat(){
        Cursor cursor = mDb.query("info",new String[]{"_id"},null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                if (mNumber.getText().toString().trim().equals(id)){
                    Toast.makeText(this,"id已经存在,插入失败",Toast.LENGTH_LONG).show();
                    return true;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

}
