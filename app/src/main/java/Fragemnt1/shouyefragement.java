package Fragemnt1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.tongzhiActivity;

import java.util.ArrayList;

public class shouyefragement extends BaseFragment  {
    private View mContentView;
    private RelativeLayout baoxiu_IBRe;

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
         mContentView=inflater.inflate(R.layout.shouyefragment,container,false);
        messagelv= mContentView.findViewById(R.id.messagelv);
        baoxiu_IBRe=  mContentView.findViewById(R.id.baoxiu_IBRe);
        myadapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,list());
        messagelv.setAdapter(myadapter);
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
        baoxiu_IBRe.setOnClickListener(new View.OnClickListener() {
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
