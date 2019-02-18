package com.example.day0216;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkhttpUtils
 * @package com.example.day0216
 * @date 2019/2/17 19:00
 **/
public class OkhttpUtils {

    //私有的静态
    private static OkhttpUtils okhttpUtils=null;

    //有残构造
    private OkhttpUtils() {
    }

    //公共的静态方法
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

    //执行get请求  公共的静态方法+void
    public static void doGet(String url, Callback callback){

        //创建拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);

            }
        });
        //指定日志拦截模式
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        //新建Okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //新建request
        Request request = new Request.Builder().url(url).build();
        //ok.call
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }


}
