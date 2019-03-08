package com.example.test03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName FirstAdapter
 * @package com.example.test03.adapter
 * @date 2019/2/28 14:57
 **/
public class FirstAdapter extends PagerAdapter {
    Context context;
    ArrayList<ImageView> list;
    public FirstAdapter(Context context, ArrayList<ImageView> list) {
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
