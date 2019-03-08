package com.example.week02test.model;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;

import com.example.week02test.OkHttpUtils.OkHttpUtils;

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
 * @package com.example.week02test.model
 * @date 2019/2/25 13:41
 **/
public class SearchModel {

    //创建接口、
    public interface OnSearchLisenter{
        void onSearch(JSONArray result);
    }
    //声明
    public OnSearchLisenter searchLisenter;

    //set接口
    public void setOnSearchLisenter(OnSearchLisenter searchLisenter){
        this.searchLisenter=searchLisenter;
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
                    //判断接口
                    if (searchLisenter!=null){
                        searchLisenter.onSearch(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };






    public void send(String goods1, int page) {
        String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page="+page+"&keyword="+goods1+"&count=10";
        //调用okhttp
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
