package com.bw.com.shop2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * @author liuruiqi
 * @fileName CustomEx
 * @package com.bw.com.shop2
 * @date 2019/3/6 15:38
 **/
public class CustomEx extends ExpandableListView {


    public CustomEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i =MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, i);
    }
}
