package com.example.xiangmu.okhttputils;

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
 * @fileName OkHttpUtils
 * @package com.example.xiangmu.okhttputils
 * @date 2019/2/27 14:04
 **/
public class OkHttpUtils {
    //单例
    private static OkHttpUtils okHttpUtils=null;

    private OkHttpUtils() {
    }

    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //私有静态
    private static OkHttpClient okHttpClient=null;

    //
    public synchronized static OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            //实例拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("zzz", message);
                }
            });
            //设置拦截器
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //实例化okhttp
            okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        }
        return okHttpClient;
    }

    //GET方法
    public static void doGet(String url, Callback callback){
         //得到okhttp
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }

    //POST方法
    public static void doPost(String url, Map<String,String> parmars,Callback callback){
        //得到okhttp
        OkHttpClient okHttpClient = getOkHttpClient();
        //body
        FormBody.Builder builder = new FormBody.Builder();
        //循环遍历
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        //build
        FormBody formBody = builder.build();
        //request
        Request request = new Request.Builder().url(url).post(formBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }




}
