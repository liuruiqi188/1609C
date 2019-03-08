package com.example.test03.modle;

import android.os.Handler;
import android.os.Message;

import com.example.test03.okhttputils.OkHttpUtils;

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
 * @package com.example.test03.modle
 * @date 2019/2/28 9:06
 **/
public class SearchModel {

    //接口
    public interface OnShowLisenter{
        void onShow(JSONArray result);
    }
    //声明
    public OnShowLisenter showLisenter;
    //set
    public void setOnShowLisenter(OnShowLisenter showLisenter){
        this.showLisenter=showLisenter;
    }



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                //原声解析
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray("result");
                    //jiekou
                    if (showLisenter!=null){
                        showLisenter.onShow(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    public void send(String goods, int page) {
        String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page="+page+"&keyword="+goods+"&count=5";
        //联网请求数据
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
