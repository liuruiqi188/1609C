package com.bw.com.yuekao01.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.yuekao01.R;
import com.bw.com.yuekao01.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName RightAdapter
 * @package com.bw.com.yuekao01.adapter
 * @date 2019/3/7 14:15
 **/
public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray data;
    public RightAdapter(Context context, JSONArray data) {
    this.context=context;
    this.data=data;
    }

    private interface OnThirdLisenter{
        void third( JSONArray list );
    }
    private OnThirdLisenter thirdLisenter;

    public void setOnThirdLisenter(OnThirdLisenter thirdLisenter){
        this.thirdLisenter=thirdLisenter;
    }

    //handler
    private Handler handler=new Handler();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.right,null);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
           //棒冰
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = data.getJSONObject(i);
            String name = jsonObject.getString("name");

            final JSONArray list = jsonObject.getJSONArray("list");

            String cid = jsonObject.getString("cid");
            //设置值
            viewHolder1.title.setText(name);

            GridLayoutManager layoutManager = new GridLayoutManager(context,3);
            viewHolder1.rlv3.setLayoutManager(layoutManager);


            //适配器
            ThirdAdapter thirdAdapter = new ThirdAdapter(context, list);
            viewHolder1.rlv3.setAdapter(thirdAdapter);





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
        private final RecyclerView rlv3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.right_title);
            rlv3 = itemView.findViewById(R.id.rlv3);

        }
    }
}
