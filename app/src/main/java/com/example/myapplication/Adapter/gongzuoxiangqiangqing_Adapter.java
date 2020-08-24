package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Bean.XinXiBean;
import com.example.myapplication.Bean.XinxichaxunBean;
import com.example.myapplication.R;

import java.util.List;

public class gongzuoxiangqiangqing_Adapter extends BaseAdapter {
  private List<XinXiBean> list;
    public Context context;
    public gongzuoxiangqiangqing_Adapter(Context context, List<XinXiBean> list){
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
        View view= LayoutInflater.from(context).inflate(R.layout.gongzuoxiangqing_item,parent,false);
        TextView xingming = view.findViewById(R.id.item_xingming);
        TextView gongzong = view.findViewById(R.id.item_gongzhong);
        TextView zhuangtai = view.findViewById(R.id.item_zhuangtiai);
        xingming.setText(list.get(position).getMingzi());
        gongzong.setText(list.get(position).getGongzhong());
        zhuangtai.setText(list.get(position).getZhuangtai());
        return view;

    }
}
