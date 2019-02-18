package com.example.day0216.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day0216.R;
import com.example.day0216.Utils;
import com.example.day0216.presenter.LoginPresenter;
import com.example.day0216.view.LoginView;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity implements LoginView {

    private TextView login_num;
    private TextView login_pwd;
    private Button login;
    private Button regist;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        login_num = findViewById(R.id.login_num);
        login_pwd = findViewById(R.id.login_pwd);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);

        //实例化LoginPresenter
        loginPresenter = new LoginPresenter(this);

        //点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = login_num.getText().toString();
                String pwd = login_pwd.getText().toString();
                //判断输入的是否符合规矩
                boolean mobileNO = Utils.isMobileNO(phone);
                if (!mobileNO){
                    Toast.makeText(MainActivity.this, "手机格式不对！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length()<=3){
                    Toast.makeText(MainActivity.this, "密码不正确！", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenter.send(phone,pwd);


            }
        });

        //注册点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void Login(String status) {
        if (status.equals("0000")){
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "账号或者密码错误！", Toast.LENGTH_SHORT).show();
        }
    }
}
