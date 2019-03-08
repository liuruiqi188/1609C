package com.bw.com.yuekao01.presenter;

import com.bw.com.yuekao01.fragment.Fragment02;
import com.bw.com.yuekao01.model.LeftModel;
import com.bw.com.yuekao01.view.LeftView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName LeftPresenter
 * @package com.bw.com.yuekao01.presenter
 * @date 2019/3/7 11:35
 **/
public class LeftPresenter {

    private final LeftModel leftModel;
    private final LeftView leftView;

    public LeftPresenter(LeftView view) {
        leftModel = new LeftModel();
        leftView = view;
    }

    public void relected() {
        leftModel.relected();
        leftModel.setOnLeftLisenter(new LeftModel.OnLeftLisenter() {
            @Override
            public void left(JSONArray data) {
                leftView.left(data);
            }
        });
    }
}
