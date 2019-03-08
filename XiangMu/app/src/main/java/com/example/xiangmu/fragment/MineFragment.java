package com.example.xiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiangmu.R;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.example.xiangmu.fragment
 * @date 2019/3/4 15:59
 **/
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.mine_fragment,null,false);



        return view;
    }
}
