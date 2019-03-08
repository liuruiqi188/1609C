package com.example.liuruiqi0226.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuruiqi0226.R;
import com.example.liuruiqi0226.presenter.RegistPresenter;
import com.example.liuruiqi0226.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    private EditText et_phone;
    private EditText et_pwd;
    private TextView login;
    private Button regist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        login = findViewById(R.id.tv_login);
        regist = findViewById(R.id.bt_regist);

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //实例化P层
        registPresenter = new RegistPresenter(this);

        //点击注册时
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //非空判断
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(RegistActivity.this, "账号或密码不能输入空值！", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
