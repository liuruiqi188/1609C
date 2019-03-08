package com.bw.com.liuruiqi0308.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.com.liuruiqi0308.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShopcarModel
 * @package com.bw.com.liuruiqi0308.model
 * @date 2019/3/8 14:13
 **/
public class ShopcarModel {


    //接口
    public interface onFirstLisenter{
        void onfirst(JSONArray data);
    }

    private onFirstLisenter firstLisenter;

    public void setonFirstLisenter(onFirstLisenter firstLisenter){
        this.firstLisenter=firstLisenter;
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
                    JSONArray data = jsonObject.getJSONArray("data");
                    //接口
                    if (firstLisenter!=null){
                        firstLisenter.onfirst(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    };




    public void relected() {
        String url="http://172.17.8.100/ks/product/getCarts?uid=51";
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("ppp",json);
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
