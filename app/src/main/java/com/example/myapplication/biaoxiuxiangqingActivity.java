package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.xlhratingbar_lib.XLHRatingBar;


public class biaoxiuxiangqingActivity extends AppCompatActivity {
    private XLHRatingBar ratingBar1,ratingBar2, ratingBar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biaoxiuxiangqing);
        XLHRatingBar ratingBar1 = (XLHRatingBar) findViewById(R.id.ratingBar1);
        ratingBar1.setCountNum(5);
        ratingBar1.setCountSelected(1);
        ratingBar1.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int countSelected) {
          //      tvResult.setText(countSelected + "");
            }
        });
        XLHRatingBar ratingBar2 = (XLHRatingBar) findViewById(R.id.ratingBar2);
        ratingBar2.setCountNum(5);
        ratingBar2.setCountSelected(1);
        ratingBar2.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int countSelected) {
              //  tvResult.setText(countSelected + "");
            }
        });
        XLHRatingBar ratingBar3 = (XLHRatingBar) findViewById(R.id.ratingBar3);
        ratingBar3.setCountNum(5);
        ratingBar3.setCountSelected(1);
        ratingBar3.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int countSelected) {
             //   tvResult.setText(countSelected + "");
            }
        });
    }
}
