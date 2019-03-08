package com.example.test03.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.test03.DemoIntentService;
import com.example.test03.DemoPushService;
import com.example.test03.R;
import com.example.test03.fragment.Fragment01;
import com.example.test03.fragment.Fragment02;
import com.example.test03.fragment.Fragment03;
import com.example.test03.fragment.Fragment04;
import com.example.test03.fragment.Fragment05;
import com.igexin.sdk.PushManager;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private FragmentTransaction transaction;
    private Fragment05 fragment05;
    private Fragment04 fragment04;
    private Fragment03 fragment03;
    private Fragment02 fragment02;
    private Fragment01 fragment01;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);

        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        //
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();


        //找控件
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);

        //实例化fragment
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragment04 = new Fragment04();
        fragment05 = new Fragment05();

        //加入
        transaction.add(R.id.fl,fragment01);
        transaction.add(R.id.fl,fragment02);
        transaction.add(R.id.fl,fragment03);
        transaction.add(R.id.fl,fragment04);
        transaction.add(R.id.fl,fragment05);

        //默认设置
        rg.check(R.id.rb1);
        transaction.show(fragment01).hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment05);
        transaction.commit();

        //rg点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //重新获取tr
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

    //QQ回传图片数据接收
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }


}
