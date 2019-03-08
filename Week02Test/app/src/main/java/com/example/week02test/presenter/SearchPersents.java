package com.example.week02test.presenter;

import com.example.week02test.fragment.Fragment01;
import com.example.week02test.model.SearchModel;
import com.example.week02test.view.SearchView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName SearchPersents
 * @package com.example.week02test.presenter
 * @date 2019/2/25 13:39
 **/
public class SearchPersents {

    private final SearchModel searchModel;
    private final SearchView searchView;

    //有参构造
    public SearchPersents(SearchView view) {
        //实例化Mceng
        searchModel = new SearchModel();
        //实例化view
        searchView = view;
    }

    public void send(String goods1, int page) {
        //联系M层
        searchModel.send(goods1,page);
        searchModel.setOnSearchLisenter(new SearchModel.OnSearchLisenter() {
            @Override
            public void onSearch(JSONArray result) {
                searchView.Search(result);
            }
        });
    }
}
