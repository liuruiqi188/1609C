package com.bw.com.liuruiqi0308.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.liuruiqi0308.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName FirstAdapter
 * @package com.bw.com.liuruiqi0308.adapter
 * @date 2019/3/8 14:24
 **/
public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray data;
    public FirstAdapter(Context context, JSONArray data) {
        this.context=context;
        this.data=data;
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
        //绑定viewholder
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = data.getJSONObject(i);
            String sellerName = jsonObject.getString("sellerName");
            JSONArray list = jsonObject.getJSONArray("list");
            //设置
            viewHolder1.title.setText(sellerName);


            //布局管理器
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            viewHolder1.rlv1.setLayoutManager(layoutManager);


            //实例化适配器
            SecendAdapter secendAdapter = new SecendAdapter(context,list);
            viewHolder1.rlv1.setAdapter(secendAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final RecyclerView rlv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item01_title);
            rlv1 = itemView.findViewById(R.id.rlv1);
        }
    }
}
