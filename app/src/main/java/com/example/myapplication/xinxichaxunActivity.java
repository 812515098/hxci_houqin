package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Adapter.gongzuoxiangqiangqing_Adapter;
import com.example.myapplication.Adapter.xinxichaxun_Adapter;
import com.example.myapplication.Adapter.xinxichaxun_Adapter2;
import com.example.myapplication.Bean.XinxichaxunBean;

import java.util.ArrayList;
import java.util.List;

public class xinxichaxunActivity extends AppCompatActivity implements View.OnClickListener {
    private List<XinxichaxunBean> xinxichaxunBeans = new ArrayList<>();
    private List<XinxichaxunBean> xinxichaxunBeans2 = new ArrayList<>();
    private List<XinxichaxunBean> xinxichaxunBeans3 = new ArrayList<>();
    private ImageView duanhua,bangong,banche,tuxiang,bk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinxichaxun);
        initBases();//初始化数据
        initBases2();//初始化数据
        xinxichaxun_Adapter adapter = new xinxichaxun_Adapter(xinxichaxunActivity.this,xinxichaxunBeans);
        ListView listView =  findViewById(R.id.xinxichaxun_Listview);
        duanhua=findViewById(R.id.xinxichaxun_dinahua);
        bangong=findViewById(R.id.xinxichaxun_bangong);
        banche=findViewById(R.id.xinxichaxun_banche);
        tuxiang=findViewById(R.id.xinxichaxun_img);
        bk=findViewById(R.id.xinxichaxun_bk);
        tuxiang.setBackgroundResource(R.drawable.dianhua_1);
        bk.setOnClickListener(this);
        duanhua.setOnClickListener(this);
        bangong.setOnClickListener(this);
        banche.setOnClickListener(this);
        listView.setAdapter(adapter);
    }

    private void initBases(){
        for (int i=0;i<3;i++){
            XinxichaxunBean xinxichaxunBean=new XinxichaxunBean();
            xinxichaxunBean.setBiaoti("火警电话话");
            xinxichaxunBean.setDianhua("1111-11111111111");
            xinxichaxunBeans.add(xinxichaxunBean);
        }
    }
    private void initBases2(){
        for (int i=0;i<3;i++){
            XinxichaxunBean xinxichaxunBean=new XinxichaxunBean();
            xinxichaxunBean.setBiaoti("2公寓电话话");
            xinxichaxunBean.setDianhua("1111-11111111");
            xinxichaxunBeans2.add(xinxichaxunBean);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.xinxichaxun_dinahua:
                tuxiang.setBackgroundResource(R.drawable.dianhua_1);
                ListView listView =  findViewById(R.id.xinxichaxun_Listview);
                xinxichaxun_Adapter adapter = new xinxichaxun_Adapter(xinxichaxunActivity.this,xinxichaxunBeans);
                listView.setAdapter(adapter);
                break;
            case R.id.xinxichaxun_bangong:
                tuxiang.setBackgroundResource(R.drawable.bangong_2);
                ListView listView2 =  findViewById(R.id.xinxichaxun_Listview);
                xinxichaxun_Adapter2 adapter2 = new xinxichaxun_Adapter2(xinxichaxunActivity.this,xinxichaxunBeans2);
                listView2.setAdapter(adapter2);
                break;
            case R.id.xinxichaxun_banche:
                tuxiang.setBackgroundResource(R.drawable.banche_3);
                ListView listView3 =  findViewById(R.id.xinxichaxun_Listview);
                listView3.setAdapter(null);
                break;
            case R.id.xinxichaxun_bk:
                finish();
                break;
        }
    }
}
