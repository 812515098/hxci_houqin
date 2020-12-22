package jiaoshi_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ShouyeMsgInfoListAdapter;
import com.example.myapplication.Bean.MsgInfo;
import com.example.myapplication.Bean.TestMessage;
import com.example.myapplication.Bean.TiaoWenBean;
import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.databinding.JiaoshiShouyefragmentBinding;
import com.example.myapplication.http.HttpSyncPostUtil;
import com.example.myapplication.http.HttpURL;
import com.example.myapplication.loginActivity;
import com.example.myapplication.tongzhiActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class jiaoshi_shouyefragement extends jiaoshi_BaseFragment {
    private View mContentView;
    private RelativeLayout baoxiu_IBRe;

    RecyclerView messagelv;
    ArrayList data ;
    ArrayAdapter<String> myadapter;
    ArrayList<MsgInfo> msgInfos=new ArrayList<>();
    ShouyeMsgInfoListAdapter adapter;
    JiaoshiShouyefragmentBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
//        binding =  DataBindingUtil.inflate(inflater,R.layout.jiaoshi_shouyefragment, container, false);

        mContentView=inflater.inflate(R.layout.jiaoshi_shouyefragment,container,false);
        messagelv= mContentView.findViewById(R.id.messagelv);
        baoxiu_IBRe=  mContentView.findViewById(R.id.baoxiu_IBRe);
//        myadapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,list());
//        messagelv.setAdapter(myadapter);
//        messagelv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            /*
//            *
//            parent  发生点击动作的AdapterView。
//            view   在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
//            position　视图在adapter中的位置。
//             id  被点击元素的行id。*/
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent intent = new Intent(getActivity(), tongzhiActivity.class);
//                startActivity(intent);
//                //Toast.makeText(getActivity(),"aaa",Toast.LENGTH_SHORT).show();
//            }
//        });
        baoxiu_IBRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), baoxiumesaageActivity.class);
                startActivity(intent);
            }
        });
        getmsgInfo();
        adapter = new ShouyeMsgInfoListAdapter(R.layout.item_shouye_msg_info, msgInfos);
        messagelv.setLayoutManager(new LinearLayoutManager(getContext()));
        messagelv.setAdapter(adapter);
        return mContentView;
    }

    private void getmsgInfo() {
        /*网络登录操作*/
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey", getContext().getPackageName());
        mapParams.put("secret", "IOTCARE");
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.SHOUYEMAGINFO);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 1);
        httpLoginPost.execute(mapUrl, mapParams);
    }
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if(msg.what==1) {
                Bundle bundle = msg.getData();
                String result = bundle.getString("code");
                Log.e("aaaa", "result" + msg.obj);
                Object object = msg.obj;
                Log.e("ping","数据--->>"+msg.obj);
                if (object != null) {
                    Gson gson = new Gson();


                    try{
                        JSONArray jsonArray = new JSONArray(object.toString());
                        for(int i = 0;i < jsonArray.length();i++){
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            MsgInfo msgInfo = new MsgInfo();
                            msgInfo.setMsgId(jsonObject.optInt("msgId"));
                            msgInfo.setContent(jsonObject.optString("content"));
                            msgInfo.setIsDel(jsonObject.optInt("isDel"));
                            msgInfo.setReleaseTel(jsonObject.optString("releaseTel"));
                            msgInfo.setReleaseTime(jsonObject.optString("releaseTime"));
                            msgInfo.setReleaseTimeStr(jsonObject.optString("releaseTimeStr"));
                            msgInfo.setReleaseUser(jsonObject.optString("releaseUser"));
                            msgInfo.setTitle(jsonObject.optString("title"));
                            msgInfo.setType(jsonObject.optInt("type"));
                            msgInfo.setTypeStr(jsonObject.optString("typeStr"));
                            msgInfos.add(msgInfo);
                            adapter.setNewData(msgInfos);
                            adapter.notifyDataSetChanged();
                        }
                    }catch (Exception e){
                        Toast.makeText(getContext(), "服务器或网络错误", Toast.LENGTH_LONG).show();
                        e.printStackTrace();}

                    } else {
                        Toast.makeText(getContext(), "服务器或网络错误", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(), "服务器或网络错误", Toast.LENGTH_LONG).show();
                }
            }


        };

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
