package com.example.xiangmu.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xiangmu.R;

/**
 * @author liuruiqi
 * @fileName CustomView
 * @package com.example.xiangmu.custom
 * @date 2019/3/5 11:32
 **/
public class CustomView extends LinearLayout {

    private EditText goods;
    private Button search;
    private Button erweima;
    //-----------------------------------------------------搜索的接口----------------------------------------------------------------------------
    public interface onSearchLisenter{
        void OnSearch(String goods);
    }
    public onSearchLisenter searchLisenter;
    public void setonSearchLisenter(onSearchLisenter searchLisenter){
        this.searchLisenter=searchLisenter;
    }
    //------------------------------------------------------二维码接口=-----------------------------------------------------------------------------
    public interface onErweimaLisenter{
        void OnErweima();
    }
    public onErweimaLisenter erweimaLisenter;
    public void setonErweimaLisenter(onErweimaLisenter erweimaLisenter){
        this.erweimaLisenter=erweimaLisenter;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
         //加载视图
        View view=LinearLayout.inflate(getContext(), R.layout.customview,null);
        addView(view);

        //找控件
        goods = view.findViewById(R.id.goods);
        search = view.findViewById(R.id.search);
        erweima = view.findViewById(R.id.erweima);


        //搜索的点击事件
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String goods = CustomView.this.goods.getText().toString();
                //非空判断
                if (goods.equals("")){
                    Toast.makeText(getContext(), "不能输入空值呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                searchLisenter.OnSearch(goods);
            }
        });

        //二维码扫面的点击事件
        erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                erweimaLisenter.OnErweima();
            }
        });

    }

    public CustomView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
