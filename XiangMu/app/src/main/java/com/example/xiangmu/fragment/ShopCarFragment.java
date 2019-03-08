package com.example.xiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.xiangmu.R;
import com.example.xiangmu.adapter.ShopCarAdapter;
import com.example.xiangmu.presenter.ShopCarPresenter;
import com.example.xiangmu.view.ShopCarView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.example.xiangmu.fragment
 * @date 2019/3/4 15:59
 **/
public class ShopCarFragment extends Fragment implements ShopCarView {

    private XRecyclerView xrlv;
    private LinearLayoutManager layoutManager;
    private ShopCarPresenter shopCarPresenter;
    private CheckBox ck;
    private ShopCarAdapter shopCarAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.shopcarfragment,null,false);

        //找控件
        xrlv = view.findViewById(R.id.xrlv);
        ck = view.findViewById(R.id.ck);
//获得布局管理器
        layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        xrlv.setLayoutManager(layoutManager);
//实例化P
        shopCarPresenter = new ShopCarPresenter(this);
        shopCarPresenter.relected();

        //选择框点击事件
       ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ck.isChecked();
                if (checked){
                    shopCarAdapter.notifCheckData(true);
                }else {
                    shopCarAdapter.notifCheckData(false);
                }
            }
        });

        return view;
    }

    @Override
    public void shopcar(JSONArray result) {
        //适配器
        shopCarAdapter = new ShopCarAdapter(getContext(),result);
        //设置适配器
        xrlv.setAdapter(shopCarAdapter);

       //适配器调用接口
        shopCarAdapter.setonQuanCheckLisenter(new ShopCarAdapter.onQuanCheckLisenter() {
            @Override
            public void quancheck(boolean flag) {
                ck.setChecked(flag);
            }
        });

    }
}
