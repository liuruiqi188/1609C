package com.example.xiangmu.presenter;

import com.example.xiangmu.fragment.CircleFragment;
import com.example.xiangmu.model.SearchModel;
import com.example.xiangmu.view.SearchView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName SearchPresenter
 * @package com.example.xiangmu.presenter
 * @date 2019/3/5 15:36
 **/
public class SearchPresenter {

    private final SearchModel searchModel;
    private final SearchView searchView1;

    public SearchPresenter(SearchView searchView) {
        //实例
        searchModel = new SearchModel();
        //实例
        searchView1 = searchView;
    }

    public void send(String goods1, int page) {
        searchModel.send(goods1,page);
        searchModel.setonSearchLisenter(new SearchModel.onSearchLisenter() {
            @Override
            public void onsearch(JSONArray result) {
                searchView1.search(result);
            }
        });
    }
}
