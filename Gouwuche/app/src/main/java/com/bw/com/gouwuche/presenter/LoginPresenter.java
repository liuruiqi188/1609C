package com.bw.com.gouwuche.presenter;

import com.bw.com.gouwuche.activity.MainActivity;
import com.bw.com.gouwuche.model.LoginModel;
import com.bw.com.gouwuche.view.LoginView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName LoginPresenter
 * @package com.bw.com.gouwuche.presenter
 * @date 2019/3/4 19:15
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
        //联系M层
        loginModel.send(phone,pwd);
        //接口
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
