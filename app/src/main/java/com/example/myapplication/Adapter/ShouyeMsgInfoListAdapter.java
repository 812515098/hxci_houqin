package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.Bean.MsgInfo;
import com.example.myapplication.R;
import com.example.myapplication.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShouyeMsgInfoListAdapter extends BaseQuickAdapter<MsgInfo, BaseViewHolder> {

    public ShouyeMsgInfoListAdapter(int layoutResId, @Nullable ArrayList<MsgInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgInfo item) {

        helper.setText(R.id.item_content, item.getContent());
        helper.setText(R.id.item_date, item.getReleaseTimeStr());
        helper.setText(R.id.item_tongzhi,item.getTypeStr());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate01 = new Date(System.currentTimeMillis());
        String releaseTimeStr = item.getReleaseTimeStr();
        String replace = releaseTimeStr.replace("-", "");
        String format01 = formatter.format(curDate01);

        int gapCount =Integer.parseInt(format01)-Integer.parseInt(replace);
        Log.e("ping","curDate01-->>"+curDate01);

        if(gapCount>1){
            helper.getView(R.id.is_new).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.is_new).setVisibility(View.VISIBLE);
        }

    }

}
