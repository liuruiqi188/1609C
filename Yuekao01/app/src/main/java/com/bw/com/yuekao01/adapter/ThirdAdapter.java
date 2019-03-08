package com.bw.com.yuekao01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.yuekao01.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName ThirdAdapter
 * @package com.bw.com.yuekao01.adapter
 * @date 2019/3/7 14:31
 **/
public class ThirdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray list;
    public ThirdAdapter(Context context, JSONArray list) {
    this.context=context;
    this.list=list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context,R.layout.third,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //绑定
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = list.getJSONObject(i);
            String name = jsonObject.getString("name");
            String icon = jsonObject.getString("icon");

            //设置数据
            viewHolder1.title.setText(name);
            Glide.with(context).load(icon).into(viewHolder1.img);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.length();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.third_img);
            title = itemView.findViewById(R.id.third_titile);
        }
    }
}
