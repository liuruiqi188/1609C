package com.bw.com.week03test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.week03test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName FirstAdapter
 * @package com.bw.com.week03test.adapter
 * @date 2019/3/2 10:33
 **/
public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public FirstAdapter(Context context, JSONArray result) {
       this.context=context;
       this.result=result;
    }
    //接口
    public interface OnSecendLisenter{
        void onsecend(String id);
    }
    public OnSecendLisenter secendLisenter;

    public void setOnSecendLisenter(OnSecendLisenter secendLisenter){
        this.secendLisenter=secendLisenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item02, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String name = jsonObject.getString("name");
            final String id = jsonObject.getString("id");
            //设置
            viewHolder1.title.setText(name);
            //设置接口
            viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    secendLisenter.onsecend(id);
                }
            });
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //找控件
            title = itemView.findViewById(R.id.item02_title);

        }
    }
}
