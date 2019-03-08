package com.bw.com.shop2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.shop2.CustomLin;
import com.bw.com.shop2.R;
import com.bw.com.shop2.activity.MainActivity;
import com.bw.com.shop2.bean.Datazhong;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.bw.com.shop2.adapter
 * @date 2019/3/6 18:50
 **/
public class MyAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<Datazhong> data;
    public MyAdapter(Context context, ArrayList<Datazhong> data) {
    this.context=context;
    this.data=data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getSpus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getSpus().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item01,null);
            viewHolder1=new ViewHolder1();
            viewHolder1.title=convertView.findViewById(R.id.item01_title);
            convertView.setTag(viewHolder1);
        }else {
            viewHolder1= (ViewHolder1) convertView.getTag();
        }
        viewHolder1.title.setText(data.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           ViewHolder2 viewHolder2;
           if (convertView==null){
               convertView=View.inflate(context,R.layout.item02,null);
               viewHolder2=new ViewHolder2();
               viewHolder2.name=convertView.findViewById(R.id.item02_name);
               viewHolder2.money=convertView.findViewById(R.id.item02_money);
               viewHolder2.img=convertView.findViewById(R.id.item02_img);
               viewHolder2.cvl=convertView.findViewById(R.id.cvl);
               convertView.setTag(viewHolder2);
           }else {
               viewHolder2= (ViewHolder2) convertView.getTag();
           }
           viewHolder2.name.setText(data.get(groupPosition).getSpus().get(childPosition).getName());
           viewHolder2.money.setText(data.get(groupPosition).getSpus().get(childPosition).getPraise_num());
           Glide.with(context).load(data.get(groupPosition).getSpus().get(childPosition).getPic_url()).into(viewHolder2.img);

           //加加减减接口回调
        viewHolder2.cvl.setonJiaLisenter(new CustomLin.onJiaLisenter() {
            @Override
            public void onjia(int q) {

            }
        });
        viewHolder2.cvl.setonJianLisenter(new CustomLin.onJianLisenter() {
            @Override
            public void onJian(int q) {

            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private class ViewHolder1{
        private TextView title;
    }
    private class ViewHolder2{
        private TextView name;
        private TextView money;
        private ImageView img;
        private CustomLin cvl;
    }
}
