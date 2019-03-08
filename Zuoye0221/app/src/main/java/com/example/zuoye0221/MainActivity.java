package com.example.zuoye0221;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.zuoye0221.adapter.MyAdapter;
import com.example.zuoye0221.persenter.SerchPresenter;
import com.example.zuoye0221.view.SerachView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements SerachView {

    private CustomSearch cs;
    private GridView rv;
    private SerchPresenter serchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        cs = findViewById(R.id.cs);
        rv = findViewById(R.id.gv);
        //实例化P层
        serchPresenter = new SerchPresenter(this);

        //给cs设置监听事件
        cs.setOnSerchLisenter(new CustomSearch.OnSerchLisenter() {




            @Override
            public void onSerch(String goods) {


                //关联P层
                serchPresenter.related(goods);
            }
        });
    }

    @Override
    public void serach(JSONArray result) {
        Toast.makeText(this, result.length()+"", Toast.LENGTH_SHORT).show();
        //新建适配器
        MyAdapter myAdapter = new MyAdapter(this,result);
        rv.setAdapter(myAdapter);
    }
}
