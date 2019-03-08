package com.bw.com.week03test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.week03test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName ShowAdapter
 * @package com.bw.com.week03test.adapter
 * @date 2019/3/2 8:57
 **/
public class ShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    private ViewHolder1 viewHolder1;

    public ShowAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item01, null);
        viewHolder1 = new ViewHolder1(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            //开始设置值
            viewHolder1.tltle.setText(commodityName);
            Glide.with(context).load(masterPic).into(viewHolder1.img);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView tltle;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item01_img);
            tltle = itemView.findViewById(R.id.item01_title);

        }
    }
}
