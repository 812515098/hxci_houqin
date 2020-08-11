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

/*
        if(isChecked){
            //如果选中，显示密码
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            //否则隐藏密码
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());*/

    }
}
