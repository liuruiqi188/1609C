package com.example.test03.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test03.R;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.test03.fragment
 * @date 2019/2/27 20:45
 **/
public class Fragment03 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag03,null,false);



        return view;
    }
}
