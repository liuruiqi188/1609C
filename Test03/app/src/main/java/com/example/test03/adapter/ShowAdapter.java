package com.example.test03.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test03.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName ShowAdapter
 * @package com.example.test03.adapter
 * @date 2019/2/28 15:46
 **/
public class ShowAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public ShowAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item01, null, false);
        ViewHolder1 viewHolder1 = new ViewHolder1(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
      //赋值ViewHolder
        ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
      //数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commobiptName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            //赋值
            viewHolder1.title.setText(commobiptName);
            Glide.with(context).load(masterPic).into(viewHolder1.img);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder1 extends XRecyclerView.ViewHolder{

        private final ImageView img;
        private final TextView title;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item01_img);
            title = itemView.findViewById(R.id.item01_title);
        }
    }
}
