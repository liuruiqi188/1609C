package com.bw.com.week03test.presenter;

import com.bw.com.week03test.fragment.StyleFragment;
import com.bw.com.week03test.model.SecendModel;
import com.bw.com.week03test.view.SecendView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName SecendPresenter
 * @package com.bw.com.week03test.presenter
 * @date 2019/3/2 14:37
 **/
public class SecendPresenter {

    private final SecendModel secendModel;
    private final SecendView secendView;

    public SecendPresenter(SecendView view) {
        //实例化
        secendModel = new SecendModel();
        //实例化
        secendView = view;
    }

    public void sendd(String id) {
        secendModel.sendd(id);
        secendModel.setOnsshowLisenter(new SecendModel.OnsshowLisenter() {
            @Override
            public void sshow(JSONArray result) {
                secendView.secend(result);
            }
        });
    }
}
