package com.bw.com.liuruiqi0308.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.liuruiqi0308.R;
import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;


import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName ShowFragment
 * @package com.bw.com.liuruiqi0308.fragment
 * @date 2019/3/8 13:39
 **/
public class ShowFragment extends Fragment {

    private EditText title;
    private Button search;

    private Button bt;
    private ArrayList<String> list;
    private AutoFlowLayout flow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show,null,false);

        //找布局
        title = view.findViewById(R.id.edit_text);
        search = view.findViewById(R.id.bt_search);
        flow = view.findViewById(R.id.flow);
        bt = view.findViewById(R.id.bt_clean);

        list = new ArrayList<>();

        //搜索点击事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = title.getText().toString();
                list.add(s);
                getData(list);
            }
        });
        //清除按钮事件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.getText().clear();
                list.clear();
                flow.removeAllViews();
            }
        });

        return view;
    }

    private void getData(final ArrayList<String> list) {
        //流式布局的适配器
       flow.setAdapter(new FlowAdapter(list) {
           @Override
           public View getView(int i) {
               //引入视图
               View inflate = LayoutInflater.from(getContext()).inflate(R.layout.flow, null, false);
               //找控件
               TextView text=inflate.findViewById(R.id.flow_text);
               text.setText(list.get(i));
               list.clear();
               return inflate;
           }
       });



    }
}
