package com.example.myapplication;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle; 
import android.widget.EditText;
import androidx.annotation.Nullable;
public class loginActivity extends Activity {
    EditText user,pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user=findViewById(R.id.Edit_user);
        pass=findViewById(R.id.Edit_pass);
        Drawable searchEditDraw = getResources().getDrawable(R.drawable.denglu_zh);
        Drawable searchEditDrawmm = getResources().getDrawable(R.drawable.denglu_mm);
        Drawable searchEditDrawyc = getResources().getDrawable(R.drawable.denglu_yc);
        searchEditDraw.setBounds(0, 0, 30, 30);
        searchEditDrawmm.setBounds(0, 0, 30, 30);
        searchEditDrawyc.setBounds(0, 0, 25, 25);
        user.setCompoundDrawables(searchEditDraw, null, null, null);
        pass.setCompoundDrawables(searchEditDrawmm,null,searchEditDrawyc,null);

/*
        if(isChecked){
            //如果选中，显示密码
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            //否则隐藏密码
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());*/

    }
}
