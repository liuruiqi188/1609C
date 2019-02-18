package com.example.zuoye0214.presenter;

import com.example.zuoye0214.activity.RegistActivity;
import com.example.zuoye0214.model.RegistModel;
import com.example.zuoye0214.view.RegistView;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.example.zuoye0214.presenter
 * @date 2019/2/15 13:45
 **/






//传过来的参数
public class RegistPresenter {

    private final RegistModel registModel;

    private final RegistView registView;

    //在构造方法中实例化RegistPresenter
    public RegistPresenter(RegistView view){
        registModel = new RegistModel();
        registView = view;
    }


    public void sendParameter(String phone, String pwd) {
        registModel.regist(phone,pwd);
        registModel.setOnRegistLisenter(new RegistModel.onRegistLisenter() {
            @Override
            public void onResult(String status, String message) {
                registView.view(status,message);

            }
        });

    }
}
