package com.example.day0216;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day0216.activity.ShowActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName FirstAdapter
 * @package com.example.day0216
 * @date 2019/2/17 19:38
 **/
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewholder> {

    //接口
    public interface OnItemclickLisenter{
        void onItemClick(int i);
        void onItemLongClick(int i);
    }
    //监听
    public OnItemclickLisenter itemclickLisenter;

    //set
    public void setOnItemclickLisenter(OnItemclickLisenter itemclickLisenter ){
        this.itemclickLisenter=itemclickLisenter;
    }



Context context;
 JSONArray result;

    public FirstAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }
private static final int TYPE0=0;
    private static final int TYPE1=1;
    @Override
    public int getItemViewType(int position) {
        if (position%2==1){
            return TYPE0;
        }else {
            return TYPE1;
        }
    }

    @NonNull
    @Override
    public FirstAdapter.FirstViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          //代入布局

          View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item01,null,false);
          //实例化优化Viewholder
          FirstViewholder firstViewholder = new FirstViewholder(view);

          return firstViewholder;





    }

    @Override
    public void onBindViewHolder(@NonNull FirstAdapter.FirstViewholder firstViewholder, final int i) {


            ///解析数据并复制
            try {
                JSONObject jsonObject = result.getJSONObject(i);
                String imageUrl = jsonObject.getString("imageUrl");
                String title = jsonObject.getString("title");
                //此时补全下面的Viewholder找控件
                firstViewholder.item01_text.setText(title);
                ImageLoader.getInstance().displayImage(imageUrl,firstViewholder.item01_img);

                //
                firstViewholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemclickLisenter.onItemClick(i);
                    }
                });
                firstViewholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        itemclickLisenter.onItemLongClick(i);
                        return true;
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

    public class FirstViewholder extends RecyclerView.ViewHolder {

        private final ImageView item01_img;
        private final TextView item01_text;

        public FirstViewholder(@NonNull View itemView) {
            super(itemView);

            item01_img = itemView.findViewById(R.id.item01_img);
            item01_text = itemView.findViewById(R.id.item01_text);

        }
    }
}
