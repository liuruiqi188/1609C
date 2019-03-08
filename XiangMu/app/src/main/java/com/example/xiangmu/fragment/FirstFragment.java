package com.example.xiangmu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.adapter.ViewpagerAdapter;
import com.example.xiangmu.presenter.ViewpagerParsenter;
import com.example.xiangmu.view.ViewpagerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.example.xiangmu.fragment
 * @date 2019/3/4 15:59
 **/
public class FirstFragment extends Fragment implements ViewpagerView {

    private RadioGroup rg;
    private ViewPager vp;
    private ViewpagerParsenter viewpagerParsenter;
    private ArrayList<ImageView> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    int currentItem = vp.getCurrentItem()+1;
                    vp.setCurrentItem(currentItem);
                    handler.sendEmptyMessageDelayed(0,1000);
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.first_fragment,null,false);

        //找控件
        rg = view.findViewById(R.id.rg);
        vp = view.findViewById(R.id.vp);

        //实例化P层
        viewpagerParsenter = new ViewpagerParsenter(this);

        viewpagerParsenter.reletecd();

   vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
     public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        i=i%list.size();
        rg.check(rg.getChildAt(i).getId());
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
});
        return view;
    }

    @Override
    public void viewpager(JSONArray data) {
        list = new ArrayList<>();
        for (int a=0;a<data.length();a++){
            try {
                JSONObject jsonObject = data.getJSONObject(a);
                String imageUrl = jsonObject.getString("imageUrl");
                ImageView imageView = new ImageView(getActivity());
                Glide.with(getContext()).load(imageUrl).into(imageView);
                list.add(imageView);
                //小圆点
                RadioButton radioButton = new RadioButton(getActivity());
                rg.addView(radioButton);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getContext(), list);
        vp.setAdapter(viewpagerAdapter);
         rg.check(rg.getChildAt(0).getId());
       handler.sendEmptyMessageDelayed(0,1000);


    }
}
