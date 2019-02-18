package com.example.zuoye0214.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName LoginModel
 * @package com.example.zuoye0214.model
 * @date 2019/2/14 19:08
 **/
public class LoginModel {

    //----------------------------------------------------------------------------
    //定义接口
    public interface OnLoginLisnter{
        void onResult(String status);
    }
    //声明接口
    public OnLoginLisnter loginLisnter;

    //提供一个公共的设置监听的方法
    public void setOnLoginLisnter(OnLoginLisnter loginLisnter){
        this.loginLisnter=loginLisnter;
    }

    //新建handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    //判断
                    if (loginLisnter!=null){
                        //回调数据
                        loginLisnter.onResult(status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };


    public void login(String phone, String pwd) {
        //创建网络请求对象
        OkHttpClient okHttpClient=new OkHttpClient
                .Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
        //创建RequestBody并封装请求参数
        RequestBody requestBody=new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();

        //创建post请求
        Request request=new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/login")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            //此方法在子线程
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("xxx",json);
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });

    }
}
