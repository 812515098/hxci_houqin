package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.zip.Checksum;

import androidx.annotation.Nullable;
public class loginActivity extends Activity implements View.OnClickListener {
    private SharedPreferences sp;//记住用户名密码
    EditText user,pass;
    CheckBox jizummima;
    Button denglu;
    ImageView yincangmima;
    private boolean canSee=false;
    private String name;
    private String pwd;
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
        switch (name){
            case "1": //学生
                jizhumima();
                Intent intent1 = new Intent(loginActivity.this, index_xueshengActivity.class);
                startActivity(intent1);
                break;
            case "2"://教师
                jizhumima();
                Intent intent2 = new Intent(loginActivity.this, index_jiaoshiActivity.class);
                startActivity(intent2);
                break;
            case "3"://维修人员
                jizhumima();
                Intent intent3 = new Intent(loginActivity.this, index_weixiurenyuanActivity.class);
                startActivity(intent3);
                break;
            case "4"://管理员
                jizhumima();
                Intent intent4 = new Intent(loginActivity.this, index_guanliyuanActivity.class);
                startActivity(intent4);
                break;
                default:
                    Toast.makeText(this,"用户名密码错误",Toast.LENGTH_LONG).show();
        }

    }
private  void jizhumima(){

    SharedPreferences.Editor editor = sp.edit();
    editor.putString("USERZHANGHAO",name);
    editor.putString("PASSWORD", pwd);
    if (jizummima.isChecked()) {
        editor.putBoolean("remember", true);

    } else {
        editor.putBoolean("remember", false);
    }
    editor.commit();
}



}
