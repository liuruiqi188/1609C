package com.example.liuruiqi0226.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuruiqi0226.R;
import com.example.liuruiqi0226.custom.CustomView;
import com.example.liuruiqi0226.presenter.LoginPersenter;
import com.example.liuruiqi0226.view.LoginView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText et_pwd;
    private EditText et_phone;
    private Button login;
    private TextView regist;
    private CheckBox ck;
    private LoginPersenter loginPersenter;
    private SharedPreferences sp;

    private String phone;
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


 sp = getSharedPreferences("第一次", Context.MODE_PRIVATE);
        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        login = findViewById(R.id.bt_login);
        regist = findViewById(R.id.tv_regist);
        ck = findViewById(R.id.ck);
        boolean a = sp.getBoolean("记住", false);

        if (a){
            et_phone.setText(sp.getString("phone",""));
            et_pwd.setText(sp.getString("pwd",""));
            ck.setChecked(a);
        }

        //实例化P层
        loginPersenter = new LoginPersenter(this);

        //关联弱引用
        loginPersenter.attachView(this);

        //注册点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //点击登录成功实践
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框中的值
                phone = et_phone.getText().toString();
                pwd = et_pwd.getText().toString();
                //非空判断
                if (phone.equals("")|| pwd.equals("")){
                    Toast.makeText(MainActivity.this, "账号或密码不能输入空值！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //sp
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("记住", ck.isChecked());
                edit.putString("phone", phone);
                edit.putString("pwd", pwd);
                edit.commit();


                //联系P层
                loginPersenter.send(phone, pwd);
            }
        });

    }

    @Override
    public void login(String status) {
        if (status.equals("0000")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
//            //判断ck是否被勾选
//            if (ck.isChecked()){
//                edit = sp.edit();
//                edit.putString("phone", phone);
//                edit.putString("pwd", pwd);
//                edit.putBoolean("记住",true);
//                edit.commit();
//            }

            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra("num",phone);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "账号或密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPersenter.deathView();
        Log.i("zzz","销毁了");
    }
}
