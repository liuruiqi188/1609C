package com.example.liuruiqi0218.presenter;

import com.example.liuruiqi0218.activity.MainActivity;
import com.example.liuruiqi0218.model.ShowModel;
import com.example.liuruiqi0218.view.ShowView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.example.liuruiqi0218.presenter
 * @date 2019/2/18 8:50
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    //构造
    public ShowPresenter(ShowView view) {
        //实例化M层
        showModel = new ShowModel();
        //实例化V
        showView = view;

    }

    public void relate() {
        //发送数据
        showModel.lianxi();
        //jiekou
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onResult(JSONArray data1) {
                showView.Show(data1);
            }
        });
    }
}
