package com.bw.com.gouwuche.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.com.gouwuche.okhttputils.OkHttpUtils;

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
 * @package com.bw.com.gouwuche.model
 * @date 2019/3/4 19:26
 **/
public class LoginModel {
    //接口
    public interface OnLoginLisenter{
        void onLogin(String status);
    }
    public OnLoginLisenter loginLisenter;
    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
    }

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


    private HashMap<String,String> parmars=new HashMap<String,String>();
    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/login";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
        //得到参数
        OkHttpUtils.getInstance().doPost(url, parmars, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //打印一下
                Log.i("zzz",json);
                //新建消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送消息
                handler.sendMessage(message);


            }
        });

    }
}
