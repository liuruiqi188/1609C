package com.example.xiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiangmu.R;
import com.example.xiangmu.presenter.LoginPresenter;
import com.example.xiangmu.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText user_phone;
    private EditText user_pwd;
    private Button login;
    private Button regist;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        user_phone = findViewById(R.id.user_phone);
        user_pwd = findViewById(R.id.user_pwd);
        login = findViewById(R.id.bt_login);
        regist = findViewById(R.id.bt_regist);

        //实例化P层
        loginPresenter = new LoginPresenter(this);

        //注册点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //跳转
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //登录点击时间
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = user_phone.getText().toString();
                String pwd = user_pwd.getText().toString();
                //非空判断
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(MainActivity.this, "账号或者密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联系P层
                loginPresenter.send(phone,pwd);
            }
        });


    }

    @Override
    public void onLogin(String status) {
        if (status.equals("0000")){
            //成功 跳转
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "账号或密码错误,请重新登陆！", Toast.LENGTH_SHORT).show();
        }
    }
}
