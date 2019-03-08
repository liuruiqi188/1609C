package tuisong.wangyi.com.tuisong;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启信鸽的日志输出，线上版本不建议调用
        XGPushConfig.enableDebug(this, true);

        //注册数据更新监听器
        MessageReceiver receiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wangyi.tuisong.UPDATE_LISTVIEW");
        registerReceiver(receiver, intentFilter);
        //注册获取token
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Log.i("xxx",o.toString());
                //通过网络请求给后台

            }

            @Override
            public void onFail(Object o, int i, String s) {

            }
        });

    }


}
