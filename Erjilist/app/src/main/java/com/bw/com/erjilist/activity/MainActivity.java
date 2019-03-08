package com.bw.com.erjilist.activity;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.bw.com.erjilist.R;
import com.bw.com.erjilist.adapter.MyAdapter;
import com.bw.com.erjilist.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlv;
    private CheckBox ck;
    private String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
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
                    //适配器
                    MyAdapter myAdapter = new MyAdapter(MainActivity.this,result);
                    rlv.setAdapter(myAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //找控件
        rlv = findViewById(R.id.rlv);
        ck = findViewById(R.id.checkbox);

        //布局的管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rlv.setLayoutManager(layoutManager);

        //初始化数据
        initData();


        //ck点击事件调用适配器里的接口
        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

    }

    private void initData() {
        //获得网络数据
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //消息
                Message message = new Message();
                message.obj=json;
                message.what=0;
                //发送消息

                handler.sendMessage(message);

            }
        });

    }
}
