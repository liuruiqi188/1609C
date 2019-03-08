package com.bw.com.liuruiqi0308.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.com.liuruiqi0308.R;

/**
 * @author liuruiqi
 * @fileName ShowFragment
 * @package com.bw.com.liuruiqi0308.fragment
 * @date 2019/3/8 13:39
 **/
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine,null,false);



        return view;
    }
}
