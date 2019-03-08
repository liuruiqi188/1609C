package com.bw.com.yuekao01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.com.yuekao01.R;
import com.bw.com.yuekao01.adapter.LeftAdapter;
import com.bw.com.yuekao01.adapter.RightAdapter;
import com.bw.com.yuekao01.presenter.LeftPresenter;
import com.bw.com.yuekao01.presenter.RightPresenter;
import com.bw.com.yuekao01.view.LeftView;
import com.bw.com.yuekao01.view.RightView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.bw.com.yuekao01.fragment
 * @date 2019/3/7 11:28
 **/
public class Fragment02 extends Fragment implements LeftView ,RightView {

    private RecyclerView rlv1;
    private RecyclerView rlv2;
    private LeftPresenter leftPresenter;
    private RightPresenter rightPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment02,null,false);

        //找控件
        rlv1 = view.findViewById(R.id.rlv1);
        rlv2 = view.findViewById(R.id.rlv2);

        //得到布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv1.setLayoutManager(layoutManager);

        //得到布局管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rlv2.setLayoutManager(layoutManager1);
        //实例化P
        leftPresenter = new LeftPresenter(this);

        leftPresenter.relected();

        //实例化P
        rightPresenter = new RightPresenter(this);


        return view;
    }

    @Override
    public void left(JSONArray data) {
        //适配器
        LeftAdapter leftAdapter = new LeftAdapter(getContext(),data);
        rlv1.setAdapter(leftAdapter);

        //适配器的点击事件
        leftAdapter.setOnRightLisenter(new LeftAdapter.OnRightLisenter() {
            @Override
            public void right(String a) {
                //取得跟右边的联系
                rightPresenter.relectedd(a);
            }
        });


    }

    @Override
    public void right(JSONArray data) {
        //适配器
        RightAdapter rightAdapter = new RightAdapter(getContext(),data);
        rlv2.setAdapter(rightAdapter);

    }
}
