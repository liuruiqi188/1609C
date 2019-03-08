package com.example.week02test.model;

import android.os.Handler;
import android.os.Message;

import com.example.week02test.OkHttpUtils.OkHttpUtils;

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
 * @fileName RegistModel
 * @package com.example.week02test.model
 * @date 2019/2/23 11:35
 **/
public class RegistModel {

    private Map<String,String> parmars=new HashMap<>();

    //创建接口
    public interface onRegistLisenter{
        void onRegist(String status);
    }
    //声明
    public onRegistLisenter registLisenter;

    //set接口
    public void onRegistLisenter (onRegistLisenter registLisenter){
        this.registLisenter=registLisenter;
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
        //得到网络数据
        OkHttpUtils.getInstance().doPost(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //新建信息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送消息
                handler.sendMessage(message);
            }
        },parmars);
    }
}
