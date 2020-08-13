package xuesheng_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import xuesheng_Fragment.xuesheng_BaseFragment;

public class xuesheng_baoxiufragment extends xuesheng_BaseFragment {
  private View mContentView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.xuesheng_baoxiufragment,container,false);
        return mContentView;
    }




}
