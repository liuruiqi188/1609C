package com.example.xiangmu.model;

import android.os.Handler;
import android.os.Message;

import com.example.xiangmu.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShopCarModel
 * @package com.example.xiangmu.model
 * @date 2019/3/5 13:59
 **/
public class ShopCarModel {

    //接口
    public interface OnShopCarLisenter{
        void shopcar(JSONArray result);
    }
    private OnShopCarLisenter shopCarLisenter;

    public void setOnShopCarLisenter(OnShopCarLisenter shopCarLisenter){
        this.shopCarLisenter=shopCarLisenter;
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
                   if (shopCarLisenter!=null){
                       shopCarLisenter.shopcar(result);
                   }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    };


    String url="http://172.17.8.100/small/commodity/v1/findCommodityListByLabel?count=5&page=1&labelId=1003";
    public void relected() {
        OkHttpUtils.getInstance().doGet(url, new Callback() {
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
                //发送
                handler.sendMessage(message);

            }
        });

    }
}
