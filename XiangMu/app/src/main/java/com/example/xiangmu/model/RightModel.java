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
 * @fileName RightModel
 * @package com.example.xiangmu.model
 * @date 2019/3/5 14:29
 **/
public class RightModel {

    //接口
    public interface onRightLisenter{
        void right(JSONArray result);
    }
    private onRightLisenter rightLisenter;

    public void setonRightLisenter(onRightLisenter rightLisenter){
        this.rightLisenter=rightLisenter;
    }


    //handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //解析数据
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray("result");
                    //接口
                    if (rightLisenter!=null){
                        rightLisenter.right(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    };
    public void send(String id) {
        String url="http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId="+id;
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
