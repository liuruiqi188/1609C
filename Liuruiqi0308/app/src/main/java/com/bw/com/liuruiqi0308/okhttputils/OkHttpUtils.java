package com.bw.com.liuruiqi0308.okhttputils;

import android.util.Log;

import java.util.Map;
import java.util.PropertyResourceBundle;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkHttpUtils
 * @package com.bw.com.liuruiqi0308.okhttputils
 * @date 2019/3/8 14:02
 **/
public class OkHttpUtils {
    //单例
    public static OkHttpUtils okHttpUtils=null;

    //私有的构造

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

    //声明
    private static OkHttpClient okHttpClient=null;

    public synchronized static OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            //实例化拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("aaa", message);
                }
            });
            //设置模式
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //实例化okhttpclient
            okHttpClient=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }
        return okHttpClient;
    }


    //doGet
    public void doGet(String url, Callback callback){
        //得到ok
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }

    //dopost
    public void doPost(String url, Map<String,String> parmars,Callback callback){
        //得到ok
        OkHttpClient okHttpClient = getOkHttpClient();
        //formbody
        FormBody.Builder builder = new FormBody.Builder();
        //for循环
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        FormBody formBody = builder.build();
        //request
        Request request = new Request.Builder().url(url).post(formBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }


}
