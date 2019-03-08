package com.bw.com.yuekao01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class Fragment01 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment01,null,false);



        return view;
    }
}
