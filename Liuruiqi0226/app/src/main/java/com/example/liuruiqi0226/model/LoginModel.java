package com.example.liuruiqi0226.model;

import android.os.Handler;
import android.os.Message;

import com.example.liuruiqi0226.OkHttpUtils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName LoginModel
 * @package com.example.liuruiqi0226.model
 * @date 2019/2/26 9:46
 **/
public class LoginModel {

    private HashMap<String,String> parmars=new HashMap<String,String>();

    //创建接口
    public interface onLoginLisenter{
        void login(String status);
    }
    //声明
    public onLoginLisenter loginLisenter;
    //set接口
    public void setonLoginLisenter(onLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
    }

    //handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //y原生解析
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    //接口
                    if (loginLisenter!=null){
                        loginLisenter.login(status);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/login";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
        //post请求
        OkHttpUtils.getInstance().doPost(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                //消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送

                handler.sendMessage(message);
            }
        },parmars);
    }
}
