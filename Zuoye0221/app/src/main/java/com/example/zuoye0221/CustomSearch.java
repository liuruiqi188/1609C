package com.example.zuoye0221;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author liuruiqi
 * @fileName CustomSearch
 * @package com.example.zuoye0221
 * @date 2019/2/21 15:42
 **/
public class CustomSearch extends LinearLayout {

    private EditText et;
    private Button qd;

    //创建接口
    public interface OnSerchLisenter{
        void onSerch(String goods);
    }
    //声明借口
    public OnSerchLisenter serchLisenter;

    //set方法
    public void setOnSerchLisenter(OnSerchLisenter serchLisenter){
        this.serchLisenter=serchLisenter;
    }

    public CustomSearch(Context context) {
        super(context);
    }

    public CustomSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomSearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initView() {
        //找见布局 加入布局
        View view = inflate(getContext(), R.layout.yinyong, null);
        addView(view);
        //找控件
        et = findViewById(R.id.ed);
        qd = findViewById(R.id.bt_qd);

        //确定按钮点击事件
        qd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String goods = et.getText().toString();
                //判断是否输入的事空值 如果是 吐司一下
                if (goods.equals("")){
                    Toast.makeText(getContext(), "您什么都没有输入，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                //接口回调
                serchLisenter.onSerch(goods);

            }
        });

    }


}
