package com.example.zuoye0214.presenter;

import com.example.zuoye0214.activity.ShowActivity;
import com.example.zuoye0214.model.ShowModel;
import com.example.zuoye0214.view.ShowView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.example.zuoye0214.presenter
 * @date 2019/2/16 8:28
 **/

     //这个页面第一步是创建他的构造方法 ---------记住

public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView view){
        showModel = new ShowModel();
        showView = view;
    }



    public void related() {
        showModel.getGoodsData();
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onShow(JSONArray result) {
                //把数据给View层
                showView.show(result);
            }
        });
    }
}
