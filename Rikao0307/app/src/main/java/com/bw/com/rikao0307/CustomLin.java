package com.bw.com.rikao0307;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author liuruiqi
 * @fileName CustomLin
 * @package com.bw.com.rikao0307
 * @date 2019/3/7 8:42
 **/
public class CustomLin extends LinearLayout {

    private Button back;

    public CustomLin(Context context) {
        super(context);
    }

    public CustomLin(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view=LinearLayout.inflate(getContext(),R.layout.customlin,null);
        addView(view);
        
        //找控件
        back = view.findViewById(R.id.back);
        
        //点击事件
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击返回，可结束此页面。", Toast.LENGTH_SHORT).show();
            }
        });
        
        
    }

    public CustomLin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
