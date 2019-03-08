package com.example.rikao0225;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
         findViewById(R.id.qqlogin).setOnClickListener((View.OnClickListener) this);
         findViewById(R.id.qqshare).setOnClickListener(this);
        img = findViewById(R.id.img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * qq登录
             */
            case R.id.qqlogin:
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                //登录授权监听
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        // 登录信息集合
                        // Log.i("Tag",map+"");
                        String s = map.get("profile_image_url");
                        Glide.with(MainActivity.this).load(s).into(img);
                    }
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
            /**
             * qq分享
             */
            case R.id.qqshare:
                //分享监听
                UMShareListener umShareListener = new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Log.d("plat", "platform" + platform);
                        Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(MainActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                        Log.i("xxx", "onError: " + t);
                    }
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(MainActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
                    }
                };
                //友盟图片
                UMImage umImage = new UMImage(MainActivity.this, R.mipmap.ic_launcher);
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(umImage)//分享图片
                        .setCallback(umShareListener)//回调监听器
                        .share();
                break;
        }

    }
    //必须重写
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
