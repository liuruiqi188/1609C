package com.example.week02test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week02test.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.example.week02test.adapter
 * @date 2019/2/25 13:53
 **/
public class MyAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public MyAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }

    //建立接口
    public interface onRemoveLisenter{
        void onRemove(int i);
    }
    //声明
    public onRemoveLisenter removeLisenter;
    //set
    public void setonRemoveLisenter(onRemoveLisenter removeLisenter){
        this.removeLisenter=removeLisenter;
    }


    //多条目必须多写一个布局

    private int TYPE_ONE=0;
    private int TYPE_TWO=1;

    @Override
    public int getItemViewType(int position) {
        if (position%2==1){
            return TYPE_ONE;
        }else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==TYPE_ONE){
            View view1 = LayoutInflater.from(context).inflate(R.layout.item01, null, false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view1);
            return viewHolder1;
        }else {
            View view2=LayoutInflater.from(context).inflate(R.layout.item02,null,false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view2);
            return viewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, final int i) {

        //给空间赋值
        int itemViewType = getItemViewType(i);
        if (itemViewType==TYPE_ONE){
            ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
            try {
                JSONObject jsonObject = result.getJSONObject(i);
                String commobiptName = jsonObject.getString("commodityName");
                String masterPic = jsonObject.getString("masterPic");
                //赋值
                viewHolder1.item01_title.setText(commobiptName);
                Glide.with(context).load(masterPic).into(viewHolder1.item01_img);
                viewHolder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        removeLisenter.onRemove(i);
                        return true;
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            ViewHolder2 viewHolder2= (ViewHolder2) viewHolder;
            try {
                JSONObject jsonObject = result.getJSONObject(i);
                String commobiptName = jsonObject.getString("commodityName");
                String masterPic = jsonObject.getString("masterPic");
                //赋值
                viewHolder2.item02_title.setText(commobiptName);
                Glide.with(context).load(masterPic).into(viewHolder2.item02_img);
                //调用接口
                viewHolder2.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        removeLisenter.onRemove(i);
                        return true;
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
//自己写一个删除的方法
    public void ItemRemove(int position){
        result.remove(position);
        //刷新
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return result.length();
    }
    private class ViewHolder1 extends XRecyclerView.ViewHolder{

        private final ImageView item01_img;
        private final TextView item01_title;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            item01_img = itemView.findViewById(R.id.item01_img);
            item01_title = itemView.findViewById(R.id.item01_title);
        }
    }
    private class ViewHolder2 extends XRecyclerView.ViewHolder{

        private final ImageView item02_img;
        private final TextView item02_title;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            item02_img = itemView.findViewById(R.id.item02_img);
            item02_title = itemView.findViewById(R.id.item02_title);
        }
    }
}
