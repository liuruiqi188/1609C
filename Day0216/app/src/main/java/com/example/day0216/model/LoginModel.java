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
 * @fileName LoginModel
 * @package com.example.day0216.model
 * @date 2019/2/16 10:37
 **/
public class LoginModel {

    //创建接口
    public interface OnLoginLisenter{
        void onResult(String status);
    }
    //接口
    public OnLoginLisenter loginLisenter;

    //set接口
    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
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
                    //判断接口
                    if (loginLisenter!=null){
                        loginLisenter.onResult(status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    //post联网解析
    public void send(String phone, String pwd) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //新建body,并封装数据
        RequestBody requestBody = new FormBody.Builder().add("phone", phone).add("pwd", pwd).build();
        //建立request请求
        Request request = new Request.Builder().url("http://172.17.8.100/small/user/v1/login").post(requestBody).build();
        //建立call
        Call call = okHttpClient.newCall(request);

        //执行异步
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
