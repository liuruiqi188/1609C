package com.example.xiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiangmu.R;
import com.example.xiangmu.presenter.RegistPresenter;
import com.example.xiangmu.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    private EditText user_phone;
    private EditText user_pwd;
    private Button regist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //找控件
        user_phone = findViewById(R.id.user_phone);
        user_pwd = findViewById(R.id.user_pwd);
        regist = findViewById(R.id.bt_regist);

        //实例化P层
        registPresenter = new RegistPresenter(this);


        //点击注册时间
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = user_phone.getText().toString();
                String pwd = user_pwd.getText().toString();
                //非空判断
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(RegistActivity.this, "账号或者密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联系P层
                registPresenter.send(phone,pwd);

            }
        });

    }

    @Override
    public void regist(String status) {
        if (status.equals("0000")){
            //成功直接跳转展示页面
            Intent intent = new Intent(RegistActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "你的账号或密码格式不对，请重新输入！", Toast.LENGTH_SHORT).show();
        }
    }
}
