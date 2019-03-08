package com.bw.com.shop2.presenter;

import com.bw.com.shop2.activity.MainActivity;
import com.bw.com.shop2.bean.Datazhong;
import com.bw.com.shop2.model.ShowModel;
import com.bw.com.shop2.view.ShowView;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.bw.com.shop2.presenter
 * @date 2019/3/6 15:47
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView  view) {
        showModel = new ShowModel();
        showView = view;
    }

    public void relected() {
        showModel.relected();
        showModel.OnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onshow(ArrayList<Datazhong> data) {
                showView.show(data);
            }
        });

    }
}
