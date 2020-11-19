package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SelfDialog;

public class zhanghaoshezhiActivity extends AppCompatActivity implements View.OnClickListener {
private RelativeLayout shoujihao;
private TextView tuichu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghaoshezhi);
        shoujihao=findViewById(R.id.shoujihao);
        tuichu=findViewById(R.id.tuichu);
        shoujihao.setOnClickListener(this);
        tuichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.shoujihao:
                Intent intent1 = new Intent(zhanghaoshezhiActivity.this,SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.tuichu:
               finish();
                break;
        }
    }
}
