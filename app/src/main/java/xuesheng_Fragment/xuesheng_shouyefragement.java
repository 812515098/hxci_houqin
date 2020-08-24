package xuesheng_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.Bean.XinxichaxunBean;
import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.tongzhiActivity;
import com.example.myapplication.xinxichaxunActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jiaoshi_Fragment.jiaoshi_BaseFragment;

public class xuesheng_shouyefragement extends xuesheng_BaseFragment {
    private View mContentView;
    private ImageButton xuesheng_baoxiu_IB,xuesheng_chaxun_IB;
    ListView messagelv;
    ArrayList data ;
    ArrayAdapter<String> myadapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
         mContentView=inflater.inflate(R.layout.xuesheng_shouyefragment,container,false);
        messagelv= mContentView.findViewById(R.id.xuesheng_messagelv);
        xuesheng_chaxun_IB=  mContentView.findViewById(R.id.xuesheng_chaxun_IB);
        xuesheng_baoxiu_IB=mContentView.findViewById(R.id.xuesheng_baoxiu_IB);
        myadapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,list());
        messagelv.setAdapter(myadapter);
        xuesheng_chaxun_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), xinxichaxunActivity.class);
                startActivity(intent);
            }
        });
        messagelv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*
            *
            parent  发生点击动作的AdapterView。
            view   在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
            position　视图在adapter中的位置。
             id  被点击元素的行id。*/
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getActivity(), tongzhiActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(),"aaa",Toast.LENGTH_SHORT).show();
            }
        });
        xuesheng_baoxiu_IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), baoxiumesaageActivity.class);
                startActivity(intent);
            }
        });
        return mContentView;
    }
    public ArrayList list(){
        data =new ArrayList();
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        return  data;
    }

    @Override
    public void onStart() {

        super.onStart();
    }
}
