package com.example.week02test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week02test.R;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.week02test.fragment
 * @date 2019/2/23 9:27
 **/
public class Fragment04 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment04,null,false);




        return view;
    }
}
