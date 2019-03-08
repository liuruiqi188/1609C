package com.bw.com.shop2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.bw.com.shop2.CustomEx;
import com.bw.com.shop2.R;
import com.bw.com.shop2.adapter.MyAdapter;
import com.bw.com.shop2.bean.Datazhong;
import com.bw.com.shop2.presenter.ShowPresenter;
import com.bw.com.shop2.view.ShowView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShowView {


    private ShowPresenter showPresenter;
    private ExpandableListView et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        et = findViewById(R.id.ct);

        //实例化P
        showPresenter = new ShowPresenter(this);
        //联系
        showPresenter.relected();


    }

    @Override
    public void show(ArrayList<Datazhong> data) {
        //适配器
        MyAdapter myAdapter = new MyAdapter(this,data);
        et.setAdapter(myAdapter);
    }
}
