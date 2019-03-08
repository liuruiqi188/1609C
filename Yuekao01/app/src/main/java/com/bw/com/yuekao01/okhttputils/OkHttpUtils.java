package com.bw.com.yuekao01.okhttputils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkHttpUtils
 * @package com.bw.com.yuekao01.okhttputils
 * @date 2019/3/7 11:37
 **/
public class OkHttpUtils {
    //单例
    public static OkHttpUtils okHttpUtils=null;

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
           //新建拦截器
           HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(String message) {

               }
           });
           //设置模式
           loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           okHttpClient=new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(new Interceptor() {
               @Override
               public Response intercept(Chain chain) throws IOException {
                   Request request=chain.request().newBuilder().addHeader("source","android").build();
                   return chain.proceed(request);
               }
           }).build();
       }
       return okHttpClient;
    }

    public void doGet(String url, Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public void doPost(String url, Map<String,String> parmars,Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        FormBody build = builder.build();
        Request request = new Request.Builder().url(url).post(build).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }





}
