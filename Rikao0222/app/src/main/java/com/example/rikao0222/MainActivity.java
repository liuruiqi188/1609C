package com.example.rikao0222;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private Button shuiping;
    private Button chuizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        img = findViewById(R.id.img);
        shuiping = findViewById(R.id.shuiping);
        chuizhi = findViewById(R.id.chuizhi);

    }
}
