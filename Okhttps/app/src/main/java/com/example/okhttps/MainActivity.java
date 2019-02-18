package com.example.okhttps;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText pwd;
    private EditText zh;
    private Button login;
    private Button regist;
    private String path = "http://172.17.8.100/small/user/v1/login";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json= (String) msg.obj;
                Gson gson = new Gson();


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
        initData();

    }

    private void initView() {
        //找控件
        zh = findViewById(R.id.num);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);



    }
    private void initData() {
        //注册点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //注册登陆事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框中数据
                String phone  = zh.getText().toString();
                String password  = pwd.getText().toString();
                //判断
                if (phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "手机号或密码不能为空!!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (phone.length() != 11) {
                        Toast.makeText(MainActivity.this, "手机号格式不正确!!!", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 3) {
                        Toast.makeText(MainActivity.this, "密码格式不正确!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        //实例化okhttp
                        OkHttpClient okHttpClient = new OkHttpClient();
                        //设置请求体
                        RequestBody requestBody = new FormBody.Builder()
                                .add("phone", phone)
                                .add("pwd", password)
                                .build();
                        //设置请求方式
                        Request request = new Request.Builder()
                                .post(requestBody)
                                .url(path)
                                .build();
                        //异步请求
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                //获取到数据
                                String json = response.body().toString();
                                Log.i("yyy","onResponse:"+json);
                                //创建消息对象
                                Message message = new Message();
                                message.what=0;
                                message.obj=json;
                                //发送消息

                            }
                        });
                    }
                }
            }
        });

    }
}
