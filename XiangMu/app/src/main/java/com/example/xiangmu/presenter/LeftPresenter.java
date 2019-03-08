package com.example.xiangmu.presenter;

import com.example.xiangmu.fragment.CircleFragment;
import com.example.xiangmu.model.LeftModel;
import com.example.xiangmu.view.LeftView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName LeftPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/5 11:42
 **/
public class LeftPresenter {

    private final LeftModel leftModel;
    private final LeftView leftView;

    public LeftPresenter(LeftView view) {
        //实例化
        leftModel = new LeftModel();
        leftView = view;

    }

    public void relected() {
        //联系
        leftModel.relected();
        leftModel.setOnFirstLisenter(new LeftModel.OnFirstLisenter() {
            @Override
            public void onfirst(JSONArray result) {
                leftView.left(result);
            }
        });
    }
}
