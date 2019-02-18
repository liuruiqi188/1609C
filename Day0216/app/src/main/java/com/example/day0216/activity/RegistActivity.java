package com.example.day0216.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day0216.R;
import com.example.day0216.presenter.RegistPresenter;
import com.example.day0216.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    private TextView regist_pwd;
    private TextView regist_phone;
    private Button qd;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //找控件
        regist_phone = findViewById(R.id.regist_num);
        regist_pwd = findViewById(R.id.regist_pwd);
        qd = findViewById(R.id.qd);

        //实例化p层
        registPresenter = new RegistPresenter(this);


        //确定点击时间
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = regist_phone.getText().toString();
                String pwd = regist_pwd.getText().toString();
                if (phone.length()==0||pwd.length()==0){
                    Toast.makeText(RegistActivity.this, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                registPresenter.send(phone,pwd);


            }
        });


    }

    @Override
    public void regist(String status, String message) {
        if (status.equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"注册失败！",Toast.LENGTH_SHORT).show();
        }
    }
}
