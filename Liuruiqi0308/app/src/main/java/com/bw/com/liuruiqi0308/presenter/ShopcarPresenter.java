package com.bw.com.liuruiqi0308.presenter;

import com.bw.com.liuruiqi0308.fragment.ShopCarFragment;
import com.bw.com.liuruiqi0308.model.ShopcarModel;
import com.bw.com.liuruiqi0308.view.ShopcarView;

import org.json.JSONArray;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName ShopcarPresenter
 * @package com.bw.com.liuruiqi0308.presenter
 * @date 2019/3/8 14:12
 **/
public class ShopcarPresenter <T>{

    private final ShopcarModel shopcarModel;
    private final ShopcarView shopcarView;
    private Reference tweak;

    //构造
    public ShopcarPresenter(ShopcarView view) {
        //实例化M层
        shopcarModel = new ShopcarModel();
        //实例化
        shopcarView = view;
    }
    //内存泄漏
    public void attachView(T t){
        tweak = new WeakReference<>(t);
    }

    public void relected() {
        //联系M层
        shopcarModel.relected();
        shopcarModel.setonFirstLisenter(new ShopcarModel.onFirstLisenter() {
            @Override
            public void onfirst(JSONArray data) {
                shopcarView.shopcar(data);
            }
        });
    }

    public void deathView(){
        if (tweak.get()!=null){
            tweak.clear();
            tweak=null;
        }
    }

}
