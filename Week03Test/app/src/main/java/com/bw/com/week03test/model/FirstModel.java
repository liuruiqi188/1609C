package com.bw.com.week03test.model;

import com.bw.com.week03test.fragment.StyleFragment;
import com.bw.com.week03test.presenter.FirstPresenter;
import com.bw.com.week03test.view.FirstView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName FirstModel
 * @package com.bw.com.week03test.model
 * @date 2019/3/2 10:43
 **/
public class FirstModel {

    private final FirstPresenter firstPresenter;
    private final FirstView firstView;

    public FirstModel(FirstView view) {
        //实例化
        firstPresenter = new FirstPresenter();
        //实例化
        firstView = view;
    }

    public void relected() {
        firstPresenter.relected();
        firstPresenter.setOnFirstLisenter(new FirstPresenter.OnFirstLisenter() {
            @Override
            public void onfirst(JSONArray result) {
                firstView.first(result);
            }
        });
    }
}
