package com.bw.com.week03test.presenter;

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
 * @fileName FirstPresenter
 * @package com.bw.com.week03test.presenter
 * @date 2019/3/2 10:44
 **/
public class FirstPresenter {

    //创建接口
    public interface OnFirstLisenter{
        void  onfirst(JSONArray result);
    }
    private OnFirstLisenter firstLisenter;
    //set
    public void setOnFirstLisenter(OnFirstLisenter firstLisenter){
        this.firstLisenter=firstLisenter;
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
                    if (firstLisenter!=null){
                        firstLisenter.onfirst(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void relected() {
        String url="http://172.17.8.100/small/commodity/v1/findFirstCategory";
        //解析数据
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //新建消息
                Message message = new Message();
                message.obj=json;
                message.what=0;
                //发送数据
                handler.sendMessage(message);

            }
        });
    }
}
