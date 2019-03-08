package com.example.xiangmu.model;

import android.os.Handler;
import android.os.Message;

import com.example.xiangmu.okhttputils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName LoginModel
 * @package com.example.xiangmu.model
 * @date 2019/3/4 15:02
 **/
public class LoginModel {

    //接口
    public interface OnLoginLisenter{
        void onLogin(String status);
    }
    private OnLoginLisenter loginLisenter;

    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
    }


    private HashMap<String,String> parmars=new HashMap<>();
    //handler
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

    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/login";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
        OkHttpUtils.getInstance().doPost(url, parmars, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析数据
                String json = response.body().string();
                //新建消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送数据
                handler.sendMessage(message);

            }
        });

    }
}
