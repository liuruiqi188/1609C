package com.bw.com.week03test.model;

import android.os.Handler;
import android.os.Message;

import com.bw.com.week03test.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShowModel
 * @package com.bw.com.week03test.model
 * @date 2019/3/2 8:45
 **/
public class ShowModel {

    //接口
    public interface OnShowLisenter{
        void onShow(JSONArray result);
    }
    private OnShowLisenter showLisenter;
    //set
    public void setOnShowLisenter (OnShowLisenter showLisenter){
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
                    //接口
                    if (showLisenter!=null){
                        showLisenter.onShow(result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void send(String goods1, int page) {
        String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page="+page+"&keyword="+goods1+"&count=5";
        //得到联网数据
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
