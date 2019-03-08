package com.example.xiangmu.presenter;

import android.view.ViewManager;

import com.example.xiangmu.fragment.FirstFragment;
import com.example.xiangmu.model.ViewpagerModel;
import com.example.xiangmu.view.ViewpagerView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ViewpagerParsenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/7 19:07
 **/
public class ViewpagerParsenter {

    private final ViewpagerModel viewpagerModel;
    private final ViewpagerView viewpagerView;

    public ViewpagerParsenter(ViewpagerView view) {
        //实例化
        viewpagerModel = new ViewpagerModel();
        viewpagerView = view;
    }

    public void reletecd() {
        viewpagerModel.relected();
        viewpagerModel.setonViewpagerLisenter(new ViewpagerModel.onViewpagerLisenter() {
            @Override
            public void viewpager(JSONArray data) {
                viewpagerView.viewpager(data);
            }
        });
    }
}
