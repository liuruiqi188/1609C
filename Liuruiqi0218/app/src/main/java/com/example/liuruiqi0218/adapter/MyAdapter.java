package com.example.liuruiqi0218.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuruiqi0218.R;
import com.example.liuruiqi0218.activity.MainActivity;
import com.example.liuruiqi0218.imageloder.ImageLoder;
import com.nostra13.universalimageloader.core.ImageLoader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.example.liuruiqi0218.adapter
 * @date 2019/2/18 9:23
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    JSONArray data1;
    public MyAdapter(Context context, JSONArray data1) {
        this.context=context;
        this.data1=data1;
    }

    //创建接口
    public interface OnItemClickLisenter{
        void onItemLongClickLisenter();
    }
    //监听
    public OnItemClickLisenter itemClickLisenter;

    //set 方法
    public void setOnItemClickLisenter(OnItemClickLisenter itemClickLisenter){
        this.itemClickLisenter=itemClickLisenter;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item01,null,false);
        //实例化Viewholder
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
        //解析数据
        try {
            JSONObject jsonObject = data1.getJSONObject(i);
            String title = jsonObject.getString("title");
            JSONArray pics = jsonObject.getJSONArray("pics");
            String img = "http://365jia.cn/uploads/"+(String) pics.get(0);
            //设置值
            myViewHolder.item01_text.setText(title);
            ImageLoader.getInstance().displayImage(img,myViewHolder.item01_img);

            //接口点击
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "长按事件", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data1.length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item01_text;
        private final ImageView item01_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item01_text = itemView.findViewById(R.id.item01_text);
            item01_img = itemView.findViewById(R.id.item01_img);

        }
    }
}
