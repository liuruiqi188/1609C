package com.bw.com.erjilist.okhttputils;

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
 * @package com.bw.com.erjilist.okhttputils
 * @date 2019/3/6 11:41
 **/
public class OkHttpUtils {
    //单例化
    public static OkHttpUtils okHttpUtils=null;
    //私有构造
    private OkHttpUtils() {
    }
    //
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
            //拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {

                }
            });
            //设置拦截器模式
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //实例化okclient
            okHttpClient=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }
        return okHttpClient;
    }

    //doget方法
    public void doGet(String url, Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    //doPost方法
    public void doPost(String url, Map<String,String> parmars,Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }



}
