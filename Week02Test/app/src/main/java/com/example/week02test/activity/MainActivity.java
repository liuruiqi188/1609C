package com.example.week02test.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.week02test.FragmentAdapter;
import com.example.week02test.R;
import com.example.week02test.fragment.Fragment01;
import com.example.week02test.fragment.Fragment02;
import com.example.week02test.fragment.Fragment03;
import com.example.week02test.fragment.Fragment04;
import com.example.week02test.fragment.Fragment05;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ViewPager vp;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        rg = findViewById(R.id.rg);
        vp = findViewById(R.id.vp);

        //集合
        list = new ArrayList<Fragment>();
        //实例化fragment
        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();
        Fragment04 fragment04 = new Fragment04();
        Fragment05 fragment05 = new Fragment05();
        //添加进去
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
        list.add(fragment04);
        list.add(fragment05);

        //适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this,list);
        vp.setAdapter(fragmentAdapter);

        //初始化
        rg.check(R.id.rb1);

        //rg点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        vp.setCurrentItem(3);
                        break;
                    case R.id.rb5:
                        vp.setCurrentItem(4);
                        break;
                }
            }
        });

        //viewpager事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                  rg.check(rg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
}
