package com.example.xiangmu.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangmu.R;

/**
 * @author liuruiqi
 * @fileName CustomShopCar
 * @package com.example.xiangmu.custom
 * @date 2019/3/5 14:08
 **/
public class CustomShopCar extends LinearLayout {

    private Button add;
    private Button jian;
    private EditText num;
    int q=1;

    public CustomShopCar(Context context) {
        super(context);
    }

    public CustomShopCar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    //加法的接口回调
    public interface onAddLisenter{
        void onadd(int c);
    }
    private onAddLisenter addLisenter;
    public void setonAddLisenter(onAddLisenter addLisenter){
        this.addLisenter=addLisenter;
    }

    //减法的接口回调
    public interface onJianLisenter{
        void onjian(int p);
    }

    private onJianLisenter jianLisenter;

    public void setonJianLisenter(onJianLisenter jianLisenter){
        this.jianLisenter=jianLisenter;
    }

    private void initView() {
        //加载布局
        View view=LinearLayout.inflate(getContext(), R.layout.item02,null);
        addView(view);
        //找控件
        add = view.findViewById(R.id.add);
        jian = view.findViewById(R.id.jian);
        num = view.findViewById(R.id.num);

        //加的点击事件 接口回调
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               q++;
               num.setText(q+"");
                //接口回传
                if (addLisenter!=null){
                   addLisenter.onadd(q);
                }


            }
        });

        //减法的接口回调
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q<=1){
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                }else {
                    q--;
                    num.setText(q+"");
                    if (jianLisenter!=null){
                        jianLisenter.onjian(q);
                    }
                }
            }
        });

    }

    public CustomShopCar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
