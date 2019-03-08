package com.bw.com.gouwuche.presenter;

import com.bw.com.gouwuche.activity.ShowActivity;
import com.bw.com.gouwuche.model.ShowModel;
import com.bw.com.gouwuche.view.ShowView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.bw.com.gouwuche.presenter
 * @date 2019/3/4 20:09
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    //有参构造
    public ShowPresenter(ShowView view) {
        //实例化
        showModel = new ShowModel();
        //实例化
        showView = view;
    }

    public void relected() {
        showModel.relcted();
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onshow(JSONArray result) {
                showView.show(result);
            }
        });
    }
}
