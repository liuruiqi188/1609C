package com.example.week02test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.week02test.activity.MainActivity;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName FragmentAdapter
 * @package com.example.week02test
 * @date 2019/2/23 9:35
 **/
public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;
    ArrayList<Fragment> list;
    public FragmentAdapter(FragmentManager fm,Context context, ArrayList<Fragment> list) {
        super(fm);
        this.context=context;
        this.list=list;
    }



    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
