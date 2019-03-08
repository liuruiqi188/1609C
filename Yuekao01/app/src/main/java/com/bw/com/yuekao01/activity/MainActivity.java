package com.bw.com.yuekao01.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bw.com.yuekao01.R;
import com.bw.com.yuekao01.fragment.Fragment01;
import com.bw.com.yuekao01.fragment.Fragment02;
import com.bw.com.yuekao01.fragment.Fragment03;
import com.bw.com.yuekao01.fragment.Fragment04;
import com.bw.com.yuekao01.fragment.Fragment05;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private Fragment05 fragment05;
    private Fragment04 fragment04;
    private Fragment03 fragment03;
    private Fragment02 fragment02;
    private Fragment01 fragment01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);

        //新建
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragment04 = new Fragment04();
        fragment05 = new Fragment05();

        //得到管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        //默认选择第一个
        rg.check(R.id.rb1);
        transaction.add(R.id.fl,fragment01);
        transaction.add(R.id.fl,fragment02);
        transaction.add(R.id.fl,fragment03);
        transaction.add(R.id.fl,fragment04);
        transaction.add(R.id.fl,fragment05);

        transaction.show(fragment01).hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment05);
        transaction.commit();
        //rg事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                    transaction1.show(fragment01).hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment05).commit();
                    break;
                    case R.id.rb2:
                        transaction1.show(fragment02).hide(fragment01).hide(fragment03).hide(fragment04).hide(fragment05).commit();
                        break;
                    case R.id.rb3:
                        transaction1.show(fragment03).hide(fragment02).hide(fragment01).hide(fragment04).hide(fragment05).commit();
                        break;
                    case R.id.rb4:
                        transaction1.show(fragment04).hide(fragment02).hide(fragment03).hide(fragment01).hide(fragment05).commit();
                        break;
                    case R.id.rb5:
                        transaction1.show(fragment05).hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment01).commit();
                        break;
                }
            }
        });




    }
}
