package com.example.zuoye0214.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zuoye0214.R;
import com.example.zuoye0214.adapter.FirstAdapter;
import com.example.zuoye0214.model.ShowModel;
import com.example.zuoye0214.presenter.ShowPresenter;
import com.example.zuoye0214.view.ShowView;

import org.json.JSONArray;

public class ShowActivity extends AppCompatActivity implements ShowView {

    private RecyclerView rlv;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //找控件
        rlv = findViewById(R.id.rlv);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rlv.setLayoutManager(linearLayoutManager);
        //创建presents
        showPresenter = new ShowPresenter(this);
        //让p层关联Activity
        showPresenter.related();

    }


    @Override
    public void show(JSONArray result) {

        //把数据给适配器
        FirstAdapter firstAdapter = new FirstAdapter(this,result);
        //rlv设置适配器
        rlv.setAdapter(firstAdapter);


    }
}
