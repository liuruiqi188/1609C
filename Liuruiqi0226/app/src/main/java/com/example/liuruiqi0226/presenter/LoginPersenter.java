package com.example.liuruiqi0226.presenter;

import com.example.liuruiqi0226.activity.MainActivity;
import com.example.liuruiqi0226.model.LoginModel;
import com.example.liuruiqi0226.view.LoginView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName LoginPersenter
 * @package com.example.liuruiqi0226.presenter
 * @date 2019/2/26 9:46
 **/
public class LoginPersenter<T> {

    private final LoginModel loginModel;
    private final LoginView loginView;
    private Reference reference;

    public LoginPersenter(LoginView view) {
        loginModel = new LoginModel();
        //实例化V
        loginView = view;
    }

    public void send(String phone, String pwd) {
        loginModel.send(phone,pwd);
        loginModel.setonLoginLisenter(new LoginModel.onLoginLisenter() {
            @Override
            public void login(String status) {
                loginView.login(status);
            }
        });
    }
    //解决泄露
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }
//消除数据
    public void deathView(){
        if (reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }
}
