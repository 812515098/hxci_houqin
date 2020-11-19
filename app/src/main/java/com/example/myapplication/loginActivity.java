package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Bean.LoginBean;
import com.example.myapplication.http.HttpSyncPostUtil;
import com.example.myapplication.http.HttpURL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Checksum;

import androidx.annotation.Nullable;
public class loginActivity extends Activity implements View.OnClickListener {
    private SharedPreferences sp;//记住用户名密码
    EditText user,pass; //输入的用户名密码
    CheckBox jizummima;//记住密码选项
    Button denglu;//登录按钮
    ImageView yincangmima;//显示密码的图片
    private boolean canSee=false;
    private String name;//用户名
    private String pwd;//密码
    private String shengfen;//身份 1学生 2老师 3维修人员 4管理员
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user=findViewById(R.id.Edit_user);
        pass=findViewById(R.id.Edit_pass);
        jizummima=findViewById(R.id.Check_jizhumima);
        denglu=findViewById(R.id.Button_denglu);
        yincangmima=findViewById(R.id.Image_yincangmima);
        yincangmima.setOnClickListener(this);
         denglu.setOnClickListener(this);
        sp=getSharedPreferences("userinfo",0);
        name=sp.getString("USERZHANGHAO","");
        pwd=sp.getString("PASSWORD","");
        boolean chooseRemember=sp.getBoolean("remember",false);
        if(chooseRemember){
            user.setText(name);
            pass.setText(pwd);
            jizummima.setChecked(chooseRemember);//勾选记住密码
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Image_yincangmima:

                if (canSee==false){
                    //如果是不能看到密码的情况下，
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yincangmima.setImageResource(R.drawable.denglu_yj);
                    canSee=true;
                }else {
                    //如果是能看到密码的状态下
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yincangmima.setImageResource(R.drawable.denglu_yc);
                    canSee=false;
                }
                break;
            case R.id.Button_denglu:
                login();
                break;
        }
    }
    private void login(){
      name=user.getText().toString();
      pwd=pass.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        /*网络登录操作*/
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey", this.getPackageName());
        mapParams.put("secret", "IOTCARE");
        mapParams.put("userName", name);
        mapParams.put("userTel", pwd);
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.LOGIN);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 3);
        httpLoginPost.execute(mapUrl, mapParams);
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==3) {
                Bundle bundle = msg.getData();
                String result = bundle.getString("code");
                Log.e("aaaa", "result" + msg.obj);
                Object object = msg.obj;
                if (object != null) {
                            Gson gson = new Gson();
                            LoginBean<LoginBean.UserBean> LoginResultBean = gson.fromJson(object.toString(), new TypeToken<LoginBean<LoginBean.UserBean>>() {
                            }.getType());
                            String Result = LoginResultBean.getResult();
                            if (Result.equals("ok")) {
                                int stuno = LoginResultBean.getUser().getUserId();
                                String name = LoginResultBean.getUser().getUserName();
                                String paw =pwd;//正确的密码
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USER_NAME", name);
                                editor.putString("PASSWORD", paw);
                                editor.putString("USERID", stuno+"");
                                editor.putString("USERZHANGHAO", name);//留着备用
                                if (jizummima.isChecked()) {
                                    editor.putBoolean("remember", true);

                                } else {
                                    editor.putBoolean("remember", false);
                                }
                                editor.commit();
                                switch (LoginResultBean.getUser().getIdentity()) {
                                    case 1:
                                        Intent intent1 = new Intent(loginActivity.this,index_jiaoshiActivity. class);//老师
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2= new Intent(loginActivity.this, index_weixiurenyuanActivity.class);//工人
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent(loginActivity.this, index_xueshengActivity.class);//学生
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent(loginActivity.this, index_guanliyuanActivity.class);//管理员
                                        startActivity(intent4);
                                        break;
                                }
                            } else
                                if (Result.equals("no")) {
                                Toast.makeText(loginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(loginActivity.this, "服务器或网络错误", Toast.LENGTH_LONG).show();
                            }
                } else {
                    Toast.makeText(loginActivity.this, "服务器或网络错误", Toast.LENGTH_LONG).show();
                }
            }


        }};
}
