package com.bw.com.liuruiqi0304.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.com.liuruiqi0304.R;
import com.bw.com.liuruiqi0304.presenter.RegistPresenter;
import com.bw.com.liuruiqi0304.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    private Button regist;
    private TextView et_pwd;
    private TextView et_phone;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //找控件
        regist = findViewById(R.id.bt_regist);
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);

        //实例化p
        registPresenter = new RegistPresenter(this);

        registPresenter.attachView(this);
        //点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //非空判断
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(RegistActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联系
                registPresenter.send(phone,pwd);
            }
        });
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        registPresenter.deathView();
    }

    @Override
    public void regist(String status) {
        if (status.equals("0000")){
            Intent intent = new Intent(RegistActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "你注册的账号或密码格式错误！请重新注册", Toast.LENGTH_SHORT).show();
        }
    }
}
