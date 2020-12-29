package weixiurenyuan_Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.example.myapplication.Adapter.ShouyeMsgInfoListAdapter;
import com.example.myapplication.Adapter.WeixiurenShouyeListAdapter;
import com.example.myapplication.Bean.TiaoWenBean;
import com.example.myapplication.Bean.WeixiurenShouyeMsgList;
import com.example.myapplication.R;
import com.example.myapplication.baoxiumesaageActivity;
import com.example.myapplication.databinding.WeixiurenyuanShouyefragmentBinding;
import com.example.myapplication.http.HttpSyncPostUtil;
import com.example.myapplication.http.HttpURL;
import com.example.myapplication.tongzhiActivity;
import com.example.myapplication.view.LoadMoreFooterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class weixiurenyuan_shouyefragement extends weixiurenyuan_BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private View mContentView;
  //  private RelativeLayout baoxiu_IBRe;
    ListView messagelv;
    ArrayList data ;
    ArrayAdapter<String> myadapter;
    WeixiurenShouyeListAdapter adapter;
    ArrayList<WeixiurenShouyeMsgList.RepairsBean> repairsBeans=new ArrayList<>();
    WeixiurenyuanShouyefragmentBinding binding;
    int pagenum=1;
    int tatal=1;
    private String userid;
    private SharedPreferences sp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        binding =  DataBindingUtil.inflate(inflater,R.layout.weixiurenyuan_shouyefragment, container, false);
//         mContentView=inflater.inflate(R.layout.weixiurenyuan_shouyefragment,container,false);
//        messagelv= mContentView.findViewById(R.id.messagelv);
//  //      baoxiu_IBRe=  mContentView.findViewById(R.id.baoxiu_IBRe);
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
  //     baoxiu_IBRe.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
  //              Intent intent=new Intent(getActivity(), baoxiumesaageActivity.class);
 //               startActivity(intent);
  //          }
   //     });

        sp=getContext().getSharedPreferences("userinfo",0);
        userid=sp.getString("USERID","");
        getJson(pagenum,userid);
        adapter = new WeixiurenShouyeListAdapter(R.layout.item_weixiurenyuan_shouye, repairsBeans);
        binding.list.swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.list.swipeTarget.setAdapter(adapter);
        binding.list.swipeToLoadLayout.setOnRefreshListener(this);
        binding.list.swipeToLoadLayout.setOnLoadMoreListener(this);
        return binding.getRoot();
    }

    //调温显示初始化
    public void getJson(int pagenum,String userId){

        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey",getContext().getPackageName());
        mapParams.put("secret", "IOTCARE");
        mapParams.put("pagenum",pagenum+"");//
        mapParams.put("pagesize","99");//
        mapParams.put("userId","1");
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.WEIXIURENSHOUYEMAGINFO);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 0);
        httpLoginPost.execute(mapUrl, mapParams);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                Bundle bundle = msg.getData();
                String result = bundle.getString("code");
                Object object = msg.obj;
                Log.e("ping","object--->>"+object);

                if (object != null) {

                    Gson gson = new Gson();
                    WeixiurenShouyeMsgList textMessage = gson.fromJson(object.toString(), new TypeToken<WeixiurenShouyeMsgList>() {
                    }.getType());
                    Log.e("ping","textMessage--->>"+textMessage);
                    if(textMessage!=null){
                        repairsBeans=textMessage.getRepairs();
                        adapter.setNewData(repairsBeans);
                        adapter.notifyDataSetChanged();
                        tatal=textMessage.getTotal();
                    }

                }
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
    @Override
    public void onLoadMore() {
//        if(tatal>=pagenum){
//            if(tatal==pagenum){
//
//            }else {
//                pagenum++;
//            }
//           getJson(pagenum,userid);
//        }
        binding.list.swipeToLoadLayout.setRefreshing(false);
        binding.list.swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
//        if(tatal>=pagenum){
//            if(pagenum==1){
//
//            }else {
//                pagenum--;
//            }
//            getJson(pagenum,userid);
//        }
        binding.list.swipeToLoadLayout.setRefreshing(false);
        binding.list.swipeToLoadLayout.setLoadingMore(false);
    }
}
