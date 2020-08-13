package xuesheng_Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jiaoshi_Fragment.jiaoshi_BaseFragment;

public class xuesheng_myfragment extends jiaoshi_BaseFragment {
    private View mContentView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
       mContentView= inflater.inflate(R.layout.xuesheng_myfragment,container,false);
        return mContentView;
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
