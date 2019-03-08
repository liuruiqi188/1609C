package com.bw.com.gouwuche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.com.gouwuche.R;
import com.bw.com.gouwuche.presenter.LoginPresenter;
import com.bw.com.gouwuche.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView{
    private EditText et_pwd;
    private EditText et_phone;
    private Button regist;
    private Button login;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        login = findViewById(R.id.bt_login);
        regist = findViewById(R.id.bt_regist);


        //实例化P层
        loginPresenter = new LoginPresenter( this);
        //泄露
        loginPresenter.attachView(this);

        //注册事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //登陆事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //非空判断
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(MainActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //传参
                loginPresenter.send(phone,pwd);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.deathView();
    }

    @Override
    public void login(String status) {
        //判断
        if (status.equals("0000")){
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "账号或密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
