package com.bw.com.liuruiqi0308.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.liuruiqi0308.R;
import com.bw.com.liuruiqi0308.custom.CustomView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName SecendAdapter
 * @package com.bw.com.liuruiqi0308.adapter
 * @date 2019/3/8 15:43
 **/
public class SecendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray list;
    public SecendAdapter(Context context, JSONArray list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context,R.layout.item02,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //绑定
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析
        try {
            JSONObject jsonObject = list.getJSONObject(i);
            String title = jsonObject.getString("title");
            String price = jsonObject.getString("price");
            String detailUrl = jsonObject.getString("detailUrl");
            //设置值
            viewHolder1.title.setText(title);
            viewHolder1.price.setText(price);
            Glide.with(context).load(detailUrl).into(viewHolder1.img);

            //回调自定义的接口 加加减减事件
            viewHolder1.cus.setonJiaLisenter(new CustomView.onJiaLisenter() {
                @Override
                public void jia(int p) {

                }
            });
            viewHolder1.cus.setonJianLisenter(new CustomView.onJianLisenter() {
                @Override
                public void jian(int p) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.length();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView img;
        private final TextView price;
        private final CustomView cus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item02_title);
            img = itemView.findViewById(R.id.item02_img);
            price = itemView.findViewById(R.id.item02_price);
            cus = itemView.findViewById(R.id.custom);
        }
    }
}
