package xuesheng_Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.Adapter.SelfDialog;
import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.index_weixiurenyuanActivity;
import com.example.myapplication.loginActivity;
import com.example.myapplication.zhanghaoshezhiActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jiaoshi_Fragment.jiaoshi_BaseFragment;

public class xuesheng_myfragment extends jiaoshi_BaseFragment implements View.OnClickListener {
    private View mContentView;
    private LinearLayout  daichuli,yiwancheng,daipingjia;
    private RelativeLayout quanbudingdan,  zhanghaoshezhi;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
       mContentView= inflater.inflate(R.layout.xuesheng_myfragment,container,false);
       daichuli=mContentView.findViewById(R.id.xuesheng_daichuli);
        yiwancheng=mContentView.findViewById(R.id.xuesheng_yiwancheng);
        daipingjia=mContentView.findViewById(R.id.xuesheng_daipingjia);
        quanbudingdan=mContentView.findViewById(R.id.xuesheng_quanbudingdan);
      zhanghaoshezhi  =mContentView.findViewById(R.id.xuesheng_zhanghaoshezhi);
        daichuli.setOnClickListener(this);
        yiwancheng.setOnClickListener(this);
        daipingjia.setOnClickListener(this);
        quanbudingdan.setOnClickListener(this);
        zhanghaoshezhi .setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.xuesheng_daichuli:
                Intent intent1 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent1);
                break;
            case R.id.xuesheng_yiwancheng:
                Intent intent2 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent2);
                break;
            case R.id.xuesheng_daipingjia:
                Intent intent3 = new Intent(getContext(),baoxiumesaageActivity.class);
                startActivity(intent3);
                break;
            case R.id.xuesheng_quanbudingdan:
                Intent intent4 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent4);
                break;
            case R.id.xuesheng_zhanghaoshezhi:
                Intent intent5 = new Intent(getContext(), zhanghaoshezhiActivity.class);
                startActivity(intent5);
                break;
        }
    }
    /*   *//*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        initData();
        return mContentView;
    }*//*

//
    public static final String BUNDLE_TITLE = "title";


    private Unbinder unbinder;





    private void initView() {
        unbinder = ButterKnife.bind(this, mContentView);
    }

    //初始化数据
    private void initData() {

    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static jiaoshi_myfragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        jiaoshi_myfragment fragment = new jiaoshi_myfragment();
        fragment.setArguments(bundle);
        return fragment;
    }
*/
}
