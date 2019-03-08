package com.example.xiangmu.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.xiangmu.R;
import com.example.xiangmu.fragment.BillFragment;
import com.example.xiangmu.fragment.CircleFragment;
import com.example.xiangmu.fragment.FirstFragment;
import com.example.xiangmu.fragment.MineFragment;
import com.example.xiangmu.fragment.ShopCarFragment;

public class ShowActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private MineFragment mineFragment;
    private BillFragment billFragment;
    private ShopCarFragment shopCarFragment;
    private CircleFragment circleFragment;
    private FirstFragment firstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //找控件
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
        //实例化fragmebt
        firstFragment = new FirstFragment();
        circleFragment = new CircleFragment();
        shopCarFragment = new ShopCarFragment();
        billFragment = new BillFragment();
        mineFragment = new MineFragment();

        //管局器
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl,firstFragment);
        transaction.add(R.id.fl,circleFragment);
        transaction.add(R.id.fl,shopCarFragment);
        transaction.add(R.id.fl,billFragment);
        transaction.add(R.id.fl,mineFragment);

        //默认选中
        rg.check(R.id.rb1);
        transaction.show(firstFragment).hide(circleFragment).hide(shopCarFragment).hide(billFragment).hide(mineFragment).commit();


        //rg点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction1.show(firstFragment).hide(circleFragment).hide(shopCarFragment).hide(billFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb2:
                        transaction1.show(circleFragment).hide(firstFragment).hide(shopCarFragment).hide(billFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb3:
                        transaction1.show(shopCarFragment).hide(circleFragment).hide(firstFragment).hide(billFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb4:
                        transaction1.show(billFragment).hide(circleFragment).hide(shopCarFragment).hide(firstFragment).hide(mineFragment).commit();
                        break;
                    case R.id.rb5:
                        transaction1.show(mineFragment).hide(circleFragment).hide(shopCarFragment).hide(billFragment).hide(firstFragment).commit();
                        break;
                }
            }
        });


    }
}
