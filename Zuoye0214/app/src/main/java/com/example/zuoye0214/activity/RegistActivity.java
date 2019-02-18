package com.example.zuoye0214.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zuoye0214.R;
import com.example.zuoye0214.presenter.RegistPresenter;
import com.example.zuoye0214.view.RegistView;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener ,RegistView {

    private Button bt_commit;
    private EditText et_pwd;
    private EditText et_phone;
    private RegistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        //找控件
        bt_commit = findViewById(R.id.bt_commit);
        et_phone = findViewById(R.id.ed_name);
        et_pwd = findViewById(R.id.ed_pwd);

        //实例化RegistPersenter
        presenter = new RegistPresenter(this);


        //按钮的点击事件
        bt_commit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //获取输入框里的值
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        //进行判断
        if (phone.length()==0||pwd.length()==0){
            Toast.makeText(this, "输入的账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.sendParameter(phone,pwd);
    }

    @Override
    public void view(String status, String message) {
        if (status.equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "格式不对！", Toast.LENGTH_SHORT).show();
        }
    }
}
