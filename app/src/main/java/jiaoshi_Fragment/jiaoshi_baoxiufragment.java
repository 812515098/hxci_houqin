package jiaoshi_Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.myapplication.Adapter.ImagePickerAdapter;
import com.example.myapplication.Adapter.SelfDialog;
import com.example.myapplication.GlideImageLoader;
import com.example.myapplication.R;
import com.example.myapplication.SelectDialog;
import com.example.myapplication.http.HttpCallBack;
import com.example.myapplication.http.HttpError;
import com.example.myapplication.http.HttpSyncPostUtil;
import com.example.myapplication.http.HttpURL;
import com.isseiaoki.simplecropview.FreeCropImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.e;

public class jiaoshi_baoxiufragment extends jiaoshi_BaseFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener ,View.OnClickListener {
    //维修校区
    private String xiaoqu[] ;
    //维修地点
   private Map<String,String[]>didian= new HashMap<String, String[]>();;
    //维修分类
    private String fenlei[] ;
    //维修分类
    private Map<String,String[]> neirong= new HashMap<String, String[]>();;
    //维修等级
    private String dengji[] = new String[]{"普通","加急"};
    //获取的Json
    private JSONObject mJsonObj1;
    private EditText Adress;//报修内容
    private EditText Info;//详细说明
    private EditText TEL;//报修人手机
    private String sno;//报修人ID
    private JSONObject mJsonObj2;
    private SharedPreferences sp;

    private View mContentView;
    private SelfDialog selfDialog;
    private  String baoxiufenlei_shuju[]= new String[]{"江西","湖南"};
    //下拉菜单
    private Spinner baoxiufenlei, baoxiuneirong, weixiudengji,baoxiuxiaoqu, baoxiudidian;

    ArrayAdapter<String> baoxiuleibieAdapter = null;
    ArrayAdapter<String> baoxiuwupinAdapter = null;
    ArrayAdapter<String> baoxiuxiaoquAdapter = null;
    ArrayAdapter<String> baoxiudidianAdapter = null;

    private ProgressDialog progressDialog;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private Button tijiao;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 4;               //允许选择图片最大数
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.jiaoshi_baoxiufragment,container,false);
        baoxiufenlei =mContentView.findViewById(R.id.jiaoshi_bx_fenlei);
        baoxiuneirong =mContentView.findViewById(R.id.jiaoshi_bx_content);
        weixiudengji =mContentView.findViewById(R.id.jiaoshi_bx_dengji);
        baoxiuxiaoqu =mContentView.findViewById(R.id.jiaoshi_xiaoqu);
        baoxiudidian =mContentView.findViewById(R.id.jiaoshi_jiaoxuelou);
        Adress =mContentView.findViewById(R.id.jiaoshi_baoxiudizhi);
        Info=mContentView.findViewById(R.id.jiaoshi_bx_xiangqing);
        TEL =mContentView.findViewById(R.id.jiaoshi_lianxifangshi);
        sno = sp.getString("USERID", "");

        //图片拍照和相册

        initImagePicker();
        getleibie();
        getdidian();
        initWidget();
        tijiao.setOnClickListener(this);
        return mContentView;
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);                      //多选
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
    private void initWidget() {
        tijiao=mContentView.findViewById(R.id.jiaoshi_baoxiu_tijiao);
        RecyclerView recyclerView = mContentView.findViewById(R.id.jiaoshi_recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getContext(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 圆圈加载进度的 dialog
     */
    private void showWaiting() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("提交中...");
        progressDialog.setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        progressDialog.setCancelable(false);//点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
        progressDialog.show();

    }
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style.transparentFrameWindowStyle, listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                ImagePicker.getInstance().setMultiMode(false);
                                ImagePicker.getInstance().setFreeCrop(true, FreeCropImageView.CropMode.FREE);
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                ImagePicker.getInstance().setMultiMode(true);
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }
                }, names);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getContext(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS,true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null){
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null){
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.jiaoshi_baoxiu_tijiao:
                selfDialog = new SelfDialog(getContext(),R.style.dialog);
                selfDialog.setMessage("你确定提交报修吗?");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(getContext(),"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        showWaiting();
                        //生成工单号
                        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String strDate = sfDate.format(new Date());
                        Insertbaoxiu(baoxiuxiaoqu.getSelectedItem().toString(), baoxiudidian.getSelectedItem().toString(), baoxiufenlei.getSelectedItem().toString(), TEL.getText().toString(), Adress.getText().toString(), baoxiuneirong.getSelectedItem().toString(), weixiudengji.getSelectedItem().toString(), Info.getText().toString(), sno, selImageList,strDate, new HttpCallBack() {
                            @Override
                            public void onSuccess() {
                                selImageList.clear();
                                adapter.setImages(selImageList);
                        }

                            @Override
                            public void onFaild(HttpError httpError) {

                            }
                        });

                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(getContext(),"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
                selfDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                break;
        }

    }

    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
    /**
     * 获取图片的旋转角度
     *
     * @param filePath
     * @return
     */
    public static int getRotateAngle(String filePath) {
        int rotate_angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate_angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate_angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate_angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate_angle;
    }
    /**
     * 旋转图片角度
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap setRotateAngle(int angle, Bitmap bitmap) {

        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;

    }

    /**
     * 图片压缩-质量压缩
     *
     * @param filePath 源图片路径
     * @return 压缩后的路径
     */

    public static String compressImage(String filePath) {

        //原文件
        File oldFile = new File(filePath);
        //压缩文件路径 照片路径/
        String targetPath = oldFile.getPath();
        int quality = 50;//压缩比例0-100
        Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
        int degree = getRotateAngle(filePath);//获取相片拍摄角度

        if (degree != 0) {//旋转照片角度，防止头像横着显示
            bm = setRotateAngle(degree, bm);
        }
        File outputFile = new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return filePath;
        }
        return outputFile.getPath();
    }


