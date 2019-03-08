package com.example.zuoye0221.model;

import android.os.Handler;
import android.os.Message;

import com.example.zuoye0221.utils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName SerachModel
 * @package com.example.zuoye0221.model
 * @date 2019/2/21 16:13
 **/
public class SerachModel {

    //创建接口
    public interface OnShowLisenter{
        void show(JSONArray result);
    }
    //声明
    public OnShowLisenter showLisenter;

    //set方法
    public void setOnShowLisenter(OnShowLisenter showLisenter){
        this.showLisenter=showLisenter;
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
                    JSONArray result = jsonObject.getJSONArray("result");
                    //调用接口
                    showLisenter.show(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public void relected(String goods) {
       String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&keyword="+goods+"&count=5";
        //得到请求 解析数据
        OkHttpUtils.getInstance().doGet(url,new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what=0;
                message.obj=json;

                handler.sendMessage(message);

            }
        });


    }
}
