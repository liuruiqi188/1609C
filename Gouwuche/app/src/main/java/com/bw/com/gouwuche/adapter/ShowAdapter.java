package com.bw.com.gouwuche.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.gouwuche.R;
import com.bw.com.gouwuche.activity.ShowActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName ShowAdapter
 * @package com.bw.com.gouwuche.adapter
 * @date 2019/3/5 10:55
 **/
public class ShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public ShowAdapter(Context context, JSONArray result) {
    this.context=context;
    this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context,R.layout.item01,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //实例化viewholder
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            //设置值
            viewHolder1.title.setText(commodityName);
            Glide.with(context).load(masterPic).into(viewHolder1.img);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item01_img);
            title = itemView.findViewById(R.id.item01_title);


        }
    }
}
