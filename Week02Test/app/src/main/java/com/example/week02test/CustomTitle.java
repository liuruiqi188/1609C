package com.example.week02test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author liuruiqi
 * @fileName CustomTitle
 * @package com.example.week02test
 * @date 2019/2/23 11:54
 **/
public class CustomTitle extends LinearLayout {

    private EditText et_goods;
    private Button bt_search;


    //创建接口
    public interface onSearchLisenter{
        void onSearch(String goods);
    }
    //声明
    public onSearchLisenter searchLisenter;

    //set方法
    public void onSearchLisenter(onSearchLisenter searchLisenter){
        this.searchLisenter=searchLisenter;
    }


    public CustomTitle(Context context) {
        super(context);
    }

    public CustomTitle(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public CustomTitle(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {

        //创建布局
        View view=inflate(getContext(),R.layout.customtitle,null);
        addView(view);

        //找控件
        et_goods = view.findViewById(R.id.et_goods);
        bt_search = view.findViewById(R.id.bt_search);

        //点击事件
        bt_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框的值
                String goods = et_goods.getText().toString();

                //非空验证
                if (goods.equals("")){
                    Toast.makeText(getContext(), "你没有写东西呦!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //调用接口
                searchLisenter.onSearch(goods);


            }
        });


    }

}
