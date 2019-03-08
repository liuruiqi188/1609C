package com.example.zuoye0221.utils;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkHttpUtils
 * @package com.example.zuoye0221.utils
 * @date 2019/2/21 16:15
 **/
public class OkHttpUtils {
    //单例化
    //私有静态
    private static OkHttpUtils okHttpUtils=null;
    //私有构造
    private OkHttpUtils() {
    }
    //公共的静态
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            //同步锁
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }
    //声明不实例化
    private static OkHttpClient okHttpClient=null;
    public synchronized static OkHttpClient getOkHttpClient(){
if (okHttpClient==null){
    //拦截器
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.i("xxx",message);
        }
    });
    //设置模式
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    //创建ok
     okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
}
        return okHttpClient;
    }

    //创建get请求
    public static void doGet(String url, Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        //request请求
        Request request=new Request.Builder().url(url).build();
        //call
        Call call=okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }
}
