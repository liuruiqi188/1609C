package com.example.liuruiqi0218.model;

import android.os.Handler;
import android.os.Message;

import com.example.liuruiqi0218.utils.OkhttpUtils;

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
 * @package com.example.liuruiqi0218.model
 * @date 2019/2/18 8:52
 **/
public class ShowModel {


    private String url="http://365jia.cn/news/api3/365jia/news/headline?page=1";

    //自定义接口
    public interface OnShowLisenter{
        void onResult(JSONArray data1);
    }
    //监听接口
    public OnShowLisenter showLisenter;

    //set方法接口
    public void setOnShowLisenter(OnShowLisenter showLisenter){
        this.showLisenter=showLisenter;
    }



    //新建handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //原生解析
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray data1 = data.getJSONArray("data");
                    //调用接口
                    showLisenter.onResult(data1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    //解析数据
    public void lianxi() {
        //调用发放
        OkhttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //

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
