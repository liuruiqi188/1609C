package com.example.zuoye0214.model;

import android.os.Handler;
import android.os.Message;

import com.example.zuoye0214.OkhttpsUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName ShowModel
 * @package com.example.zuoye0214.model
 * @date 2019/2/16 8:32
 **/
public class ShowModel {
    private String url="http://172.17.8.100/small/commodity/v1/bannerShow";
    //创建接口
    public interface OnShowLisenter{
        void onShow(JSONArray result);
    }
    //接口
    private OnShowLisenter lisenter;
    //set接口
    public void setOnShowLisenter(OnShowLisenter lisenter){
        this.lisenter=lisenter;
    }




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
                    if (lisenter!=null){
                        lisenter.onShow(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    //获取网络数据
    public void getGoodsData() {
        OkhttpsUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //得到响应码
                String json = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });


    }
}
