package com.bw.com.liuruiqi0304.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.com.liuruiqi0304.DemoIntentService;
import com.bw.com.liuruiqi0304.DemoPushService;
import com.bw.com.liuruiqi0304.R;
import com.igexin.sdk.PushManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class ShowActivity extends AppCompatActivity {

    private ImageView img;
    private TextView name;
    private Button bug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);

        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        //找控件
        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        bug = findViewById(R.id.bug);

        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowActivity.this,2%0, Toast.LENGTH_SHORT).show();
            }
        });

        //点击图片直接跳转第三方登录
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(ShowActivity.this);
                umShareAPI.getPlatformInfo(ShowActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {

                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                        Log.i("Tag",map+"");
                        String s = map.get("profile_image_url");
                        String screen_name = map.get("screen_name");
                        Glide.with(ShowActivity.this).load(s).into(img);
                        Toast.makeText(ShowActivity.this, "登录成功"+screen_name, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }

                });


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(ShowActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
