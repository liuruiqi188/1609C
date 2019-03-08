package com.example.xiangmu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.custom.CustomShopCar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuruiqi
 * @fileName ShopCarAdapter
 * @package com.example.xiangmu.adapter
 * @date 2019/3/5 14:06
 **/
public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    private Map<String,Boolean> map=new HashMap<>();

    //-------------------------------------------------------全选接口
    public interface onQuanCheckLisenter{
        void quancheck(boolean flag);
    }
    private onQuanCheckLisenter quanCheckLisenter;

    public void setonQuanCheckLisenter(onQuanCheckLisenter quanCheckLisenter){
        this.quanCheckLisenter=quanCheckLisenter;
    }




    public ShopCarAdapter(Context context, JSONArray result) {
    this.context=context;
    this.result=result;
    setCheckData(false);
    }

    //写一个获取ck状态的方法
   private  void setCheckData(boolean checkflag){
        map.clear();
        for (int c=0;c<result.length();c++){
            try {
                JSONObject jsonObject = result.getJSONObject(c);
                String id = jsonObject.getString("commodityId");
                map.put(id,checkflag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //一个刷新的方法
    public void notifCheckData(boolean checkflag){
        setCheckData(checkflag);
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context,R.layout.shopcar,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String commodityName = jsonObject.getString("commodityName");
              String masterPic = jsonObject.getString("masterPic");
            final String id = jsonObject.getString("commodityId");
            //设置值
            viewHolder1.title.setText(commodityName);
            Glide.with(context).load(masterPic).into(viewHolder1.img);

            //-------------------------------------------------------------------------
          viewHolder1.ck1.setChecked(map.get(id));
            viewHolder1.ck1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = viewHolder1.ck1.isChecked();
                    map.put(id,checked);
                    boolean flag=true;
                    for (String k:map.keySet()){
                        Boolean aBoolean = map.get(k);
                        if (!aBoolean){
                            flag=false;
                        }else {
                            if (quanCheckLisenter!=null){
                                quanCheckLisenter.quancheck(flag);
                            }
                        }
                    }
                }
            });


            //适配器里的自定义布局调用接口

            viewHolder1.csv.setonAddLisenter(new CustomShopCar.onAddLisenter() {
                @Override
                public void onadd(int c) {

                }
            });

            viewHolder1.csv.setonJianLisenter(new CustomShopCar.onJianLisenter() {
                @Override
                public void onjian(int p) {

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

        private final ImageView img;
        private final TextView title;
        private final CustomShopCar csv;
        private final CheckBox ck1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.shopcar_img);
            title = itemView.findViewById(R.id.shopcar_title);
            csv = itemView.findViewById(R.id.shopcar_cv);
            ck1 = itemView.findViewById(R.id.ck1);
        }
    }
}
