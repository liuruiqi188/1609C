package com.example.test03.okhttputils;

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
 * @package com.example.test03.okhttputils
 * @date 2019/2/28 9:08
 **/
public class OkHttpUtils {
    //单例
    private static OkHttpUtils okHttpUtils=null;
    //私有构造
    private OkHttpUtils() {
    }
    //公共静态
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

    //私有
    private static OkHttpClient okHttpClient=null;

    //公共同步静态
    public synchronized static OkHttpClient getOkHttpClient(){
       if (okHttpClient==null){
           //X新建拦截器
           HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(String message) {
                   Log.i("zzz", message);
               }
           });
           //设置拦截器模式
           loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
           //新建okclient
           okHttpClient=new OkHttpClient();
       }
        return okHttpClient;
    }

    public static void doGet(String url, Callback callback){
        //得到
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }


    public static void doPost(String url, Map<String,String> parmars,Callback callback){
        //得到
        OkHttpClient okHttpClient = getOkHttpClient();
        //formbody
        FormBody.Builder builder = new FormBody.Builder();
        //for循环
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        //build
        FormBody formBody = builder.build();
        //repost
        Request request = new Request.Builder().url(url).post(formBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }



}
