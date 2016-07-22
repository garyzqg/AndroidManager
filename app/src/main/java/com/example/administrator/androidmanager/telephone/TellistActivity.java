package com.example.administrator.androidmanager.telephone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.androidmanager.javabean.TelnumberInfo;
import com.example.administrator.androidmanager.R;

import java.util.ArrayList;

public class TellistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String mId;
    private ArrayList<TelnumberInfo> mTelnumberInfos;
    private TellistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);
        ListView listView = (ListView) findViewById(R.id.lv_tellist);
        //获取Intent传递过来的id
        mId = getIntent().getStringExtra("idx");
        //调用DBReader里的方法,读取数据库文件中子列表n的数据,得到一个集合
        mTelnumberInfos = DBReader.readTeldbTable(mId, TelActivity.mFile);
        mAdapter = new TellistAdapter(this, mTelnumberInfos);
        listView.setAdapter(mAdapter);
        //设置listview的点击事件
        listView.setOnItemClickListener(this);

        //toolbar + 菜单
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_tel);
        toolbar.inflateMenu(R.menu.menu_tellist);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        Toast.makeText(TellistActivity.this,"click search item",Toast.LENGTH_SHORT).show();
                    break;
                    case R.id.action_settings:
                        Toast.makeText(TellistActivity.this,"click setting item",Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
                return false;
            }
        });


    }

    //为listView设置点击
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //利用intent进行拨号,注意:获取电话号码也可以利用TellistAdapter类,mAdapter.getItem(position).number
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mTelnumberInfos.get(position).number));
        startActivity(intent);
    }
}
