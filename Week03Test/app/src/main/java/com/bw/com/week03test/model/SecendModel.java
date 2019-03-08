package com.bw.com.week03test.model;

import android.os.Handler;
import android.os.Message;

import com.bw.com.week03test.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName SecendModel
 * @package com.bw.com.week03test.model
 * @date 2019/3/2 14:40
 **/
public class SecendModel {
    public interface OnsshowLisenter{
        void sshow(JSONArray result);
    }
    private OnsshowLisenter onsshowLisenter;
    public void setOnsshowLisenter(OnsshowLisenter onsshowLisenter){
        this.onsshowLisenter=onsshowLisenter;
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
                    if (onsshowLisenter!=null){
                        onsshowLisenter.sshow(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    };


    public void sendd(String id) {
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
