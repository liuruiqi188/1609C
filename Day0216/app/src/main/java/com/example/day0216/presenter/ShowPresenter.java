package com.example.day0216.presenter;

import com.example.day0216.activity.ShowActivity;
import com.example.day0216.model.ShowModel;
import com.example.day0216.view.ShowView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.example.day0216.presenter
 * @date 2019/2/17 18:56
 **/
public class ShowPresenter {
    private final ShowModel showModel;
    private final ShowView showView;

    //有参构造


    public ShowPresenter(ShowView view) {
        //实例化modle
        showModel = new ShowModel();
        //实例化v
        showView = view;
    }

    public void related() {
        //联系M层
        showModel.getGoodsData();
        showModel.setOnShoeLisnter(new ShowModel.OnShoeLisnter() {
            @Override
            public void onResult(JSONArray result) {
                showView.show(result);
            }
        });

    }
}
