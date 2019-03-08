package com.bw.com.liuruiqi0304.presenter;

import com.bw.com.liuruiqi0304.activity.RegistActivity;
import com.bw.com.liuruiqi0304.model.RegistModel;
import com.bw.com.liuruiqi0304.view.RegistView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.bw.com.liuruiqi0304.presenter
 * @date 2019/3/4 9:36
 **/
public class RegistPresenter<T> {

    private final RegistModel registModel;
    private final RegistView registView;
    private Reference reference;

    public RegistPresenter(RegistView view) {
        //
        registModel = new RegistModel();
        registView = view;
    }
    //内存泄漏
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }

    public void send(String phone, String pwd) {
        registModel.send(phone,pwd);
        registModel.setOnRegistLisenter(new RegistModel.OnRegistLisenter() {
            @Override
            public void onRegist(String status) {
                registView.regist(status);
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
