package com.example.rikao0221;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author liuruiqi
 * @fileName CustomViewGroup
 * @package com.example.rikao0221
 * @date 2019/2/21 8:46
 **/
public class CustomViewGroup extends LinearLayout {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }



    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context, AttributeSet attrs) {
        initView();
        initData();

    }

    private void initData() {
    }

    private void initView() {
        
    }

}
