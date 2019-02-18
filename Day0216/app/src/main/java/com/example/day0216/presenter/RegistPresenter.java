package com.example.day0216.presenter;

import com.example.day0216.activity.RegistActivity;
import com.example.day0216.model.RegistModel;
import com.example.day0216.view.RegistView;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.example.day0216.presenter
 * @date 2019/2/16 11:05
 **/
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    //有参构造
    public RegistPresenter(RegistView view) {

        //实例化model
        registModel = new RegistModel();

        //实例化view
        registView = view;

    }

    public void send(String phone, String pwd) {
        //model发送参数
        registModel.send(phone,pwd);
        //调用接口
        registModel.setOnRegistLisenter(new RegistModel.OnRegistLisenter() {
            @Override
            public void onResult(String status, String message) {
                //往v层传数据
                registView.regist(status,message);
            }
        });
    }
}
