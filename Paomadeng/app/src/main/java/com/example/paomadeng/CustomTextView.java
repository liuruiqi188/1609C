package com.example.paomadeng;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @author liuruiqi
 * @fileName CustomTextView
 * @package com.example.paomadeng
 * @date 2019/2/20 20:22
 **/
public class CustomTextView extends android.support.v7.widget.AppCompatTextView {


    //定义一个x轴的起始值
    private float textX=0;
    private Thread thread=null;
    private boolean runing=true;


    private Paint paint;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔
        paint = new Paint();
        paint.setTextSize(30);
        //设置文字
        canvas.drawText("哈哈哈哈哈",textX,50,paint);
        if (thread==null){
            thread=new MyThread();
            thread.start();
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (runing){
                textX=textX+7;
                if (textX>getWidth()){
                    //获取文字长度
                    textX=0-paint.measureText("哈哈哈哈");
                }
                //重新设定文字
                postInvalidate();
                try {
                    //让子线程睡一会
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        runing=false;
        super.onDetachedFromWindow();
    }
}
