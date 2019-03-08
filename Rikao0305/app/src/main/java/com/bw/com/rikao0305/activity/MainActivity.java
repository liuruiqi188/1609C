package com.bw.com.rikao0305.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bw.com.rikao0305.R;
import com.bw.com.rikao0305.presenter.ShowPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xrlv;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        xrlv = findViewById(R.id.xrlv);
        //布局布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        xrlv.setLayoutManager(layoutManager);
        //实例化
        showPresenter = new ShowPresenter();
        //联系M层
        showPresenter.relrcted();

    }
}
