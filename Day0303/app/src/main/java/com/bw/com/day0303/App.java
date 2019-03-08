package com.bw.com.day0303;

import android.app.Application;

/**
 * @author liuruiqi
 * @fileName App
 * @package com.bw.com.day0303
 * @date 2019/3/3 20:49
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
