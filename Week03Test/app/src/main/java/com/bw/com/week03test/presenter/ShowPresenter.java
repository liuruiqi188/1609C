package com.bw.com.week03test.presenter;

import com.bw.com.week03test.fragment.StyleFragment;
import com.bw.com.week03test.model.ShowModel;
import com.bw.com.week03test.view.ShowView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.bw.com.week03test.presenter
 * @date 2019/3/2 8:43
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView view) {
        //实例化M层
        showModel = new ShowModel();
        //实例化V层
        showView = view;
    }

    public void send(String goods1, int page) {
        //联系M层
        showModel.send(goods1,page);
        //接口回调
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onShow(JSONArray result) {
                showView.Show(result);
            }
        });
    }
}
