package com.bw.com.gouwuche.presenter;

import com.bw.com.gouwuche.activity.RegistActivity;
import com.bw.com.gouwuche.model.RegistModel;
import com.bw.com.gouwuche.view.RegistVew;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.bw.com.gouwuche.presenter
 * @date 2019/3/4 19:37
 **/
public class RegistPresenter<T> {

    private final RegistModel registModel;
    private final RegistVew registVew;
    private Reference reference;
    public RegistPresenter(RegistVew view) {
        //
        registModel = new RegistModel();
        //实例
        registVew = view;
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
                registVew.regist(status);
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
