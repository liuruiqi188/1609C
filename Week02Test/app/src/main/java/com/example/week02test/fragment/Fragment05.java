package com.example.week02test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week02test.R;
import com.example.week02test.activity.RegistActivity;
import com.example.week02test.presenter.LoginPresenter;
import com.example.week02test.view.LoginView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.week02test.fragment
 * @date 2019/2/23 9:27
 **/
public class Fragment05 extends Fragment implements LoginView {

    private TextView et_pwd;
    private TextView et_phone;
    private Button login;
    private Button regist;
    private LoginPresenter loginPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment05,null,false);

        //找控件
        et_phone = view.findViewById(R.id.et_phone);
        et_pwd = view.findViewById(R.id.et_pwd);
        login = view.findViewById(R.id.bt_login);
        regist = view.findViewById(R.id.bt_regist);

        //注册点击事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistActivity.class);
                startActivity(intent);
            }
        });

        //实例化P层
        loginPresenter = new LoginPresenter(this);


        //登陆时间
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框里的值
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                //判断控制
                if (phone.equals("")||pwd.equals("")){
                    Toast.makeText(getActivity(), "不能输入空值！", Toast.LENGTH_SHORT).show();
                    return;
                }

                //传递数据
                loginPresenter.send(phone,pwd);

            }
        });





        return view;
    }

    @Override
    public void login(String status) {
        if (status.equals("0000")){
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "账号密码错误，请重新登录！", Toast.LENGTH_SHORT).show();
        }
    }
}
