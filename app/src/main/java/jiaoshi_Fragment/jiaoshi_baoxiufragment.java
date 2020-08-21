package jiaoshi_Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class jiaoshi_baoxiufragment extends jiaoshi_BaseFragment  implements View.OnClickListener {
  private View mContentView;
  private Button baoxiu_tijiao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.jiaoshi_baoxiufragment,container,false);
        baoxiu_tijiao=  mContentView.findViewById(R.id.baoxiu_tijiao);
        baoxiu_tijiao.setOnClickListener(this);

        return mContentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.baoxiu_tijiao:
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.yiwancheng).setMessage("你确定提交报修吗？");
                    builder.setPositiveButton("确定",null);
                    builder.setNegativeButton("取消",null);
                    builder.create().show();
                    break;
        }

    }
}
