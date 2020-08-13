package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import guanliyuan_Fragment.guanliyuan_baoxiufragment;
import guanliyuan_Fragment.guanliyuan_myfragment;
import guanliyuan_Fragment.guanliyuan_shouyefragement;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class index_guanliyuanActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fm;
    private Fragment mCommonFragmentOne;
    private guanliyuan_myfragment guanliyuan_myframent;
    private guanliyuan_baoxiufragment guanliyuan_baoxiufragment;
    private guanliyuan_shouyefragement guanliyuan_shouyefragement;
    private Fragment mCurrent;
    private RelativeLayout shouye_Relat,baoxiu_Relat,my_relat;
    private ImageView shouye_im,baoxiu_im,my_im;
    private TextView shouye_tv,baoxiu_tv,my_tv;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_guanliyuan);
        initView();

    }
   /* protected int getLayoutId() {
        instance = this;
        return R.layout.index_jiaoshi;
    }*/

    protected void initView() {

        shouye_Relat = findViewById(R.id.guanliyuan_shouye_Relat);
        shouye_Relat.setOnClickListener(this);
        baoxiu_Relat =  findViewById(R.id.guanliyuan_baoxiu_relat);
        baoxiu_Relat.setOnClickListener(this);
        my_relat = findViewById(R.id.guanliyuan_my_relat);
        my_relat.setOnClickListener(this);
        shouye_im = findViewById(R.id.guanliyuan_shouye_im);
        shouye_tv = findViewById(R.id.guanliyuan_shouye_textview);
        baoxiu_im = findViewById(R.id.guanliyuan_baoxiuim);
        baoxiu_tv = findViewById(R.id.guanliyuan_baoxiutv);
        my_im = findViewById(R.id.guanliyuan_my_im);
        my_tv =  findViewById(R.id.guanliyuan_mine_tv);
        shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye));
        shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
        guanliyuan_shouyefragement = new guanliyuan_shouyefragement();
        fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.guanliyuan_content_layout,guanliyuan_shouyefragement);
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
            case R.id.guanliyuan_shouye_Relat:
                labelSelection(0);
                break;
            case R.id.guanliyuan_baoxiu_relat:
                labelSelection(1);
                break;
            case R.id.guanliyuan_my_relat:
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
                hideFragment(guanliyuan_baoxiufragment, fragmentTransaction);
                hideFragment(guanliyuan_myframent, fragmentTransaction);
                if (guanliyuan_shouyefragement == null) {
                    guanliyuan_shouyefragement = new guanliyuan_shouyefragement();
                    fragmentTransaction.add(R.id.guanliyuan_content_layout, guanliyuan_shouyefragement);
                } else {
                    mCurrent = guanliyuan_shouyefragement;
                    fragmentTransaction.show(guanliyuan_shouyefragement);
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
                hideFragment(guanliyuan_shouyefragement, fragmentTransaction);
                hideFragment(guanliyuan_myframent, fragmentTransaction);
                if (guanliyuan_baoxiufragment == null) {
                    guanliyuan_baoxiufragment = new guanliyuan_baoxiufragment();
                    fragmentTransaction.add(R.id.guanliyuan_content_layout, guanliyuan_baoxiufragment);
                } else {
                    mCurrent = guanliyuan_baoxiufragment;
                    fragmentTransaction.show(guanliyuan_baoxiufragment);
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
                hideFragment(guanliyuan_shouyefragement, fragmentTransaction);
                hideFragment(guanliyuan_baoxiufragment, fragmentTransaction);
                if (guanliyuan_myframent == null) {
                    guanliyuan_myframent = new guanliyuan_myfragment();
                    fragmentTransaction.add(R.id.guanliyuan_content_layout,guanliyuan_myframent);
                } else {
                    mCurrent = guanliyuan_myframent;
                    fragmentTransaction.show(guanliyuan_myframent);
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
