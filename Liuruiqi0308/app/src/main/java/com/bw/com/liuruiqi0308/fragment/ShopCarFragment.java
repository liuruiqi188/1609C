package com.bw.com.liuruiqi0308.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.com.liuruiqi0308.R;
import com.bw.com.liuruiqi0308.adapter.FirstAdapter;
import com.bw.com.liuruiqi0308.presenter.ShopcarPresenter;
import com.bw.com.liuruiqi0308.view.ShopcarView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName ShowFragment
 * @package com.bw.com.liuruiqi0308.fragment
 * @date 2019/3/8 13:39
 **/
public class ShopCarFragment extends Fragment implements ShopcarView {

    private RecyclerView rlv;
    private ShopcarPresenter shopcarPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shopcar,null,false);

        //找控件
        rlv = view.findViewById(R.id.rlv);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(layoutManager);


        //实例化P层
        shopcarPresenter = new ShopcarPresenter(this);
        //联系P层
        shopcarPresenter.relected();

        shopcarPresenter.attachView(this);



        return view;
    }

    @Override
    public void shopcar(JSONArray data) {
        Log.i("rrr",data.length()+"");
        //适配器
        FirstAdapter firstAdapter = new FirstAdapter(getContext(),data);
        //设置适配器
       rlv.setAdapter(firstAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shopcarPresenter.deathView();
    }
}
