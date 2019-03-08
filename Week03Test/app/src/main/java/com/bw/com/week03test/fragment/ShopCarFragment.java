package com.bw.com.week03test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.com.week03test.R;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.bw.com.week03test.fragment
 * @date 2019/3/1 14:14
 **/
public class ShopCarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shopcarfragment,null,false);



        return view;
    }
}
