package com.bw.com.yuekao01.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.com.yuekao01.okhttputils.OkHttpUtils;

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
 * @package com.bw.com.yuekao01.model
 * @date 2019/3/7 14:05
 **/
public class RightModel {

    public interface OnRightLisenter{
        void right(JSONArray data);
    }
    private OnRightLisenter rightLisenter;

    public void setOnRightLisenter(OnRightLisenter rightLisenter){
        this.rightLisenter=rightLisenter;
    }



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //原生
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray data = jsonObject.getJSONArray("data");
                    //接口
                    if (rightLisenter!=null){
                        rightLisenter.right(data);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void relected(String a) {
        String url="http://172.17.8.100/ks/product/getProductCatagory?cid="+a;
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("aaa",json);
                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });
    }
}
