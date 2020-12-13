package jiaoshi_Fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.tiaowenshengqingActivity;
import com.example.myapplication.zhanghaoshezhiActivity;

public class jiaoshi_myfragment extends jiaoshi_BaseFragment implements View.OnClickListener {
    private View mContentView;
    private LinearLayout daichuli,yiwancheng,daipingjia;
    private RelativeLayout quanbudingdan,  zhanghaoshezhi,tiaowenshezhi;
    private TextView name;
    private SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("userinfo", 0);
        mContext = getActivity();
       mContentView= inflater.inflate(R.layout.jiaoshi_myfragment,container,false);
        daichuli=mContentView.findViewById(R.id.jiaoshi_daichuli);
        yiwancheng=mContentView.findViewById(R.id.jiaoshi_yiwancheng);
        daipingjia=mContentView.findViewById(R.id.jiaoshi_daipingjia);
        quanbudingdan=mContentView.findViewById(R.id.jiaoshi_quanbudingdan);
        zhanghaoshezhi  =mContentView.findViewById(R.id.jiaoshi_zhanghaoshezhi);
        tiaowenshezhi=mContentView.findViewById(R.id.jiaoshi_tiaowenshezhi);
        name=mContentView.findViewById(R.id.name);
        name.setText( sp.getString("USER_NAME", ""));
        daichuli.setOnClickListener(this);
        yiwancheng.setOnClickListener(this);
        daipingjia.setOnClickListener(this);
        quanbudingdan.setOnClickListener(this);
        zhanghaoshezhi .setOnClickListener(this);
        tiaowenshezhi.setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.jiaoshi_daichuli:
                Intent intent1 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent1);
                break;
            case R.id.jiaoshi_yiwancheng:
                Intent intent2 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent2);
                break;
            case R.id.jiaoshi_daipingjia:
                Intent intent3 = new Intent(getContext(),baoxiumesaageActivity.class);
                startActivity(intent3);
                break;
            case R.id.jiaoshi_quanbudingdan:
                Intent intent4 = new Intent(getContext(), baoxiumesaageActivity.class);
                startActivity(intent4);
                break;
            case R.id.jiaoshi_zhanghaoshezhi:
                Intent intent5 = new Intent(getContext(), zhanghaoshezhiActivity.class);
                startActivity(intent5);
                break;
                case R.id.jiaoshi_tiaowenshezhi:
                Intent intent6 = new Intent(getContext(), tiaowenshengqingActivity.class);
                startActivity(intent6);
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
