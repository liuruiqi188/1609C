package com.example.rikao0214;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwei.xlistview.XlistView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    private XlistView xlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    private void initView() {
        //找控件
        xlv = findViewById(R.id.xlv);
    }

    private void initData() {
        String path="http://120.27.23.105/product/getProducts?pscid=39&page=1";
        try {
            URL url = new URL(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
