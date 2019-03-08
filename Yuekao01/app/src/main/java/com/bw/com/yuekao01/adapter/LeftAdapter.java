package com.bw.com.yuekao01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.yuekao01.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName LeftAdapter
 * @package com.bw.com.yuekao01.adapter
 * @date 2019/3/7 13:40
 **/
public class LeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray data;
    public LeftAdapter(Context context, JSONArray data) {
    this.context=context;
    this.data=data;
    }

    //创建接口
    public interface OnRightLisenter{
        void right(String a);
    }
    private OnRightLisenter rightLisenter;

    public void setOnRightLisenter(OnRightLisenter rightLisenter){
        this.rightLisenter=rightLisenter;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LinearLayout.inflate(context, R.layout.left_layout,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//绑定
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析
        try {
            JSONObject jsonObject = data.getJSONObject(i);
            String name = jsonObject.getString("name");
            final String cid = jsonObject.getString("cid");
            //设置值
            viewHolder1.title.setText(name);

            //点击事件
            viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回传接口
                    if (rightLisenter!=null){
                        rightLisenter.right(cid);
                    }
                }
            });

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.left_title);
        }
    }
}
