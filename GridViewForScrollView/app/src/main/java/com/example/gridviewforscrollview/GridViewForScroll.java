package com.example.gridviewforscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author liuruiqi
 * @fileName GridViewForScroll
 * @package com.example.gridviewforscrollview
 * @date 2019/2/21 14:49
 **/
public class GridViewForScroll extends GridView {
    public GridViewForScroll(Context context) {
        super(context);
    }

    public GridViewForScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //重写onMeasure方法

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newHeightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);

    }
}
