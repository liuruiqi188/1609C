package com.example.xiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName SearchAdapter
 * @package com.example.xiangmu.adapter
 * @date 2019/3/5 15:44
 **/
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public SearchAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          View view=LinearLayout.inflate(context, R.layout.search_layout,null);
          ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //绑定
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            //开始设置值
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

        private final TextView title;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.search_title);
            img = itemView.findViewById(R.id.search_img);
        }
    }
}
