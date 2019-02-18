package com.example.day0216.model;


import android.os.Handler;
import android.os.Message;

import com.example.day0216.OkhttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShowModel
 * @package com.example.day0216.model
 * @date 2019/2/17 18:58
 **/
public class ShowModel {

    //接口
    public interface OnShoeLisnter{
        void onResult(JSONArray result);
    }
    //监听接口
    public OnShoeLisnter shoeLisnter;

    //set
    public  void setOnShoeLisnter(OnShoeLisnter shoeLisnter){
        this.shoeLisnter=shoeLisnter;
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
                    JSONArray result = jsonObject.getJSONArray("result");
                    //接口
                    if (shoeLisnter!=null){
                        shoeLisnter.onResult(result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

private String url="http://172.17.8.100/small/commodity/v1/bannerShow";

    public void getGoodsData() {
        //获取网络数据
        OkhttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                //发送消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });
    }
}
