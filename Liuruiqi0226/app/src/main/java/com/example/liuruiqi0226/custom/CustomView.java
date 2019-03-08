package com.example.liuruiqi0226.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuruiqi0226.R;

/**
 * @author liuruiqi
 * @fileName CustomView
 * @package com.example.liuruiqi0226.custom
 * @date 2019/2/26 10:07
 **/
public class CustomView extends LinearLayout {

    private TextView name;
    private ImageView img;
    private TextView phone;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        //初始
        initView();
    }



    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        View view=inflate(getContext(), R.layout.custom,null);
        addView(view);

        //中空间呢
        name = view.findViewById(R.id.tv_name);
        img = view.findViewById(R.id.img);
        phone = view.findViewById(R.id.tv_phone);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一种实现方式
                ObjectAnimator.ofFloat(v,"rotationX",0.0f,360.0f)
                        .setDuration(500)
                        .start();
            }
        });






    }
    //穿号码
    public void sendNum(String num){
        phone.setText(num);
    }



}
