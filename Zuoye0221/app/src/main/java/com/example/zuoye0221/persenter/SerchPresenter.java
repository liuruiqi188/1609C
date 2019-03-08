package com.example.zuoye0221.persenter;

import com.example.zuoye0221.CustomSearch;
import com.example.zuoye0221.MainActivity;
import com.example.zuoye0221.model.SerachModel;
import com.example.zuoye0221.view.SerachView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName SerchPresenter
 * @package com.example.zuoye0221.persenter
 * @date 2019/2/21 16:08
 **/
public class SerchPresenter {

    private final SerachModel serachModel;
    private final SerachView serachView;

    //构造
    public SerchPresenter(SerachView view) {
        //实例化M
        serachModel = new SerachModel();
        //实例化v
        serachView = view;

    }



    public void related(String goods) {
        //联系M层
        serachModel.relected(goods);
        //监听set
        serachModel.setOnShowLisenter(new SerachModel.OnShowLisenter() {
            @Override
            public void show(JSONArray result) {
                serachView.serach(result);
            }
        });
    }
}
