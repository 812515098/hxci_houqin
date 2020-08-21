package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.myapplication.Adapter.gongzuoxiangqiangqing_Adapter;
import com.example.myapplication.Bean.XinXiBean;

import java.util.ArrayList;
import java.util.List;

public class gongzuoxiangqingActivity extends AppCompatActivity {
    private List<XinXiBean> xinXiBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongzuoxiangqing);

//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        initBases();//初始化数据
        gongzuoxiangqiangqing_Adapter adapter = new gongzuoxiangqiangqing_Adapter(gongzuoxiangqingActivity.this,xinXiBeans);
        ListView listView =  findViewById(R.id.gongzuoxiangqing_listview);
        listView.setAdapter(adapter);
    }
    private void initBases(){
        for (int i=0;i<3;i++){
            XinXiBean xinXiBean=new XinXiBean();
            xinXiBean.setMingzi("111");
            xinXiBean.setGongzhong("222");
            xinXiBean.setZhuangtai("333");
            xinXiBeans.add(xinXiBean);}
    }

}
