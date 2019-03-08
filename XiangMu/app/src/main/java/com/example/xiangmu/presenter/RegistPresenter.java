package com.example.xiangmu.presenter;

import com.example.xiangmu.activity.RegistActivity;
import com.example.xiangmu.model.RegistModel;
import com.example.xiangmu.view.RegistView;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/4 15:33
 **/
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    public RegistPresenter(RegistView view) {
        //实例化M层
        registModel = new RegistModel();
        //实例化V层
        registView = view;
    }

    public void send(String phone, String pwd) {
        //M层联系
        registModel.send(phone,pwd);
        //接口
        registModel.setOnRegistLisenter(new RegistModel.OnRegistLisenter() {
            @Override
            public void onregist(String status) {
                registView.regist(status);
            }
        });

    }
}
