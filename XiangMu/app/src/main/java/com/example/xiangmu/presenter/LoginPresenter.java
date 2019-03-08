package com.example.xiangmu.presenter;

import com.example.xiangmu.activity.MainActivity;
import com.example.xiangmu.model.LoginModel;
import com.example.xiangmu.view.LoginView;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/4 14:57
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenter(LoginView view) {
        //实例化M
        loginModel = new LoginModel();
        //实例化
        loginView = view;

    }

    public void send(String phone, String pwd) {
        loginModel.send(phone,pwd);
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onLogin(String status) {
                loginView.onLogin(status);
            }
        });
    }
}
