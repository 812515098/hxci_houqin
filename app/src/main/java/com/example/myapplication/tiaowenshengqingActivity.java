package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.Adapter.SelfDialog;
import com.example.myapplication.Bean.TiaoWenBean;
import com.example.myapplication.http.HttpSyncPostUtil;
import com.example.myapplication.http.HttpURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tiaowenshengqingActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private List<TiaoWenBean.TemperaturesBean> list01=new ArrayList<TiaoWenBean.TemperaturesBean>();
    private ImageView taowen_tuichu;
    private ListViewAdapter adapter;
    private EditText etStartTime;
    private EditText etEndTime;
    private EditText tiaowenjutididian;
    private ImageView StartTime;
    private ImageView EndTime;
    private RadioButton  radioButton1, radioButton2,radioButton3;
    private RadioGroup tousu_Radioggroup;
    private Button xuesheng_tiaowen_tijiao;
    private String didianneirong[] = new String[]{"教学楼A座","教学楼B座","教学楼C座"};
    private Spinner didian;
    private String yuanyin="其他";
    private String userid;
    private TextView xiayiye,shangyiye;
    private  int index=0;
    int pagerCount=3;
    private   int yeshu=0;
    private SelfDialog selfDialog;
    private TiaoWenBean.TemperaturesBean tiaoWenBean;
    private ListView listView;
    private SharedPreferences sp;
    private TextView num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiaowenshengqing);
        sp=getSharedPreferences("userinfo",0);
        userid=sp.getString("USERID","");
        shangyiye=findViewById(R.id.shangyiye);
        xiayiye=findViewById(R.id.xiayiye);
        num=findViewById(R.id.yeshu);
        tiaowenjutididian=findViewById(R.id.jiaoshi_lianxifangshi);
        didian=findViewById(R.id.jiaoshi_tiaowen_xiaoqu);
        //地点等级spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, didianneirong);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        didian.setAdapter(adapter);
        tousu_Radioggroup=findViewById(R.id.tousu_Radioggroup);
        radioButton1=findViewById(R.id.baoxiu_shangban);
        radioButton2=findViewById(R.id.baoxiu_jiaban);
        radioButton3=findViewById(R.id.baoxiu_qita);
        etStartTime =findViewById(R.id.tioawen_qishitime);
        etEndTime =findViewById(R.id.tiaowen_jieshutime);
        StartTime=findViewById(R.id.tioawen_qishi);
        taowen_tuichu=findViewById(R.id.taowen_tuichu);
        EndTime=findViewById(R.id.tiaowen_jieshu);
        xuesheng_tiaowen_tijiao =findViewById(R.id.xuesheng_tiaowen_tijiao);
        getJson();
        xuesheng_tiaowen_tijiao.setOnClickListener(this);
        listView=findViewById(R.id.tiaowen_listview);

        listView.setAdapter(adapter);
        StartTime.setOnTouchListener(this);
        EndTime.setOnTouchListener(this);
        taowen_tuichu.setOnClickListener(this);
        tousu_Radioggroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i ==  radioButton1.getId()) {
                  yuanyin="上班";
                } else if (i ==  radioButton2.getId()) {
                    yuanyin="加班";
                    }else if (i ==  radioButton3.getId()) {
                    yuanyin="其他";
                   }
            }
        });

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
                        tijiaotianwen(etStartTime.getText().toString(),tiaowenjutididian.getText().toString(),etEndTime.getText().toString(),didian.getSelectedItem().toString(),yuanyin);
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
            case R.id. taowen_tuichu:
                finish();
                break;
        }
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                Bundle bundle = msg.getData();
                String result = bundle.getString("code");
                Object object = msg.obj;
                if(object!=null){

                    Gson gson = new Gson();
                    TiaoWenBean textMessage = gson.fromJson(object.toString(), new TypeToken<  TiaoWenBean  >() {
                    }.getType());

                    list01=textMessage.getTemperatures();
                    adapter = new ListViewAdapter(tiaowenshengqingActivity.this,textMessage.getTemperatures());
                    if( textMessage.getTemperatures()!=null){

//                        for (int i=0;i<textMessage.getTemperatures().size();i++){
//
//                            tiaoWenBean=new TiaoWenBean.TemperaturesBean();
//                            tiaoWenBean.setTemId(textMessage.getTemperatures().get(i).getTemId());
//                            tiaoWenBean.setTemAdress(textMessage.getTemperatures().get(i).getTemAdress());
//                            tiaoWenBean.setDetailAdress(textMessage.getTemperatures().get(i).getDetailAdress());
//                            tiaoWenBean.setBiginTime(textMessage.getTemperatures().get(i).getBiginTime());
//                            tiaoWenBean.setEndTime(textMessage.getTemperatures().get(i).getEndTime());
//                            tiaoWenBean.setStatus(textMessage.getTemperatures().get(i).getStatus());
//                            tiaoWenBean.setStatusStr(textMessage.getTemperatures().get(i).getStatusStr());
//                            tiaoWenBean.setHandlerName(textMessage.getTemperatures().get(i).getHandlerName());
//                            tiaoWenBean.setHandlerTel(textMessage.getTemperatures().get(i).getHandlerTel());
//                            tiaoWenBean.setSubTime(textMessage.getTemperatures().get(i).getSubTime());
//                            tiaoWenBean.setSubTimeStr(textMessage.getTemperatures().get(i).getSubTimeStr());
//                            tiaoWenBean.setHandTeme(textMessage.getTemperatures().get(i).getHandTeme());
//                            list01.add(tiaoWenBean);
//                        }
//      adapter.setDeviceList(list01);

                        listView.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(listView);
                        shangyiye.setVisibility(View.INVISIBLE);
                        yeshu=(list01.size()%pagerCount==0?list01.size()/pagerCount:list01.size()/pagerCount+1);
                        if((list01.size()%pagerCount)==0){
                            num.setText( yeshu+"");
                        }else {
                            num.setText(yeshu+ "");
                        }
                        if ((list01.size()/pagerCount)==0)
                        {
                            xiayiye.setVisibility(View.INVISIBLE);
                            shangyiye.setVisibility(View.INVISIBLE);
                        }
                        shangyiye.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                prePager();
                            }
                        });
                        xiayiye.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.e("eee",index+"Pager");
                                nexPager();
                            }
                        });
                    }
                }
            }
            if (msg.what == 1){
                Bundle bundle = msg.getData();
                String result = bundle.getString("code");
                Object object = msg.obj;
                Log.e("eee",msg.obj+"--");
                getJson();
                yeshu=(list01.size()%pagerCount==0?list01.size()/pagerCount:list01.size()/pagerCount+1);
                if((list01.size()%pagerCount)==0){
                    num.setText( yeshu+"");
                }else {
                    num.setText(yeshu+ "");
                }
                if ((list01.size()/pagerCount)==0)
                {
                    xiayiye.setVisibility(View.INVISIBLE);
                    shangyiye.setVisibility(View.INVISIBLE);
                }
                shangyiye.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prePager();
                    }
                });
                xiayiye.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nexPager();
                    }
                });
            }
        }
    };

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    private void nexPager() {
        index++;
        Log.e("eee",index+"");

        adapter.notifyDataSetChanged();
        //隐藏上一个或下一个按钮
        if((list01.size()%pagerCount)==0){
            num.setText( yeshu+"");
        }else {
            num.setText(yeshu + "");
        }
        checkButton();
    }

    private void prePager() {
        index--;
        adapter.notifyDataSetChanged();
        if((list01.size()%pagerCount)==0){
            num.setText(yeshu+"");
        }else {
            num.setText(yeshu+ "");
        }
        //隐藏上一个或下一个按钮
        checkButton();
    }
    private void checkButton() {
        if (index<=0){
            shangyiye.setVisibility(View.INVISIBLE);
            xiayiye.setVisibility(View.VISIBLE);
        }else if (list01.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
            xiayiye.setVisibility(View.INVISIBLE);
            shangyiye.setVisibility(View.VISIBLE);
        }else {
            xiayiye.setVisibility(View.VISIBLE);
            shangyiye.setVisibility(View.VISIBLE);
        }
    }
    //调温显示初始化
    public void getJson(){
        index=0;
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey",getPackageName());
        mapParams.put("secret", "IOTCARE");
        mapParams.put("pagenum","1");//
        mapParams.put("pagesize","99");//
      //  mapParams.put("comUserId","1");
        mapParams.put("comUserId",userid);

        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.TIAOWENSELECT);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 0);
        httpLoginPost.execute(mapUrl, mapParams);
    }
    //提交调温
    public void tijiaotianwen(String biginTime,String detailAdress,String endTime,String temAdress,String temReason){
        index=0;
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey",getPackageName());
        mapParams.put("secret", "IOTCARE");
        mapParams.put("biginTime",biginTime);
        mapParams.put("detailAdress",detailAdress);
        mapParams.put("endTime", endTime);//
        mapParams.put("temAdress",temAdress);
        mapParams.put("temReason",temReason);
        mapParams.put("userId",userid);
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.TIAOWENINSERT);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 1);
        httpLoginPost.execute(mapUrl, mapParams);
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
    class ListViewAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<TiaoWenBean.TemperaturesBean> devices;


        public ListViewAdapter (Context context , List<TiaoWenBean.TemperaturesBean> list){

            this.inflater = LayoutInflater.from(context);
            devices = list;
        }

        public void clearDeviceList() {
            if (devices != null) {
                devices.clear();
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
            View view= LayoutInflater.from(tiaowenshengqingActivity.this).inflate(R.layout.tiaowenshenqing_item,parent,false);
            int pos = position + index * pagerCount;
            for (int i = 0; i < devices.size(); i++) {
             Log.e("aaaa", String.valueOf(devices.get(i).getStatusStr()));
            }

            TextView shijian = view.findViewById(R.id.tiaowen_item_shijian);
            TextView didian = view.findViewById(R.id.tiaowen_item_didian);
            TextView yuanyin = view.findViewById(R.id.tiaowen_item_yuanyin);
            TextView zhuangtai = view.findViewById(R.id.tiaowen_item_zhuangtai);

           shijian.setText(devices.get(position).getSubTime().toString().substring(0,10));
         yuanyin.setText(devices.get(position).getTemReason());
         String tiaowenzjiangtia=devices.get(position).getStatusStr();
         if (tiaowenzjiangtia.equals("未调温"))
         {
             zhuangtai.setText(devices.get(position).getStatusStr());
            zhuangtai.setBackgroundResource(R.drawable.text_shape);
            zhuangtai.setTextColor(tiaowenshengqingActivity.this.getResources().getColor(R.color.white));
         }else    zhuangtai.setText(devices.get(position).getStatusStr());
         didian.setText(devices.get(position).getTemAdress()+devices.get(position).getDetailAdress());

            return view;
        }
    }
}
