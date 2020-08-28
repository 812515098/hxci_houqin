package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class baoxiumesaageActivity extends AppCompatActivity {
    ListView baoxiu_ms;
    ArrayList data1;
    ArrayAdapter<String> myadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baoxiumessage);
       baoxiu_ms= findViewById(R.id.baoxiu_ms);
        myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list());
        baoxiu_ms.setAdapter(myadapter);
        baoxiu_ms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(baoxiumesaageActivity.this,baoxiuxiangqing_activity.class);
                startActivity(intent);
            }
        });


    }

    private List<String> list() {
        data1 =new ArrayList();
        data1.add("测试1");
        data1.add("测试1");
        data1.add("测试1");
        data1.add("测试1");
        data1.add("测试1");

        return data1;
    }
}
