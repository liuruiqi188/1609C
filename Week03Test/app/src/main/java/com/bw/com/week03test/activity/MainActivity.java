package com.bw.com.week03test.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bw.com.week03test.R;
import com.bw.com.week03test.fragment.FindFragment;
import com.bw.com.week03test.fragment.FirstFragment;
import com.bw.com.week03test.fragment.MyFragment;
import com.bw.com.week03test.fragment.ShopCarFragment;
import com.bw.com.week03test.fragment.StyleFragment;
import com.bw.com.week03test.service.DemoIntentService;
import com.bw.com.week03test.service.DemoPushService;
import com.igexin.sdk.PushManager;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private FirstFragment firstFragment;
    private StyleFragment styleFragment;
    private FindFragment findFragment;
    private ShopCarFragment shopCarFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
        //
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        //新建fragment
        firstFragment = new FirstFragment();
        styleFragment = new StyleFragment();
        findFragment = new FindFragment();
        shopCarFragment = new ShopCarFragment();
        myFragment = new MyFragment();
        //添加
        transaction.add(R.id.fl,firstFragment);
        transaction.add(R.id.fl,styleFragment);
        transaction.add(R.id.fl,findFragment);
        transaction.add(R.id.fl,shopCarFragment);
        transaction.add(R.id.fl,myFragment);

        //默认展示第二个界面
        transaction.show(styleFragment).hide(firstFragment).hide(findFragment).hide(shopCarFragment).hide(myFragment).commit();
        //默认选中第二个
        rg.check(R.id.rb2);


        //rg点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //重新得到transaction
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction1.show(firstFragment).hide(styleFragment).hide(findFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb2:
                        transaction1.show(styleFragment).hide(firstFragment).hide(findFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb3:
                        transaction1.show(findFragment).hide(styleFragment).hide(firstFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb4:
                        transaction1.show(shopCarFragment).hide(styleFragment).hide(findFragment).hide(firstFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb5:
                        transaction1.show(myFragment).hide(styleFragment).hide(findFragment).hide(shopCarFragment).hide(firstFragment).commit();
                        break;
                }
            }
        });

        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);

        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);


    }
    //回传qq头像

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
