package com.example.week02test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week02test.R;
import com.example.week02test.presenter.RegistPresenter;
import com.example.week02test.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView {

    private EditText et_phone;
    private EditText et_pwd;
    private Button regist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        regist = findViewById(R.id.bt_regist);

        //实例化p层
        registPresenter = new RegistPresenter(this);


        //点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //判断控制
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(RegistActivity.this, "不能输入空值", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }
}
