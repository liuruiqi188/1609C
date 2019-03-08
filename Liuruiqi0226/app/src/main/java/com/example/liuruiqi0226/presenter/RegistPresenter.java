package com.example.liuruiqi0226.presenter;

import com.example.liuruiqi0226.activity.RegistActivity;
import com.example.liuruiqi0226.model.RegistModel;
import com.example.liuruiqi0226.view.RegistView;

/**
 * @author liuruiqi
 * @fileName RegistPresenter
 * @package com.example.liuruiqi0226.presenter
 * @date 2019/2/26 9:06
 **/
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    //构造
    public RegistPresenter(RegistView view) {
        //实例化M层
        registModel = new RegistModel();
        //实例化V
        registView = view;
    }

    public void send(String phone, String pwd) {
        //传给M层
        registModel.send(phone,pwd);
        //接口
        registModel.setonRegistLisenter(new RegistModel.onRegistLisenter() {
            @Override
            public void onRegist(String status) {
                registView.regist(status);
            }
        });
    }
}
