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

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.bw.com.yuekao01.fragment
 * @date 2019/3/7 11:28
 **/
public class Fragment03 extends Fragment {

    private RecyclerView rlv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment03,null,false);
        //找控件
        rlv = view.findViewById(R.id.rlv);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(layoutManager);




        return view;
    }
}