//获取类别报修的Json
    public void getleibie() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey", getContext().getPackageName());
        mapParams.put("secret", "IOTCARE");
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.BAOXIULEIXING);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 1);
        httpLoginPost.execute(mapUrl, mapParams);
    }

    //获取类报修地点的Json
    public void getdidian() {
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("appkey", getContext().getPackageName());
        mapParams.put("secret", "IOTCARE");
        Map<String, String> mapUrl = new HashMap<String, String>();
        mapUrl.put("url", HttpURL.PLACESELECT);
        HttpSyncPostUtil httpLoginPost = new HttpSyncPostUtil(mHandler, 0);
        httpLoginPost.execute(mapUrl, mapParams);
    }

//参数名	必选	类型	说明
//repairSchool	是	string	报修校区
//repairAdress 	是	string	报修地点
//subTel 	是	string	提交人电话
//detailAdress 	是	string	详细地点
//repairType	是	string	报修种类
//repairContent 	是	string	报修内容
//repairLevel	是	string	维修等级
//repairInfo	是	string	详细说明
//userId	是	int	用户id
//repairPhoto	是	string	报修图片1
//repairPhoto2	是	string	报修图片2
//repairPhoto3	是	string	报修图片3
//repairPhoto4	是	string	报修图片4
//repairNo	是	int	工单号
    public void Insertbaoxiu(String repairSchool, String reapirAdress,String repairType, String subTel, String detailAdress, String repairContent, String repairLevel, String repairInfo, String userId,ArrayList<ImageItem> list, String repairNo, final HttpCallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < list.size(); i++) {
            File f = new File(compressImage(list.get(i).path));
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), f);
            requestBody.addFormDataPart("repairPhoto", f.getName(), body);
        }
        requestBody.addFormDataPart("repairSchool", repairSchool);
        requestBody.addFormDataPart("repairAdress",  reapirAdress);
        requestBody.addFormDataPart("repairType", subTel);
        requestBody.addFormDataPart("detailAdress",detailAdress);
        requestBody.addFormDataPart("repairType", repairType);
        requestBody.addFormDataPart("repairLevel ", repairLevel );
        requestBody.addFormDataPart("repairInfo", repairInfo);
        requestBody.addFormDataPart("userId", userId);
        requestBody.addFormDataPart("repairNo", repairNo);
        Request request = new Request.Builder()
                .url(HttpURL.BAOXIU_INSERT)
                .post(requestBody.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String ig = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ig .equals("ok")) {
                            if (callBack != null){
                                callBack.onSuccess();
                            }
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "报修成功", Toast.LENGTH_LONG).show();
                        } else {
                            if (callBack != null) {
                                callBack.onFaild(new HttpError(-1, "报修失败"));
                            }
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "报修失败", Toast.LENGTH_LONG).show();

                        }
                        ;
                    }
                });


            }
        });

    }
    //地点Json解析
    private void initdidianDatas() {
        try {
            JSONArray jsonArray = mJsonObj1.getJSONArray("list");
            xiaoqu = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);
                String province = jsonP.getString("value");
                xiaoqu[i] = province;
                JSONArray jsonCs = null;
                try {
                    jsonCs = jsonP.getJSONArray("childNode");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("value");
                    mCitiesDatas[j] = city;
                    JSONArray jsonAreas = null;
                    try {
                        jsonAreas = jsonCity.getJSONArray("childNode");
                    } catch (Exception e) {
                        continue;
                    }
                }
                didian.put(province, mCitiesDatas);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mJsonObj1 = null;
    }
