package com.example.xiangmu.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
 * @fileName ViewpagerModel
 * @package com.example.xiangmu.model
 * @date 2019/3/7 19:08
 **/
public class ViewpagerModel {

    //接口回调
    public interface onViewpagerLisenter{
        void viewpager(JSONArray data);
    }
    private onViewpagerLisenter viewpagerLisenter;

    public void setonViewpagerLisenter(onViewpagerLisenter viewpagerLisenter){
        this.viewpagerLisenter=viewpagerLisenter;
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray data = jsonObject.getJSONArray("result");
                    //接口
                    if (viewpagerLisenter!=null){
                        viewpagerLisenter.viewpager(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void relected() {
        String url="http://172.17.8.100/small/commodity/v1/bannerShow";
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("ppp",json);
                Message message = new Message();
                message.what=0;
                message.obj=json;

                handler.sendMessage(message);

            }
        });
    }
}
