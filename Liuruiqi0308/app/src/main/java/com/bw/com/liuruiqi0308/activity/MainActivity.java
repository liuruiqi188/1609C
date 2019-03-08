package com.bw.com.liuruiqi0308.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bw.com.liuruiqi0308.R;
import com.bw.com.liuruiqi0308.fragment.MineFragment;
import com.bw.com.liuruiqi0308.fragment.ShopCarFragment;
import com.bw.com.liuruiqi0308.fragment.ShowFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private MineFragment mineFragment;
    private ShopCarFragment shopCarFragment;
    private ShowFragment showFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);

        //实例化fragment
        showFragment = new ShowFragment();
        shopCarFragment = new ShopCarFragment();
        mineFragment = new MineFragment();

        //管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        //添加
        transaction.add(R.id.fl,showFragment);
        transaction.add(R.id.fl,shopCarFragment);
        transaction.add(R.id.fl,mineFragment);

        //默认选择第一个
        rg.check(R.id.rb1);
        transaction.show(showFragment).hide(shopCarFragment).hide(mineFragment).commit();





        //rg点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //重新得到管理者
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction1.show(showFragment).hide(shopCarFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb2:
                        transaction1.show(shopCarFragment).hide(showFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb3:
                        transaction1.show(mineFragment).hide(shopCarFragment).hide(showFragment).commit();
                        break;
                }
            }
        });

    }
}
