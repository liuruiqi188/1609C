package com.example.week02test.OkHttpUtils;

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
 * @package com.example.week02test.OkHttpUtils
 * @date 2019/2/23 10:07
 **/
public class OkHttpUtils  {
    //单例化
    //私有静态
    private  static OkHttpUtils okHttpUtils=null;
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


    //私有静态
    private static OkHttpClient okHttpClient=null;

    public synchronized static OkHttpClient getOkHttpClient(){
       if (okHttpClient==null){
           //实例化拦截器
           HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(String message) {
                   Log.i("xxx", message);
               }
           });
           //设置拦截器模式
           httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           //实例化okcliet
           okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

       }
       return okHttpClient;
    }

    //get
    public static void doGet(String url, Callback callback){
        //得到okclient
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request=new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }

    //post
    public static void doPost(String url, Callback callback, Map<String,String> parmars){
     //得到okclient
        OkHttpClient okHttpClient = getOkHttpClient();
        //body
        FormBody.Builder builder = new FormBody.Builder();
        //便利
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        //构建FORMbody
        FormBody formBody = builder.build();
        //request
        Request request = new Request.Builder().url(url).post(formBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);

    }




}
