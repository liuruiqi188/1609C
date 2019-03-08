package com.bw.com.yuekao01.presenter;

import com.bw.com.yuekao01.fragment.Fragment02;
import com.bw.com.yuekao01.model.RightModel;
import com.bw.com.yuekao01.view.RightView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName RightPresenter
 * @package com.bw.com.yuekao01.presenter
 * @date 2019/3/7 14:04
 **/
public class RightPresenter {

    private final RightModel rightModel;
    private final RightView rightView;

    public RightPresenter(RightView view) {
        rightModel = new RightModel();
        rightView = view;
    }

    public void relectedd(String a) {
        rightModel.relected(a);
        rightModel.setOnRightLisenter(new RightModel.OnRightLisenter() {
            @Override
            public void right(JSONArray data) {
                rightView.right(data);
            }
        });
    }
}
