package com.example.myapplication.http;

import android.os.AsyncTask;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/8/2.
 * Map<String,String>类型的数组，长度为1 Map<String,String>［0］是params
 * params: map.put("k1","v1");map.put("k2","v2");...
 */
public class HttpSyncPostUtil extends AsyncTask<Map<String, String>, Integer, String> {
    private Handler mHandler;
    private int mWhat = 0;
    private int type=0;
    public HttpSyncPostUtil(Handler handler, int what){
        this.mHandler = handler;
        this.mWhat = what;
    }

    public HttpSyncPostUtil(Handler handler, int what, int type){
        this.mHandler = handler;
        this.mWhat = what;
        this.type = type;
    }

    //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Map<String, String>... params) {
        String result = null;

        //先将参数放入List，再对参数进行URL编码
        try {
            if(params!=null && params.length>1){
                // 传递的数据
                StringBuffer data = new StringBuffer();
                if(type==-1) {

                }else{
                    params[1].put("platform","ANDROID");
                }

                Set<Map.Entry<String, String>> paramsEntry = params[1].entrySet();
                for(Map.Entry paramEntry : paramsEntry){
                    data.append(paramEntry.getKey());
                    data.append("=");
                    data.append(URLEncoder.encode(paramEntry.getValue().toString(), "UTF-8"));
                    data.append("&");
                }
                data = data.deleteCharAt(data.length()-1);

                // 根据地址创建URL对象
                URL url = new URL(params[0].get("url"));

                //Log.i("UIYU",url.toString()+"==="+data.toString());

                // 根据URL对象打开链接
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                // 设置请求的方式
                urlConnection.setRequestMethod("POST");
                //urlConnection.setInstanceFollowRedirects(false);

                // 设置请求的超时时间
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(5000);


                // 设置sessionId，保持会话的一致
                if (params.length==3){
                    urlConnection.setRequestProperty("Cookie", "JSESSIONID="+params[2].get("sid"));
                }else{
                    //得到的为JSESSIONID=E1D920113F93046227C1F473AB91D2C4; Path=/aks/; HttpOnly
                    /*String headerField = urlConnection.getHeaderField("Set-Cookie");
                    Log.i("AAAA","headerField = "+headerField);
                    if (headerField!=null){
                        String sessionId = headerField.split(";")[0];
                        Log.i("AAAA","sessionId = "+sessionId);
                        if (sessionId!=null){
                            urlConnection.setRequestProperty("Cookie", sessionId);
                        }
                    }*/
                }
                // 设置请求的头
                urlConnection.setRequestProperty("Connection", "keep-alive");
                // 设置请求的头
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 设置请求的头
                urlConnection.setRequestProperty("Content-Length", String.valueOf(data.toString().getBytes().length));
                // 设置请求的头
                urlConnection .setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");

                urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
                urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
                //setDoInput的默认值就是true
                //获取输出流

                //urlConnection.connect();

                OutputStream os = urlConnection.getOutputStream();
                os.write(data.toString().getBytes());
                os.flush();
                if (urlConnection.getResponseCode() == 200) {
                    // 获取响应的输入流对象
                    InputStream is = urlConnection.getInputStream();

                    // 创建字节输出流对象
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    // 定义读取的长度
                    int len = 0;
                    // 定义缓冲区
                    byte buffer[] = new byte[1024];
                    // 按照缓冲区的大小，循环读取
                    while ((len = is.read(buffer)) != -1) {
                        // 根据读取的长度写入到os对象中
                        baos.write(buffer, 0, len);
                    }
                    // 释放资源
                    is.close();
                    baos.close();
                    // 返回字符串
                    result = new String(baos.toByteArray());
                } else {
                    result = null;//"resp code = "+urlConnection.getResponseCode();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    /**
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
     */
    @Override
    protected void onPostExecute(String result) {
        mHandler.sendMessage(mHandler.obtainMessage(mWhat,result));
    }

    /**
     * 这里的Intege参数对应AsyncTask中的第二个参数
     * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
     * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
     */
    @Override
    protected void onProgressUpdate(Integer... values) {

    }


}