//类别Json解析
    private void initleibieDatas() {
        try {
            JSONArray jsonArray = mJsonObj2.getJSONArray("list");
          fenlei = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);
                String province = jsonP.getString("value");
               fenlei[i] = province;
                JSONArray jsonCs = null;
                try {
                    jsonCs = jsonP.getJSONArray("childNode");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("value");
                    mCitiesDatas[j] = city;
                    JSONArray jsonAreas = null;
                    try {
                        jsonAreas = jsonCity.getJSONArray("childNode");
                    } catch (Exception e) {
                        continue;
                    }
                }
              neirong.put(province, mCitiesDatas);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mJsonObj2 = null;
    }

    //类别选择spinner 二级联动
    private void setleibieSpinner() {
        baoxiuleibieAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fenlei);
        baoxiufenlei .setAdapter(baoxiuleibieAdapter);
        baoxiufenlei.setSelection(0, true);//设置默认选中项
        baoxiuwupinAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,neirong.get(fenlei[0]));
        baoxiuneirong.setAdapter(baoxiuwupinAdapter);
        baoxiuneirong.setSelection(0, true);  //默认选中第0个
        baoxiufenlei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baoxiuwupinAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, neirong.get(fenlei[position]));
                baoxiuneirong.setAdapter(baoxiuwupinAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //地点选择spinner 二级联动
    private void setdiidanSpinner() {
        baoxiuxiaoquAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, xiaoqu);
        baoxiuxiaoqu .setAdapter(baoxiuxiaoquAdapter);
        baoxiuxiaoqu.setSelection(0, true);//设置默认选中项
        baoxiudidianAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,didian.get(xiaoqu[0]));
        baoxiudidian.setAdapter(baoxiudidianAdapter);
        baoxiudidian.setSelection(0, true);  //默认选中第0个
        baoxiuxiaoqu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baoxiudidianAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, didian.get(xiaoqu[position]));
                baoxiudidian.setAdapter(baoxiudidianAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg){
            if (msg.what ==0) {

                Object object = msg.obj;
                if (object != null) {
                    try {
                        mJsonObj1 = new JSONObject(object.toString());

                       initdidianDatas();
                        setdiidanSpinner() ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "服务器错误", Toast.LENGTH_LONG).show();
                }

            }else
                if (msg.what == 1) {
                Object object = msg.obj;
                if (object != null) {
                    try {
                        mJsonObj2 = new JSONObject(object.toString());
                        initleibieDatas();
                        setleibieSpinner();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "服务器错误", Toast.LENGTH_LONG).show();
                }

            }

        }



    };

}
