package com.example.day0216.presenter;

import com.example.day0216.activity.MainActivity;
import com.example.day0216.model.LoginModel;
import com.example.day0216.view.LoginView;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.example.day0216.presenter
 * @date 2019/2/16 10:29
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    //创建有参构造
    public LoginPresenter(LoginView view) {
        //实例化Model
        loginModel = new LoginModel();

        loginView = view;

    }

    public void send(String phone, String pwd) {
        loginModel.send(phone,pwd);
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onResult(String status) {
                loginView.Login(status);
            }
        });
    }
}
