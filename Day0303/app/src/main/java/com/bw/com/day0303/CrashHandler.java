package com.bw.com.day0303;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liuruiqi
 * @fileName CrashHandler
 * @package com.bw.com.day0303
 * @date 2019/3/3 20:35
 **/
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler = null;
    private Context mcontext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private CrashHandler(){

    };
    public static CrashHandler getInstance(){
        if (crashHandler==null){
            synchronized (CrashHandler.class){
                crashHandler = new CrashHandler();
            }
        }
        return crashHandler;
    }

    public void init(Context context) {
        mcontext = context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (hanlderException(e) && crashHandler != null){
            crashHandler.uncaughtException(t, e);
        }else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }



    private boolean hanlderException(final Throwable e){
        if (e == null) {
            return false;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mcontext, "程序开小差了。。。", Toast.LENGTH_SHORT).show();

                String sdFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash.txt";
                File file = new File(sdFile);

                    try {
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(e.toString().getBytes());
                        fileOutputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Looper.loop();

            }
        }).start();
        return true;
    }

}
