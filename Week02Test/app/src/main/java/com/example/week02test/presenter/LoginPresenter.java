package com.example.week02test.presenter;

import com.example.week02test.fragment.Fragment05;
import com.example.week02test.model.LoginModel;
import com.example.week02test.view.LoginView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.example.week02test.presenter
 * @date 2019/2/23 10:03
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    //有参构造
    public LoginPresenter(LoginView view) {
        //实例化m层
        loginModel = new LoginModel();
        //实例化V层
        loginView = view;

    }

    public void send(String phone, String pwd) {
        //M层传递数据
        loginModel.send(phone,pwd);
        //实现接口
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onLogin(String status) {
                loginView.login( status);
            }
        });


    }
}
