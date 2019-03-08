package com.example.zhoukao02.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.zhoukao02.R;
import com.example.zhoukao02.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment preFragment=null;

    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件

        rg = findViewById(R.id.rg);
        //初始化数据
        initData();


    }

    private void initData() {
        //默认点击第一个fragment
        rg.check(R.id.rb1);
        //默认添加一个fragment
        newFragment(MainFragment.newInstance(0));
        //通过点击radiobutton 创建新的fragment
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int i = group.indexOfChild(group.findViewById(checkedId));
                newFragment(MainFragment.newInstance(i));
            }
        });
    }

    //动态创建Fragment
    private void newFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment.isAdded()){
            transaction.show(fragment).commit();
        }else {
            transaction.add(R.id.fragment,fragment).commit();
        }
        if (preFragment!=null){
            transaction.hide(fragment);
        }
        preFragment=fragment;
    }
}
