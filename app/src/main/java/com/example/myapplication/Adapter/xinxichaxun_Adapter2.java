package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Bean.XinxichaxunBean;
import com.example.myapplication.R;

import java.util.List;

public class xinxichaxun_Adapter2 extends BaseAdapter {
    private List<XinxichaxunBean> list;
    public Context context;
    public xinxichaxun_Adapter2(Context context, List<XinxichaxunBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
       View view= LayoutInflater.from(context).inflate(R.layout.xinxichaxun_item2,parent,false);
     RelativeLayout relativeLayout=view.findViewById(R.id.xinxichaxun_bg2);
        TextView biaoti = view.findViewById(R.id.xinxichaxun_item_xingming2);
        TextView dinahua = view.findViewById(R.id.xinxichaxun_item_zhuangtiai2);
        biaoti.setText(list.get(position).getBiaoti());
        dinahua.setText(list.get(position).getDianhua());
        if(position%2 == 0){
            relativeLayout.setBackgroundColor(Color.WHITE);
        }else{
            relativeLayout.setBackgroundColor(Color.parseColor("#F1F1F1"));
        }
        return view;

    }
}
