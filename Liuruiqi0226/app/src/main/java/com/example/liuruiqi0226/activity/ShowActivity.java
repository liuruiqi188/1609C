package com.example.liuruiqi0226.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.liuruiqi0226.R;
import com.example.liuruiqi0226.custom.CustomView;

public class ShowActivity extends AppCompatActivity {

    private CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        cv = findViewById(R.id.cv);
        Intent intent = getIntent();
        String num = intent.getStringExtra("num");
        cv.sendNum(num);
    }
}
