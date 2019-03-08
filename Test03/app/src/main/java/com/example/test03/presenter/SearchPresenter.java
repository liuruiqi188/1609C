package com.example.test03.presenter;

import com.example.test03.fragment.Fragment01;
import com.example.test03.modle.SearchModel;
import com.example.test03.view.ShowView;

import org.json.JSONArray;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName SearchPresenter
 * @package com.example.test03.presenter
 * @date 2019/2/28 9:04
 **/
public class SearchPresenter<T> {

    private final SearchModel searchModel;
    private final ShowView showView;
    private Reference tWeakReference;

    //构造
    public SearchPresenter(ShowView view) {
    //实例化M层
        searchModel = new SearchModel();
        //实例化V层
        showView = view;
    }

    //内存泄漏
    public void attachView(T t){
        tWeakReference = new WeakReference<>(t);
    }

    public void send(String goods1, int page) {
        //给M层传数据
        searchModel.send(goods1,page);
        //M层接口
        searchModel.setOnShowLisenter(new SearchModel.OnShowLisenter() {
            @Override
            public void onShow(JSONArray result) {
                showView.Show(result);
            }
        });
    }

    //内存泄露
    public void deatchView(){
        if (tWeakReference.get()!=null){
            tWeakReference.clear();
            tWeakReference=null;
        }
    }

}
