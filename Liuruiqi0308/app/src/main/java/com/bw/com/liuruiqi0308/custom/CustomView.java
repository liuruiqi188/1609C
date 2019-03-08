package com.bw.com.liuruiqi0308.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.liuruiqi0308.R;

/**
 * @author liuruiqi
 * @fileName CustomView
 * @package com.bw.com.liuruiqi0308.custom
 * @date 2019/3/8 13:57
 **/
public class CustomView extends LinearLayout {

    private Button jia;
    private Button jian;
    private EditText num;
    private int a=1;


    //加的接口
    public interface onJiaLisenter{
        void jia(int p);
    }
    private onJiaLisenter jiaLisenter;

    public void setonJiaLisenter (onJiaLisenter jiaLisenter){
        this.jiaLisenter=jiaLisenter;
    }


    //减法的接口
    public interface onJianLisenter{
        void jian(int p);
    }
    private onJianLisenter jianLisenter;

    public void setonJianLisenter(onJianLisenter jianLisenter){
        this.jianLisenter=jianLisenter;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        //找到布局
        View view=LinearLayout.inflate(getContext(), R.layout.customview,null);
        //加入布局
        addView(view);

        //找控件
        jia = view.findViewById(R.id.bt_jia);
        jian = view.findViewById(R.id.bt_jian);
        num = view.findViewById(R.id.num);


        //加法的点击事件
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                a++;
                num.setText(a+"");
                //接口
                if (jiaLisenter!=null){
                    jiaLisenter.jia(a);
                }

            }
        });

        //减法的点击事件
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断视为小于1
                if (a<=1){
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                a--;
                num.setText(a+"");
                //接口
                if (jianLisenter!=null){
                    jianLisenter.jian(a);
                }
            }
        });




    }
}
