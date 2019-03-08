package com.example.week02test.presenter;

import com.example.week02test.activity.RegistActivity;
import com.example.week02test.model.RegistModel;
import com.example.week02test.view.RegistView;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.example.week02test.presenter
 * @date 2019/2/23 11:35
 **/
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    public RegistPresenter(RegistView view) {
        //实例化
        registModel = new RegistModel();
        //实例化
        registView = view;
    }

    public void send(String phone, String pwd) {
        //联系m层
        registModel.send(phone,pwd);
        //实现接口
        registModel.onRegistLisenter(new RegistModel.onRegistLisenter() {
            @Override
            public void onRegist(String status) {
                registView.regist(status);
            }
        });


    }
}
