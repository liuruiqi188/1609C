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
import org.w3c.dom.Text;

/**
 * @author liuruiqi
 * @fileName ThirdAdapter
 * @package com.bw.com.week03test.adapter
 * @date 2019/3/2 15:20
 **/
public class ThirdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public ThirdAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item04,null);
        Viewhodler viewhodler = new Viewhodler(view);
        return viewhodler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
Viewhodler viewhodler= (Viewhodler) viewHolder;
//解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            //设置
            viewhodler.title.setText(commodityName);
            Glide.with(context).load(masterPic).into(((Viewhodler) viewHolder).img);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class Viewhodler extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView img;

        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item04_title);
            img = itemView.findViewById(R.id.item04_img);

        }
    }
}
