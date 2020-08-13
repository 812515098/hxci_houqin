package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import xuesheng_Fragment.xuesheng_baoxiufragment;
import xuesheng_Fragment.xuesheng_myfragment;
import xuesheng_Fragment.xuesheng_shouyefragement;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class index_xueshengActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fm;
    private Fragment mCommonFragmentOne;
    private xuesheng_myfragment xuesheng_myframent;
    private xuesheng_baoxiufragment xuesheng_baoxiufragment;
    private xuesheng_shouyefragement xuesheng_shouyefragement;
    private Fragment mCurrent;
    private RelativeLayout shouye_Relat,baoxiu_Relat,my_relat;
    private ImageView shouye_im,baoxiu_im,my_im;
    private TextView shouye_tv,baoxiu_tv,my_tv;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_xuesheng);
        initView();

    }
   /* protected int getLayoutId() {
        instance = this;
        return R.layout.index_jiaoshi;
    }*/

    protected void initView() {

        shouye_Relat = findViewById(R.id.xuesheng_shouye_Relat);
        shouye_Relat.setOnClickListener(this);
        baoxiu_Relat =  findViewById(R.id.xuesheng_baoxiu_relat);
        baoxiu_Relat.setOnClickListener(this);
        my_relat = findViewById(R.id.xuesheng_my_relat);
        my_relat.setOnClickListener(this);
        shouye_im = findViewById(R.id.xuesheng_shouye_im);
        shouye_tv = findViewById(R.id.xuesheng_shouye_textview);
        baoxiu_im = findViewById(R.id.xuesheng_baoxiuim);
        baoxiu_tv = findViewById(R.id.xuesheng_baoxiutv);
        my_im = findViewById(R.id.xuesheng_my_im);
        my_tv =  findViewById(R.id.xuesheng_mine_tv);
        shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye));
        shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
        xuesheng_shouyefragement = new xuesheng_shouyefragement();
        fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.xuesheng_content_layout, xuesheng_shouyefragement);
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
            case R.id.xuesheng_shouye_Relat:
                labelSelection(0);
                break;
            case R.id.xuesheng_baoxiu_relat:
                labelSelection(1);
                break;
            case R.id.xuesheng_my_relat:
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
                hideFragment(xuesheng_baoxiufragment, fragmentTransaction);
                hideFragment(xuesheng_myframent, fragmentTransaction);
                if (xuesheng_shouyefragement == null) {
                    xuesheng_shouyefragement = new xuesheng_shouyefragement();
                    fragmentTransaction.add(R.id.xuesheng_content_layout, xuesheng_shouyefragement);
                } else {
                    mCurrent = xuesheng_shouyefragement;
                    fragmentTransaction.show(xuesheng_shouyefragement);
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
                hideFragment(xuesheng_shouyefragement, fragmentTransaction);
                hideFragment(xuesheng_myframent, fragmentTransaction);
                if (xuesheng_baoxiufragment == null) {
                   xuesheng_baoxiufragment = new xuesheng_baoxiufragment();
                    fragmentTransaction.add(R.id.xuesheng_content_layout, xuesheng_baoxiufragment);
                } else {
                    mCurrent = xuesheng_baoxiufragment;
                    fragmentTransaction.show(xuesheng_baoxiufragment);
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
                hideFragment(xuesheng_shouyefragement, fragmentTransaction);
                hideFragment(xuesheng_baoxiufragment, fragmentTransaction);
                if (xuesheng_myframent == null) {
                    xuesheng_myframent = new xuesheng_myfragment();
                    fragmentTransaction.add(R.id.xuesheng_content_layout, xuesheng_myframent);
                } else {
                    mCurrent = xuesheng_myframent;
                    fragmentTransaction.show(xuesheng_myframent);
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
