package com.example.xiangmu.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiangmu.R;
import com.example.xiangmu.okhttputils.OkHttpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
 * @package com.example.xiangmu.adapter
 * @date 2019/3/5 14:36
 **/
public class RightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public RightAdapter(Context context, JSONArray result) {
    this.context=context;
    this.result=result;
    }

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
        //绑定viewholder
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String name = jsonObject.getString("name");
            String id = jsonObject.getString("id");
            //设置
            viewHolder1.title.setText(name);

            //在联网判断
            String url="http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId="+id+"&page=1&count=5";
            OkHttpUtils.getInstance().doGet(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String json = response.body().string();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject1 = new JSONObject(json);
                                JSONArray result = jsonObject1.getJSONArray("result");
                                //网格化
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
                                viewHolder1.rlv.setLayoutManager(gridLayoutManager);
                                //适配器
                                ThirdAdapter thirdAdapter = new ThirdAdapter(context, result);
                                viewHolder1.rlv.setAdapter(thirdAdapter);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
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
        private final RecyclerView rlv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //找控件
            title = itemView.findViewById(R.id.right_title);
            rlv = itemView.findViewById(R.id.rlv);


        }
    }
}
