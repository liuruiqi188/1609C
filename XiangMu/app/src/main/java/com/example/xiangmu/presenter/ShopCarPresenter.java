package com.example.xiangmu.presenter;

import com.example.xiangmu.fragment.ShopCarFragment;
import com.example.xiangmu.model.ShopCarModel;
import com.example.xiangmu.view.ShopCarView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShopCarPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/5 13:58
 **/
public class ShopCarPresenter {

    private final ShopCarModel shopCarModel;
    private final ShopCarView shopCarView;

    //构造
    public ShopCarPresenter(ShopCarView view) {
        //实例化
        shopCarModel = new ShopCarModel();
        //实例化
        shopCarView = view;
    }

    public void relected() {
        shopCarModel.relected();
        shopCarModel.setOnShopCarLisenter(new ShopCarModel.OnShopCarLisenter() {
            @Override
            public void shopcar(JSONArray result) {
                shopCarView.shopcar(result);
            }
        });
    }
}
