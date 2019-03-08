package com.example.liuruiqi0218.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.liuruiqi0218.R;
import com.example.liuruiqi0218.adapter.MyAdapter;
import com.example.liuruiqi0218.presenter.ShowPresenter;
import com.example.liuruiqi0218.view.ShowView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements ShowView {

    private RecyclerView rlv;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        rlv = findViewById(R.id.rlv);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rlv.setLayoutManager(linearLayoutManager);
        //实例化p层
        showPresenter = new ShowPresenter(this);

        //连接P层
        showPresenter.relate();

        // 关联   关联弱引用管理外部类对象
        showPresenter.attchView(this);


    }

    @Override
    public void Show(JSONArray data1) {
        //实例化适配器
        MyAdapter myAdapter = new MyAdapter(this,data1);
        //设置适配器
        rlv.setAdapter(myAdapter);
    }

    //解决mvp造成的内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity销毁之前p必须先销毁
        showPresenter.deathView();
        Log.i("zzz","销毁了");
    }
}
