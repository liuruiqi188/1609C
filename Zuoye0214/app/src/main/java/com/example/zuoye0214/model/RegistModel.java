package com.example.zuoye0214.model;

import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName RegistModel
 * @package com.example.zuoye0214.model
 * @date 2019/2/15 16:32
 **/



public class RegistModel {

    //定义接口
    public interface onRegistLisenter{
        void onResult(String status,String message);
    }
    //声明接口
    public onRegistLisenter registLisenter;

    //set
    public void setOnRegistLisenter(onRegistLisenter registLisenter){
        this.registLisenter=registLisenter;
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
                    String message = jsonObject.getString("message");
                    String status = jsonObject.getString("status");
                    //判断
                    if (registLisenter!=null){
                        registLisenter.onResult(status,message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };


    public void regist(String phone, String pwd) {

        //创建okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //创建body
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", phone)
                .add("pwd", pwd)
                .build();
        //创建post请求
        Request request=new Request
                .Builder()
                .url("http://172.17.8.100/small/user/v1/register")
                .post(requestBody)
                .build();
        //call
        Call call = okHttpClient.newCall(request);

        //子线程
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });



    }
}
