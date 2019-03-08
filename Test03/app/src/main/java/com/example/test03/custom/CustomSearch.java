package com.example.test03.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.test03.R;

/**
 * @author liuruiqi
 * @fileName CustomSearch
 * @package com.example.test03.custom
 * @date 2019/2/27 19:52
 **/
public class CustomSearch extends LinearLayout {

    private EditText goods;
    private Button search;
    private Button erweima;

    //搜索接口
    public interface OnSearchLisenter{
        void onsearch(String goods);
    }
    public OnSearchLisenter searchLisenter;
    public void setOnSearchLisenter(OnSearchLisenter searchLisenter){
        this.searchLisenter=searchLisenter;
    }

    //二维码接口
    public interface onErweimaLisenter{
        void Erweima();
    }
    public onErweimaLisenter erweimaLisenter;
    public void setonErweimaLisenter(onErweimaLisenter erweimaLisenter){
        this.erweimaLisenter=erweimaLisenter;
    }


    public CustomSearch(Context context) {
        super(context);
    }

    public CustomSearch(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public CustomSearch(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View view=inflate(getContext(), R.layout.custom,null);
        addView(view);

        //找控件
        goods = view.findViewById(R.id.et_goods);
        search = view.findViewById(R.id.search);
        erweima = view.findViewById(R.id.erweima);

        //搜索物品点击事件
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到搜索框的值
                String goods = CustomSearch.this.goods.getText().toString();
                //非空判断
                if (goods.equals("")){
                    Toast.makeText(getContext(), "不能输入空值呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //接口
                searchLisenter.onsearch(goods);
            }
        });

        //扫描二维码点击事件
        erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                erweimaLisenter.Erweima();
            }
        });

    }
}
