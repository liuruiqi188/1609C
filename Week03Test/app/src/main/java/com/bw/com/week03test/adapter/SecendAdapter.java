package com.bw.com.week03test.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.week03test.R;
import com.bw.com.week03test.okhttputils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.net.ssl.SNIHostName;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName SecendAdapter
 * @package com.bw.com.week03test.adapter
 * @date 2019/3/2 14:52
 **/
public class SecendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    JSONArray result;
    public SecendAdapter(Context context, JSONArray result) {
        this.context=context;
        this.result=result;
    }
    private Handler handler=new Handler();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item03, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

//实例化hodler
        final ViewHolder holder= (ViewHolder) viewHolder;
        //解析数据
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String name = jsonObject.getString("name");
            String id = jsonObject.getString("id");
            //设置
            holder.title.setText(name);
            String url="http://172.17.8.100/small/commodity/v1/findCommodityByCategory?categoryId="+id+"&page=1&count=5";
                    ;
            //获取网络数据
            OkHttpUtils.getInstance().doGet(url, new Callback() {

                private String json;

                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    json = response.body().string();
                    Log.i("aaa", json);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject1 = new JSONObject(json);
                                JSONArray result = jsonObject1.getJSONArray("result");
                                //网格化
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
                                holder.rlv.setLayoutManager(gridLayoutManager);
                                //适配器
                                ThirdAdapter thirdAdapter = new ThirdAdapter(context, result);
                                holder.rlv.setAdapter(thirdAdapter);

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
            title = itemView.findViewById(R.id.item03_title);
            rlv = itemView.findViewById(R.id.rlv);

        }
    }
}
