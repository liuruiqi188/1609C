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
 * @fileName RegistModel
 * @package com.example.liuruiqi0226.model
 * @date 2019/2/26 9:08
 **/
public class RegistModel {

    //创建接口
    public interface onRegistLisenter{
        void onRegist(String status);
    }
    //声明
    public onRegistLisenter registLisenter;
    //set接口
    public void setonRegistLisenter(onRegistLisenter registLisenter){
        this.registLisenter=registLisenter;
    }



    private HashMap<String,String> parmars=new HashMap<String,String>();
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
                    //接口
                    if (registLisenter!=null){
                        registLisenter.onRegist(status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };


    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/register";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
        //得到数据
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
