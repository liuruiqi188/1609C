package com.example.xiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName ViewpagerAdapter
 * @package com.example.xiangmu.adapter
 * @date 2019/3/7 19:37
 **/
public class ViewpagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<ImageView> list;

    public ViewpagerAdapter(Context context, ArrayList<ImageView> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position=position%list.size();
        ImageView imageView = list.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
