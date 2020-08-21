package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import jiaoshi_Fragment.jiaoshi_baoxiufragment;
import jiaoshi_Fragment.jiaoshi_myfragment;
import jiaoshi_Fragment.jiaoshi_shouyefragement;


public class index_jiaoshiActivity extends AppCompatActivity implements View.OnClickListener {
    public static index_jiaoshiActivity instance;
    private FragmentManager fm;
    private Fragment mCommonFragmentOne;
    private jiaoshi_myfragment jiaoshi_myframent;
    private jiaoshi_baoxiufragment jiaoshi_baoxiufragment;
    private jiaoshi_shouyefragement jiaoshi_shouyefragement;
    private Fragment mCurrent;
    private RelativeLayout shouye_Relat,baoxiu_Relat,my_relat;
    private ImageView shouye_im,baoxiu_im,my_im;
    private TextView shouye_tv,baoxiu_tv,my_tv;
    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_jiaoshi);
        initView();

    }
   /* protected int getLayoutId() {
        instance = this;
        return R.layout.index_jiaoshi;
    }*/

    protected void initView() {

        shouye_Relat = findViewById(R.id.shouye_Relat);
        shouye_Relat.setOnClickListener(this);
        baoxiu_Relat =  findViewById(R.id.baoxiu_relat);
        baoxiu_Relat.setOnClickListener(this);
        my_relat = findViewById(R.id.my_relat);
        my_relat.setOnClickListener(this);
        shouye_im = findViewById(R.id.shouye_im);
        shouye_tv = findViewById(R.id.shouye_textview);
        baoxiu_im = findViewById(R.id.baoxiuim);
        baoxiu_tv = findViewById(R.id.baoxiutv);
        my_im = findViewById(R.id.my_im);
        my_tv =  findViewById(R.id.mine_tv);

        shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye));
        shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
        jiaoshi_shouyefragement = new jiaoshi_shouyefragement();
        fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, jiaoshi_shouyefragement);
        fragmentTransaction.commit();
    }


    protected void initData() {

    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.shouye_Relat:
                labelSelection(0);
                break;
            case R.id.baoxiu_relat:
                labelSelection(1);
                break;
            case R.id.my_relat:
                labelSelection(2);
                break;
            default:
                break;
        }
    }

    public void labelSelection(int position) {

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (position) {
            case 0:
                shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye));
                shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
                baoxiu_im.setImageDrawable(getResources().getDrawable(R.drawable.banxiu_2));
                baoxiu_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                my_im.setImageDrawable(getResources().getDrawable(R.drawable.my_2));
                my_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(jiaoshi_baoxiufragment, fragmentTransaction);
                hideFragment(jiaoshi_myframent, fragmentTransaction);
                if (jiaoshi_shouyefragement == null) {
                    jiaoshi_shouyefragement = new jiaoshi_shouyefragement();
                    fragmentTransaction.add(R.id.content_layout, jiaoshi_shouyefragement);
                } else {
                    mCurrent = jiaoshi_shouyefragement;
                    fragmentTransaction.show(jiaoshi_shouyefragement);
                }
                break;
            case 1:
                baoxiu_im.setImageDrawable(getResources().getDrawable(R.drawable.banxiu));
                baoxiu_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
                shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye_2));
                shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                my_im.setImageDrawable(getResources().getDrawable(R.drawable.my_2));
                my_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(jiaoshi_shouyefragement, fragmentTransaction);
                hideFragment(jiaoshi_myframent, fragmentTransaction);
                if (jiaoshi_baoxiufragment == null) {
                    jiaoshi_baoxiufragment = new jiaoshi_baoxiufragment();
                    fragmentTransaction.add(R.id.content_layout, jiaoshi_baoxiufragment);
                } else {
                    mCurrent = jiaoshi_baoxiufragment;
                    fragmentTransaction.show(jiaoshi_baoxiufragment);
                }
                break;
            case 2:
                my_im.setImageDrawable(getResources().getDrawable(R.drawable.my));
                my_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
                shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye_2));
                shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                baoxiu_im.setImageDrawable(getResources().getDrawable(R.drawable.banxiu_2));
                baoxiu_tv.setTextColor(getResources().getColor(R.color.comui_tab));
                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(jiaoshi_shouyefragement, fragmentTransaction);
                hideFragment(jiaoshi_baoxiufragment, fragmentTransaction);
                if (jiaoshi_myframent == null) {
                    jiaoshi_myframent = new jiaoshi_myfragment();
                    fragmentTransaction.add(R.id.content_layout, jiaoshi_myframent);
                } else {
                    mCurrent = jiaoshi_myframent;
                    fragmentTransaction.show(jiaoshi_myframent);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();

    }

   /* public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                startActivity(intent);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}*/
}
