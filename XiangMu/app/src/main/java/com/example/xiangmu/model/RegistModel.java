package com.example.xiangmu.model;

import android.os.Handler;
import android.os.Message;

import com.example.xiangmu.okhttputils.OkHttpUtils;

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
 * @package com.example.xiangmu.model
 * @date 2019/3/4 15:35
 **/
public class RegistModel {

    //接口
    public interface OnRegistLisenter{
        void onregist(String status);
    }
    private OnRegistLisenter registLisenter;
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
                //原生
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String status = jsonObject.getString("status");
                    //接口
                    if (registLisenter!=null){
                        registLisenter.onregist(status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private HashMap<String,String> parmars=new HashMap<String,String>();
    public void send(String phone, String pwd) {
        String url="http://172.17.8.100/small/user/v1/register";
        parmars.put("phone",phone);
        parmars.put("pwd",pwd);
        OkHttpUtils.getInstance().doPost(url, parmars, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送数据
                handler.sendMessage(message);
            }
        });

    }
}
