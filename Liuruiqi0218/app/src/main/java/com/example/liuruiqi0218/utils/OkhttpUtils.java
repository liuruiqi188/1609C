package com.example.liuruiqi0218.utils;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
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
public static void doGet(String url, Callback callback){
        //拦截器
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.i("xxx",message);
        }
    });
    //设置模式
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    //创建
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    //request请求
    Request request=new Request.Builder().url(url).build();
    //call
    Call call = okHttpClient.newCall(request);
    //异步
    call.enqueue(callback);
}

}
