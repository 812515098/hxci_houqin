package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.Adapter.SelfDialog;
import com.example.myapplication.Adapter.xinxichaxun_Adapter2;
import com.example.myapplication.Bean.TiaoWenBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class tiaowenshengqingActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private List<TiaoWenBean> list01=new ArrayList<>();
    private ListViewAdapter adapter;
    private EditText etStartTime;
    private EditText etEndTime;
    private ImageView StartTime;
    private ImageView EndTime;
    private Button xuesheng_tiaowen_tijiao;
    private String start_time;
    private String end_time;
    private TextView xiayiye,shangyiye;
    private  int index=0;
    int pagerCount=3;
    private   int yeshu=0;
    private SelfDialog selfDialog;
    private TextView num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiaowenshengqing);
        shangyiye=findViewById(R.id.shangyiye);
        xiayiye=findViewById(R.id.xiayiye);
        num=findViewById(R.id.yeshu);
        etStartTime =findViewById(R.id.tioawen_qishitime);
        etEndTime =findViewById(R.id.tiaowen_jieshutime);
        StartTime=findViewById(R.id.tioawen_qishi);
        EndTime=findViewById(R.id.tiaowen_jieshu);
        xuesheng_tiaowen_tijiao =findViewById(R.id.xuesheng_tiaowen_tijiao);
        xuesheng_tiaowen_tijiao.setOnClickListener(this);
        StartTime.setOnTouchListener(this);
        EndTime.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.xuesheng_tiaowen_tijiao:
                selfDialog = new SelfDialog(tiaowenshengqingActivity.this,R.style.dialog);
                selfDialog.setMessage("你确定提交调温吗?");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(tiaowenshengqingActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(tiaowenshengqingActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
                selfDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                break;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.date_time_dialog, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
            builder.setView(view);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH),
                    null);

            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.MINUTE);

            if (v.getId() == R.id.tioawen_qishi) {
                final int inType = etStartTime.getInputType();
                etStartTime.setInputType(InputType.TYPE_NULL);
                StartTime.onTouchEvent(event);
                etStartTime.setInputType(inType);
                etStartTime.setSelection(etStartTime.getText().length());

                builder.setTitle("选取起始时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());

                        etStartTime.setText(sb);
                        EndTime.requestFocus();

                        dialog.cancel();
                    }
                });

            } else if (v.getId() == R.id.tiaowen_jieshu) {
                int inType = etEndTime.getInputType();
                etEndTime.setInputType(InputType.TYPE_NULL);
                etEndTime.onTouchEvent(event);
                etEndTime.setInputType(inType);
                etEndTime.setSelection(etEndTime.getText().length());

                builder.setTitle("选取结束时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());
                        etEndTime.setText(sb);

                        dialog.cancel();
                    }
                });
            }

            Dialog dialog = builder.create();
            dialog.show();
        }
        return false;
    }


    public class ListViewAdapter extends BaseAdapter {
        int pagerCount=3;
        private  int index=0;
        private List<TiaoWenBean> list;
        public Context context;
        public ListViewAdapter(Context context, List<TiaoWenBean> list){
            this.context=context;
            this.list=list;
        }
        public void setDeviceList(ArrayList<TiaoWenBean> list01) {
            if (list01 != null) {
                list = (List<TiaoWenBean>) list01.clone();
                notifyDataSetChanged();
            }
        }
        public void clearDeviceList() {
            if (list != null) {
                list.clear();
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount()      {
            //数据大于页数*每页个数,显示默认数字,小于时显示剩余的
            int current = index * pagerCount;
            return list01.size() - current < pagerCount ? list01.size() - current : pagerCount;

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
            View view= LayoutInflater.from(context).inflate(R.layout.tiaowenshenqing_item,parent,false);
            int pos = position + index * pagerCount;
            TextView shijian = view.findViewById(R.id.tiaowen_item_shijian);
            TextView didian = view.findViewById(R.id.tiaowen_item_didian);
            TextView yuanyin = view.findViewById(R.id.tiaowen_item_yuanyin);
            TextView zhuangtai = view.findViewById(R.id.tiaowen_item_zhuangtai);
            shijian.setText(list.get(position).getShijian());
            yuanyin.setText(list.get(position).getYuanyin());
            didian.setText(list.get(position).getDidian());
            zhuangtai.setText(list.get(position).getZhuangtai());
            return view;

        }
    }

}
