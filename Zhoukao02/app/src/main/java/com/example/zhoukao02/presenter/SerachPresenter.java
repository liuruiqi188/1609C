package com.example.zhoukao02.presenter;

import android.content.Context;

import com.example.zhoukao02.model.SearchModel;
import com.example.zhoukao02.view.SearchView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName SerachPresenter
 * @package com.example.zhoukao02.presenter
 * @date 2019/2/22 15:00
 **/
public class SerachPresenter {

    private final SearchModel searchModel;
    private final SearchView searchView;

    //构造
    public SerachPresenter(SearchView view) {
        //实例化m层
        searchModel = new SearchModel();
        //实例化v
        searchView = view;
    }

    public void related(String goods) {
        //联系M层
        searchModel.relcted(goods);
        //实现接口
        searchModel.OnShowLisenter(new SearchModel.OnShowLisenter() {
            @Override
            public void onShow(JSONArray result) {
                searchView.Search(result);
            }
        });
    }
}
