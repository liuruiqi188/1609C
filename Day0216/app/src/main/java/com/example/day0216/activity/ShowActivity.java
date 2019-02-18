package com.example.day0216.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.day0216.FirstAdapter;
import com.example.day0216.R;
import com.example.day0216.presenter.ShowPresenter;
import com.example.day0216.view.ShowView;

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
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置管理器
        rlv.setLayoutManager(linearLayoutManager);

        //实例化P层
        showPresenter = new ShowPresenter(this);

        //联系p层
        showPresenter.related();


    }

    @Override
    public void show(JSONArray result) {
        //适配器实例化
        FirstAdapter firstAdapter = new FirstAdapter(this, result);
        rlv.setAdapter(firstAdapter);
        firstAdapter.setOnItemclickLisenter(new FirstAdapter.OnItemclickLisenter() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(ShowActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int i) {
                Toast.makeText(ShowActivity.this, "这是长按", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
