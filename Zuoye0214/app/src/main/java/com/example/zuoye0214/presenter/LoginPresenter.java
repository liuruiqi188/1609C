package com.example.zuoye0214.presenter;

import com.example.zuoye0214.activity.MainActivity;
import com.example.zuoye0214.model.LoginModel;
import com.example.zuoye0214.view.LoginView;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.example.zuoye0214.presenter
 * @date 2019/2/14 16:50
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;


    //在构造方法中实例化Model对象

    public LoginPresenter(LoginView view) {
        loginModel = new LoginModel();
        loginView = view;
    }


    //传过来的参数
    public void sendParameter(String phone, String pwd) {
       loginModel.login(phone,pwd);
       loginModel.setOnLoginLisnter(new LoginModel.OnLoginLisnter() {
           @Override
           public void onResult(String status) {
               loginView.view(status);
           }
       });
    }
}
