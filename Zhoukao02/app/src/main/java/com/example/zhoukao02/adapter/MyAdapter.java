package com.example.zhoukao02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhoukao02.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.example.zhoukao02.adapter
 * @date 2019/2/22 16:57
 **/
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public MyAdapter(Context context, JSONArray result) {
    this.context=context;
    this.result=result;
    }
    private int TYPE_ONE=0;
    private int TYPE_TWO=1;
    //返回条目类型
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i==TYPE_ONE){
            View view1=LayoutInflater.from(context).inflate(R.layout.item01,null,false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view1);
            return  viewHolder1;
        }else {
            View view2=LayoutInflater.from(context).inflate(R.layout.item02,null,false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view2);
            return viewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //给控件赋值
        int itemViewType = getItemViewType(i);
        if (itemViewType==TYPE_ONE){
            ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
            try {
                JSONObject jsonObject = result.getJSONObject(i);
                String commobiptName = jsonObject.getString("commodityName");
                String masterPic = jsonObject.getString("masterPic");
                //设置值
                viewHolder1.item01_title.setText(commobiptName);
                Glide.with(context).load(masterPic).into(viewHolder1.item01_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            ViewHolder2 viewHolder2= (ViewHolder2) viewHolder;
            try {
                JSONObject jsonObject = result.getJSONObject(i);
                String commobiptName = jsonObject.getString("commodityName");
                String masterPic = jsonObject.getString("masterPic");
                //设置值
                viewHolder2.item02_title.setText(commobiptName);
                Glide.with(context).load(masterPic).into(viewHolder2.item02_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView item01_img;
        private final TextView item01_title;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            item01_img = itemView.findViewById(R.id.item01_img);
            item01_title = itemView.findViewById(R.id.item01_title);
        }

    }
    private class ViewHolder2 extends RecyclerView.ViewHolder{

        private final ImageView item02_img;
        private final TextView item02_title;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            item02_img = itemView.findViewById(R.id.item02_img);
            item02_title = itemView.findViewById(R.id.item02_title);
        }

    }
}

