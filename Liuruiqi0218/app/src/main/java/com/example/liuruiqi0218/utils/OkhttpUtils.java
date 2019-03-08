package com.example.liuruiqi0218.utils;

import android.util.Log;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkhttpUtils
 * @package com.example.liuruiqi0218.utils
 * @date 2019/2/18 8:54
 **/
//单例化
public class OkhttpUtils {
    //私有静态
    private static OkhttpUtils okhttpUtils=null;


    //私有构造
    private OkhttpUtils() {
    }
    //公共的静态
    public static OkhttpUtils getInstance(){
        if (okhttpUtils==null){
            //同步锁
            synchronized (OkhttpUtils.class){
                if (okhttpUtils==null){
                    okhttpUtils=new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;
    }

    //声明不实例
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

            //创建okhttpclient
            okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor).build();

        }
        return okHttpClient;
    }


public static void doGet(String url, Callback callback){

    //创建
    OkHttpClient okHttpClient = getOkHttpClient();
    //request请求
    Request request=new Request.Builder().url(url).build();
    //call
    Call call = OkhttpUtils.okHttpClient.newCall(request);
    //异步
    call.enqueue(callback);
}
public static void doPost(String url, Map<String,String> parmars,Callback callback){
        //创建OkHttpClient
    OkHttpClient okHttpClient = getOkHttpClient();
    //创建build
    FormBody.Builder builder = new FormBody.Builder();
    //便立集合
    for (String key:parmars.keySet()){
        builder.add(key,parmars.get(key));
    }
    //构建FormBody
    FormBody formBody = builder.build();
    //创建Request
    Request request = new Request.Builder().url(url).post(formBody).build();
    Call call = okHttpClient.newCall(request);
    //执行一步
    call.enqueue(callback);
}
}
