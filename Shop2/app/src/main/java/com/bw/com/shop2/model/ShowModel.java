package com.bw.com.shop2.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.com.shop2.adapter.MyAdapter;
import com.bw.com.shop2.bean.Datazhong;
import com.bw.com.shop2.bean.Json;
import com.bw.com.shop2.okhttputils.OkHttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShowModel
 * @package com.bw.com.shop2.model
 * @date 2019/3/6 15:47
 **/
public class ShowModel {
public interface OnShowLisenter{
    void onshow(ArrayList<Datazhong> data);
}
private OnShowLisenter showLisenter;

public void OnShowLisenter(OnShowLisenter showLisenter){
    this.showLisenter=showLisenter;
}

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                Gson gson = new Gson();
                Json json1 = gson.fromJson(json, Json.class);
                ArrayList<Datazhong> data = json1.getData();
                Log.i("zzz",data.toString());
               //接口
                if (showLisenter!=null){
                    showLisenter.onshow(data);
                }


            }
        }
    };


    public void relected() {
        String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
        OkHttpUtils.getInstance().doGet(url, new Callback() {
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
        });
    }
}
