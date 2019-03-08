package com.bw.com.erjilist.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.erjilist.R;

/**
 * @author liuruiqi
 * @fileName CustomJisuanqi
 * @package com.bw.com.erjilist.custom
 * @date 2019/3/6 13:54
 **/
public class CustomJisuanqi extends LinearLayout {

    //加的接口
    public interface onJiaLisenter{
        void onjia(int q);
    }
    private onJiaLisenter jiaLisenter;
    public void setonJiaLisenter(onJiaLisenter jiaLisenter){
        this.jiaLisenter=jiaLisenter;
    }

    //减法的接口
    public interface onJianLisenter{
        void onjian(int q);
    }
    private onJianLisenter jianLisenter;

    public void setonJianLisenter(onJianLisenter jianLisenter){
        this.jianLisenter=jianLisenter;
    }


    private Button jian;
    private Button jia;
    private EditText num;
    private int a=1;

    public CustomJisuanqi(Context context) {
        super(context);
    }

    public CustomJisuanqi(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view=LinearLayout.inflate(getContext(), R.layout.customjisuanqi,null);
        addView(view);

        //找控件
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        num = view.findViewById(R.id.num);

        //加法点击事件
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                a++;
                num.setText(a+"");
                //接口回调
                if (jiaLisenter!=null){
                    jiaLisenter.onjia(a);
                }
            }
        });
        //减的点击事件
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                a--;
                if (a<=1){
                    Toast.makeText(getContext(), "商品数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                num.setText(a+"");
                //接口回调
                if (jianLisenter!=null){
                    jianLisenter.onjian(a);
                }
            }
        });

    }

    public CustomJisuanqi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
