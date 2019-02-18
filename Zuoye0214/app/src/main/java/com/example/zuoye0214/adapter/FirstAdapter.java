package com.example.zuoye0214.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuoye0214.R;
import com.example.zuoye0214.activity.ShowActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName FirstAdapter
 * @package com.example.zuoye0214.adapter
 * @date 2019/2/16 9:11
 **/
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder> {
    Context context;
    JSONArray result;
    public FirstAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       //代入布局，实例化ViewHolder
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item01,null,false);

        FirstViewHolder viewHolder = new FirstViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder firstViewHolder, int i) {
        //解析数据并赋值
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String title = jsonObject.getString("title");
            String imageUrl = jsonObject.getString("imageUrl");
            //
            //赋值(此时应该先在最底下的Viewholder的找控件
            firstViewHolder.title.setText(title);
            ImageLoader.getInstance().displayImage(imageUrl,firstViewHolder.img);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return result.length();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView img;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item01_text);
            img = itemView.findViewById(R.id.item01_img);
        }
    }
}
