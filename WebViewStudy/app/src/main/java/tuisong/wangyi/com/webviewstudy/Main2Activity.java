package tuisong.wangyi.com.webviewstudy;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private WebView web;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //找控件
        web = findViewById(R.id.web);
        //获得js和android的两方权限
        web.getSettings().setJavaScriptEnabled(true);
        //加载网页
        web.loadUrl("file:///android_asset/infos.html");

        //实现网页的功能 弹出对话框
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //必须改成false ，默认或者true都是不谈对话框 ，false是弹出对话框
                return false;
            }
        });



        //协议
        web.addJavascriptInterface(this,"android");


    }
    //注解
    @JavascriptInterface
    public void showUserInfo(String name,String pwd){
        Toast.makeText(this, "用户名是"+name+"密码是"+pwd, Toast.LENGTH_SHORT).show();
    }


}
