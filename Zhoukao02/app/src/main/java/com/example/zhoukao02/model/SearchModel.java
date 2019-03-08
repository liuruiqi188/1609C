package com.example.zhoukao02.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.zhoukao02.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName SearchModel
 * @package com.example.zhoukao02.model
 * @date 2019/2/22 15:03
 **/
public class SearchModel {

    //创建接口
    public interface OnShowLisenter{
        void onShow(JSONArray result);
    }
    //声明借口
    public OnShowLisenter showLisenter;
    //set接口
    public void OnShowLisenter(OnShowLisenter showLisenter){
        this.showLisenter=showLisenter;
    }


    //handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //原生解析json
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray("result");
                    //调用接口
                    if (showLisenter!=null){

                        showLisenter.onShow(result);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void relcted(String goods) {
        String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&keyword="+goods+"&count=5";
        OkHttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                Log.i("xxx",json);
               //新建消息
                Message message = new Message();
                message.obj=json;
                message.what=0;
                //发送消息
                handler.sendMessage(message);

            }
        });

    }
}
