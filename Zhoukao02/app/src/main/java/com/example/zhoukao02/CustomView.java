package com.example.zhoukao02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author liuruiqi
 * @fileName CustomView
 * @package com.example.zhoukao02
 * @date 2019/2/22 14:46
 **/
public class CustomView extends LinearLayout {

    private EditText et;
    private Button qd;

    //创建接口
    public interface OnSerachLisenter{
        void onSearch(String goods);
    }
    //声明接口
    public OnSerachLisenter serachLisenter;

    //set方法
    public void setOnSerachLisenter(OnSerachLisenter serachLisenter){
        this.serachLisenter=serachLisenter;
    }



    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        View view=inflate(getContext(),R.layout.serach,null);
        addView(view);
        //找控件
        et = view.findViewById(R.id.ed);
        qd = view.findViewById(R.id.bt_qd);

        //点击事件 获取et的值
        qd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String goods = et.getText().toString();
                if (goods.equals("")){
                    Toast.makeText(getContext(), "不能输入空值呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //回调接口 把数据传过去
                serachLisenter.onSearch(goods);

            }
        });
    }
}
