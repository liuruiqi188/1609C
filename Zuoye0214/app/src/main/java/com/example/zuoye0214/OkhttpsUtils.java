package com.example.zuoye0214;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author liuruiqi
 * @fileName OkhttpsUtils
 * @package com.example.zuoye0214
 * @date 2019/2/16 8:37
 **/
public class OkhttpsUtils {
    private static OkhttpsUtils okhttpsUtils=null;

    private OkhttpsUtils(){

    }
    public static OkhttpsUtils getInstance(){
        if (okhttpsUtils==null){
            //同步锁
            synchronized (OkhttpsUtils.class){
                if (okhttpsUtils==null){
                    okhttpsUtils=new OkhttpsUtils();
                }
            }
        }
        return okhttpsUtils;
    }

    //执行get请求
    public static void doGet(String url, Callback callback){
        //创建OkhttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Request request=new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }


}
