package com.example.zuoye0214.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zuoye0214.R;
import com.example.zuoye0214.Utils;
import com.example.zuoye0214.presenter.LoginPresenter;
import com.example.zuoye0214.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoginView {

    private EditText et_pwd;
    private EditText et_phone;
    private Button bt_login;
    private Button bt_regist;
    private boolean mobileNO;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        et_phone = findViewById(R.id.ed_name);
        et_pwd = findViewById(R.id.ed_pwd);
        bt_login = findViewById(R.id.bt_login);
        bt_regist = findViewById(R.id.bt_regist);


        //实例presenter
        presenter = new LoginPresenter(this);

        //点击登录按钮
        bt_login.setOnClickListener(this);

        //点击注册摁钮
        bt_regist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                //获得输入框的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //连接utils
                boolean mobileNO = Utils.isMobileNO(phone);
                if (!mobileNO){
                    Toast.makeText(this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length()<3){
                    Toast.makeText(this, "密码不对", Toast.LENGTH_SHORT).show();
                    return;
                }
                //传参
                presenter.sendParameter(phone,pwd);

                break;
            case  R.id.bt_regist:
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    public void view(String status) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        startActivity(intent);
        finish();
    }
}
