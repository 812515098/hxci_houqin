package com.example.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.Bean.MsgInfo;
import com.example.myapplication.Bean.WeixiurenShouyeMsgList;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.myapplication.R.color.black;

public class WeixiurenShouyeListAdapter extends BaseQuickAdapter<WeixiurenShouyeMsgList.RepairsBean, BaseViewHolder> {
    public WeixiurenShouyeListAdapter(int layoutResId, @Nullable ArrayList<WeixiurenShouyeMsgList.RepairsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeixiurenShouyeMsgList.RepairsBean item) {

        helper.setText(R.id.tv_time, item.getSubDateStr());
        helper.setText(R.id.tv_place, item.getRepairAdress());
        helper.setText(R.id.tv_content,item.getRepairContent());
//        0:未接单，1：已完成（移动端多人），2：需二次，3：处理中
        if(item.getStatus()==0){
            helper.setText(R.id.tv_state,"未接单");
            helper.getView(R.id.tv_state).setBackgroundResource(R.color.red_1);
        }else if(item.getStatus()==1){
            helper.setText(R.id.tv_state,"已完成");
            helper.getView(R.id.tv_state).setBackgroundResource(R.color.comui_tab_selected);
        }else if(item.getStatus()==2){
            helper.setText(R.id.tv_state,"需二次");
            helper.getView(R.id.tv_state).setBackgroundResource(R.color.comui_tab_selected);
        }else if(item.getStatus()==3){
            helper.setText(R.id.tv_state,"处理中");
            helper.getView(R.id.tv_state).setBackgroundResource(R.color.comui_tab_selected);
        }


    }

}
