package com.example.liuruiqi0226.OkHttpUtils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkHttpUtils
 * @package com.example.liuruiqi0226.OkHttpUtils
 * @date 2019/2/26 9:09
 **/
public class OkHttpUtils {
    //单例化
    private static OkHttpUtils okHttpUtils=null;

    private OkHttpUtils() {
    }

    public synchronized static OkHttpUtils getInstance(){
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
    //getOkhttp
    public static OkHttpClient getOkHttpClient(){
        //创建拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx", message);
            }
        });
        //设置拦截器
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建okhttp
        okHttpClient=new OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request =chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return okHttpClient;
    }
//get请求方法
    public static void doGet(String url, Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }
    public static void doPost(String url, Callback callback, Map<String,String> parmars){
        OkHttpClient okHttpClient = getOkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        //便利
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        //body
        FormBody body = builder.build();
        //request
        Request request = new Request.Builder().url(url).post(body).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);

    }

}
