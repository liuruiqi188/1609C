package com.example.rikao0220.zidiyiview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author liuruiqi
 * @fileName PointView
 * @package com.example.rikao0220.zidiyiview
 * @date 2019/2/20 15:08
 **/
public class PointView extends View {
    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //对于画笔
    }
}
