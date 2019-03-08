package com.bw.com.liuruiqi0304.presenter;

import com.bw.com.liuruiqi0304.activity.MainActivity;
import com.bw.com.liuruiqi0304.model.LoginModel;
import com.bw.com.liuruiqi0304.view.LoginView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.bw.com.liuruiqi0304.presenter
 * @date 2019/3/4 9:03
 **/
public class LoginPresenter<T> {

    private final LoginModel loginModel;
    private final LoginView loginView;
    private Reference reference;

    //有参构造
    public LoginPresenter(LoginView view) {
        //实例化
        loginModel = new LoginModel();
        //实例化
        loginView = view;
    }
    //内存泄漏
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }

    public void send(String phone, String pwd) {
        //M层传参
        loginModel.send(phone,pwd);
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onLogin(String status) {
                loginView.login(status);
            }
        });

    }
    public void deathView(){
        if (reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }
}
