package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import weixiurenyuan_Fragment.weixiurenyuan_baoxiufragment;
import weixiurenyuan_Fragment.weixiurenyuan_myfragment;
import weixiurenyuan_Fragment.weixiurenyuan_shouyefragement;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class index_weixiurenyuanActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fm;
    private Fragment mCommonFragmentOne;
    private weixiurenyuan_myfragment weixiurenyuan_myframent;
    private weixiurenyuan_Fragment.weixiurenyuan_baoxiufragment weixiurenyuan_baoxiufragment;
    private weixiurenyuan_Fragment.weixiurenyuan_shouyefragement weixiurenyuan_shouyefragement;
    private Fragment mCurrent;
    private RelativeLayout shouye_Relat,baoxiu_Relat,my_relat;
    private ImageView shouye_im,baoxiu_im,my_im;
    private TextView shouye_tv,baoxiu_tv,my_tv;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_weixiurenyuan);
        initView();

    }
   /* protected int getLayoutId() {
        instance = this;
        return R.layout.index_jiaoshi;
    }*/

    protected void initView() {

        shouye_Relat = findViewById(R.id.weixiurenyuan_shouye_Relat);
        shouye_Relat.setOnClickListener(this);
        baoxiu_Relat =  findViewById(R.id.weixiurenyuan_baoxiu_relat);
        baoxiu_Relat.setOnClickListener(this);
        my_relat = findViewById(R.id.weixiurenyuan_my_relat);
        my_relat.setOnClickListener(this);
        shouye_im = findViewById(R.id.weixiurenyuan_shouye_im);
        shouye_tv = findViewById(R.id.weixiurenyuan_shouye_textview);
        baoxiu_im = findViewById(R.id.weixiurenyuan_baoxiuim);
        baoxiu_tv = findViewById(R.id.weixiurenyuan_baoxiutv);
        my_im = findViewById(R.id.weixiurenyuan_my_im);
        my_tv =  findViewById(R.id.weixiurenyuan_mine_tv);
        shouye_im.setImageDrawable(getResources().getDrawable(R.drawable.shouye));
        shouye_tv.setTextColor(getResources().getColor(R.color.comui_tab_selected));
        weixiurenyuan_shouyefragement = new weixiurenyuan_shouyefragement();
        fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.weixiurenyuan_content_layout, weixiurenyuan_shouyefragement);
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
            case R.id.weixiurenyuan_shouye_Relat:
                labelSelection(0);
                break;
            case R.id.weixiurenyuan_baoxiu_relat:
                labelSelection(1);
                break;
            case R.id.weixiurenyuan_my_relat:
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
                hideFragment(weixiurenyuan_baoxiufragment, fragmentTransaction);
                hideFragment(weixiurenyuan_myframent, fragmentTransaction);
                if (weixiurenyuan_shouyefragement == null) {
                    weixiurenyuan_shouyefragement = new weixiurenyuan_shouyefragement();
                    fragmentTransaction.add(R.id.weixiurenyuan_content_layout, weixiurenyuan_shouyefragement);
                } else {
                    mCurrent = weixiurenyuan_shouyefragement;
                    fragmentTransaction.show(weixiurenyuan_shouyefragement);
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
                hideFragment(weixiurenyuan_shouyefragement, fragmentTransaction);
                hideFragment(weixiurenyuan_myframent, fragmentTransaction);
                if (weixiurenyuan_baoxiufragment == null) {
                    weixiurenyuan_baoxiufragment = new weixiurenyuan_baoxiufragment();
                    fragmentTransaction.add(R.id.weixiurenyuan_content_layout, weixiurenyuan_baoxiufragment);
                } else {
                    mCurrent = weixiurenyuan_baoxiufragment;
                    fragmentTransaction.show(weixiurenyuan_baoxiufragment);
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
                hideFragment(weixiurenyuan_shouyefragement, fragmentTransaction);
                hideFragment(weixiurenyuan_baoxiufragment, fragmentTransaction);
                if (weixiurenyuan_myframent == null) {
                    weixiurenyuan_myframent = new weixiurenyuan_myfragment();
                    fragmentTransaction.add(R.id.weixiurenyuan_content_layout,weixiurenyuan_myframent);
                } else {
                    mCurrent = weixiurenyuan_myframent;
                    fragmentTransaction.show(weixiurenyuan_myframent);
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
