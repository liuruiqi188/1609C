package com.example.xiangmu.presenter;

import com.example.xiangmu.fragment.CircleFragment;
import com.example.xiangmu.model.RightModel;
import com.example.xiangmu.view.RightView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName RightPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/5 14:27
 **/
public class RightPresenter {

    private final RightModel rightModel;
    private final RightView rightView;

    public RightPresenter(RightView view) {
    //实例化
        rightModel = new RightModel();

        rightView = view;
    }

    public void send(String id) {
        //联系M层
        rightModel.send(id);
        rightModel.setonRightLisenter(new RightModel.onRightLisenter() {
            @Override
            public void right(JSONArray result) {
                rightView.right(result);
            }
        });
    }
}
