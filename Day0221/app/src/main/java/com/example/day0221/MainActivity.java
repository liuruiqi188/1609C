package com.example.day0221;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        iv = findViewById(R.id.iv);
        //图片iv点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一种实现方式
                ObjectAnimator.ofFloat(v,"rotationX",0.0f,360.0f)
                        .setDuration(500)
                        .start();
            }
        });


    }
}
