package com.example.week02test.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.week02test.OkHttpUtils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName LoginModel
 * @package com.example.week02test.model
 * @date 2019/2/23 10:05
 **/
public class LoginModel {
       Map<String,String> parmars=new HashMap<String,String>();

    //创建接口
    public interface OnLoginLisenter{
        void onLogin(String status);
    }
    //声明接口
    public OnLoginLisenter loginLisenter;

    //set接口
    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
    }



    //私有handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){

                String json= (String) msg.obj;

                //解析数据
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    //接口
                    if (loginLisenter!=null){
                        loginLisenter.onLogin(status);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    };


    //解析数据
    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/login";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
       OkHttpUtils.getInstance().doPost(url, new Callback() {
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
               //发送消息
               handler.sendMessage(message);
           }
       },parmars);
    }
}
