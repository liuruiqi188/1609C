package com.bw.com.shop2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author liuruiqi
 * @fileName CustomLin
 * @package com.bw.com.shop2
 * @date 2019/3/6 19:37
 **/
public class CustomLin extends LinearLayout {

    private int a=1;
    private Button jia;
    private Button jian;
    private EditText num;

    //加法接口
    public interface onJiaLisenter{
        void onjia(int q);
    }
    private onJiaLisenter jiaLisenter;

    public void setonJiaLisenter(onJiaLisenter jiaLisenter){
        this.jiaLisenter=jiaLisenter;
    }

    //减法借口
    public interface onJianLisenter{
        void onJian(int q);
    }
    private onJianLisenter jianLisenter;
    public void setonJianLisenter(onJianLisenter jianLisenter){
        this.jianLisenter=jianLisenter;
    }


    public CustomLin(Context context) {
        super(context);
    }

    public CustomLin(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view=LinearLayout.inflate(getContext(),R.layout.customlin,null);
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
                //接口
                if (jiaLisenter!=null){
                    jiaLisenter.onjia(a);
                }
            }
        });

        //减法
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a<=1){
                    Toast.makeText(getContext(), "数量不能小于一", Toast.LENGTH_SHORT).show();
                    return;
                }
                a--;
                num.setText(a+"");
                //接口
                if (jianLisenter!=null){
                    jianLisenter.onJian(a);
                }
            }
        });


    }

    public CustomLin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
