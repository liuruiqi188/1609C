package com.example.zhoukao02.okhttputils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author liuruiqi
 * @fileName OkHttpUtils
 * @package com.example.zhoukao02.okhttputils
 * @date 2019/2/22 15:04
 **/
public class OkHttpUtils {
    //单例化
    //私有静态声明
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

    //私有声明静态不实例化
    private static OkHttpClient okHttpClient=null;
    //公共同步静态okhttpclient
    public synchronized static OkHttpClient getOkHttpClient(){
       if (okHttpClient==null){
           //拦截器
           HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(String message) {

               }
           });
           //设置模式
           httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           //实例化okhttpclient
           okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
       }
       return okHttpClient;
    }
    //创建get请求  公共的静态的void
    public static void doGet(String url, Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        //request请求
        Request build = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(build);
        //异步
        call.enqueue(callback);
    }



}
