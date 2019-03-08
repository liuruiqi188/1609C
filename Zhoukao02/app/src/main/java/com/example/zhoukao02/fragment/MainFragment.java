package com.example.zhoukao02.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhoukao02.CustomView;
import com.example.zhoukao02.R;
import com.example.zhoukao02.adapter.MyAdapter;
import com.example.zhoukao02.base.BaseFragment;
import com.example.zhoukao02.presenter.SerachPresenter;
import com.example.zhoukao02.view.SearchView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName MainFragment
 * @package com.example.zhoukao02.fragment
 * @date 2019/2/22 14:03
 **/
public class MainFragment extends BaseFragment implements SearchView {

    private CustomView cv;
   private RecyclerView rlv;
    private SerachPresenter serachPresenter;
    private int index;

    @Override
    protected int layoutResID() {
        return R.layout.fragment;
    }

    @Override
    protected void initView(View view) {
        cv = view.findViewById(R.id.cv);
        rlv = view.findViewById(R.id.rlv);
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
    }

    @Override
    protected void initData() {
        if (index==0){
            //得到布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            //设置
            rlv.setLayoutManager(linearLayoutManager);

            //实例化p层
            serachPresenter = new SerachPresenter(this);
            //接口事件  cs监听
           cv.setOnSerachLisenter(new CustomView.OnSerachLisenter() {
                @Override
                public void onSearch(String goods) {
                    //联系P层
                    serachPresenter.related(goods);

                }
            });

        }else if (index==1){


        }else if (index==2){

        }else if (index==3){

        }else {

        }
    }
    //静态工厂方法
    public static MainFragment newInstance(int index){
        MainFragment mainFragment=new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    @Override
    public void Search(JSONArray result) {
        Toast.makeText(getContext(), result.length()+"", Toast.LENGTH_SHORT).show();
        //实例化适配器
        MyAdapter myAdapter = new MyAdapter(getContext(), result);
        rlv.setAdapter(myAdapter);
    }
}
