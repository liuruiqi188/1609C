package com.bw.com.gouwuche.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.bw.com.gouwuche.R;
import com.bw.com.gouwuche.adapter.ShowAdapter;
import com.bw.com.gouwuche.presenter.ShowPresenter;
import com.bw.com.gouwuche.view.ShowView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowActivity extends AppCompatActivity implements ShowView {

    private XRecyclerView xrlv;
    private LinearLayoutManager layoutManager;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //找控件
        xrlv = findViewById(R.id.xrlv);
        //获得布局管理器
        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        xrlv.setLayoutManager(layoutManager);
        //实例化P层
        showPresenter = new ShowPresenter(this);
        //联系
        showPresenter.relected();
    }

    @Override
    public void show(JSONArray result) {
        Toast.makeText(this, result.length()+"", Toast.LENGTH_SHORT).show();
        //适配器
        ShowAdapter showAdapter = new ShowAdapter(this,result);
        //设置适配器
        xrlv.setAdapter(showAdapter);

    }
}
