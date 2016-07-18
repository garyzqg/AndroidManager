package com.example.administrator.androidmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.androidmanager.telephone.HomeAdapter;
import com.example.administrator.androidmanager.telephone.MainActivity;

public class HomeActivity extends AppCompatActivity{

    private GridView mGridView;
    int[] draws = {R.drawable.icon_rocket,R.drawable.icon_softmgr,R.drawable.icon_phonemgr,
            R.drawable.icon_telmgr,R.drawable.icon_filemgr,R.drawable.icon_sdclean};
    String[] texts ={"手机加速","软件管理","手机检测","通讯大全","文件管理","垃圾清理"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mGridView = (GridView) findViewById(R.id.gv_home);
        mGridView.setAdapter(new HomeAdapter(HomeActivity.this,draws,texts));

        //菜单
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_home);
        toolbar.inflateMenu(R.menu.menu_home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(HomeActivity.this,"点击了菜单键",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        //对GridView设置点击事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 3:
                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
    }



}
