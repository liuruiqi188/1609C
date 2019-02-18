package com.example.day0216.model;

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
 * @package com.example.day0216.model
 * @date 2019/2/16 11:07
 **/
public class RegistModel {


    //新建街口
    public interface OnRegistLisenter{
        void onResult(String status,String message);
    }
    //接口
    public OnRegistLisenter registLisenter;

    //set接口
    public void setOnRegistLisenter(OnRegistLisenter registLisenter){
        this.registLisenter=registLisenter;
    }


    //handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //原生解析
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");
                    //接口判断
                    if (registLisenter!=null){
                        registLisenter.onResult(status,message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };




    //post解析okhttp
    public void send(String phone, String pwd) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //封住body
        RequestBody requestBody = new FormBody.Builder().add("phone", phone).add("pwd", pwd).build();
        //request请求
        Request request= new Request.Builder().url("http://172.17.8.100/small/user/v1/register").post(requestBody).build();
        //call
        Call call = okHttpClient.newCall(request);
        //异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //新建消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });


    }
}
