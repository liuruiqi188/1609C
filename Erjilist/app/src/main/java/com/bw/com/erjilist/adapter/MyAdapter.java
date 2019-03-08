package com.bw.com.erjilist.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.erjilist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.bw.com.erjilist.adapter
 * @date 2019/3/6 11:39
 **/
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public MyAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context,R.layout.show,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //绑定viewholder
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
            String id = jsonObject.getString("commodityId");

            //设置值
            viewHolder1.title.setText(commodityName);
            //




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox ck;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ck = itemView.findViewById(R.id.ck);
            title = itemView.findViewById(R.id.show_title);

        }
    }
}
