package com.bawei.guojinyi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.bawei.guojinyi.App;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 8:50
 * @Description: 用途：网络工具类
 */
public class NetUtil {
    private static final String TAG = "NetUtil";
    //单例模式
    private static NetUtil instance;
    private NetUtil(){}
    public static NetUtil getInstance(){
        if (instance==null) {
            synchronized (NetUtil.class){
                if (instance==null) {
                    instance = new NetUtil();
                }
            }
        }

        return instance;
    }
    //get请求
    @SuppressLint("StaticFieldLeak")
    public void dogetData(String path, final DataCallBack callBack){

        //使用Asyntask
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                String str = strings[0];
                //创建URL实例
                try {
                    URL url = new URL(str);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    //获取状态吗
                    int responseCode = httpURLConnection.getResponseCode();
                    if(responseCode==200){
                        //读取流
                        InputStream inputStream = httpURLConnection.getInputStream();
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        StringBuffer buffer = new StringBuffer();
                        while ((len= inputStream.read(bytes))!=-1){
                            buffer.append(new String(bytes,0,len));
                        }
                        //关流
                        inputStream.close();
                        //打印
                        String s = buffer.toString();
                        Log.i(TAG, "doInBackground: "+s);
                        return s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //判空
                boolean empty = TextUtils.isEmpty(s);
                if(empty){
                    callBack.failed("请求失败");
                }else{
                    callBack.success(s);
                }
            }
        }.execute(path);
    }

    //判断网络状态
    public boolean doGetIntener(){
        ConnectivityManager connectivityManager = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //判断网络
        if (activeNetworkInfo != null) {
            boolean available = activeNetworkInfo.isAvailable();
            return available;
        }
        return false;
    }

    //接口回调
    public interface DataCallBack{
        void success(String json);
        void failed(String failed);
    }
}
