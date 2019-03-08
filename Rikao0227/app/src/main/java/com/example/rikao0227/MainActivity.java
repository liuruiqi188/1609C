package com.example.rikao0227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化
        CrashHandler.getInstance().init(this);

        //创建一个错误
        String a=null;
        String b=a.toString();
        Toast.makeText(this, "异常是"+b, Toast.LENGTH_SHORT).show();
       Log.i("xxx",b);


    }
}
