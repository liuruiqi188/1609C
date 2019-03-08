package com.bw.com.week03test.okhttputils;

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
 * @package com.bw.com.week03test.okhttputils
 * @date 2019/3/1 15:59
 **/
public class OkHttpUtils {
    //单例化
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

    private static OkHttpClient okHttpClient=null;

    public synchronized static OkHttpClient getOkHttpClient(){
       if (okHttpClient==null){
           //加入拦截器
           HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
               @Override
               public void log(String message) {

               }
           });
           //设置拦截器
           httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

           //新建okclient
           okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
       }
       return okHttpClient;
    }

    //doGet方法
    public static void doGet(String url, Callback callback){
        //得到okclient
        OkHttpClient okHttpClient = getOkHttpClient();
        //request
        Request request = new Request.Builder().url(url).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }

    //doPost方法
    public static void doPost(String url, Map<String,String> parmars , Callback callback){
        OkHttpClient okHttpClient = getOkHttpClient();
        //forbody
        FormBody.Builder builder = new FormBody.Builder();
        //for
        for (String key:parmars.keySet()){
            builder.add(key,parmars.get(key));
        }
        //得到formbody
        FormBody formBody = builder.build();
        //request
        Request request = new Request.Builder().url(url).post(formBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(callback);
    }





}
