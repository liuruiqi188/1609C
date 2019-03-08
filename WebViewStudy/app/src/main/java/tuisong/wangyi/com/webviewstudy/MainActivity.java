package tuisong.wangyi.com.webviewstudy;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView web;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        web = findViewById(R.id.web);
        //android调用网页
        WebSettings settings = web.getSettings();
        //设置
        settings.setJavaScriptEnabled(true);
        //加载网页
        web.loadUrl("file:///android_asset/info.html");

        //webview 长按事件
        web.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                web.loadUrl("javascript:test1()");

                return false;
            }
        });

        //调用android协议
        web.addJavascriptInterface(this,"android");


    }
    @JavascriptInterface
    public void jump(){
        //跳转
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
