package com.example.zuoye0221.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye0221.MainActivity;
import com.example.zuoye0221.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liuruiqi
 * @fileName MyAdapter
 * @package com.example.zuoye0221.adapter
 * @date 2019/2/21 17:14
 **/
public class MyAdapter extends BaseAdapter {
    Context context;
    JSONArray result;
    public MyAdapter(Context context, JSONArray result) {
     this.context=context;
     this.result=result;
    }

    @Override
    public int getCount() {
        return result.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item01,null);
            viewHolder=new ViewHolder();
            viewHolder.tv=convertView.findViewById(R.id.item01_text);
            viewHolder.img=convertView.findViewById(R.id.item01_img);
            convertView.setTag(viewHolder);

        }else {
        viewHolder= (ViewHolder) convertView.getTag();
        }
        try {
            JSONObject jsonObject = result.getJSONObject(position);
            String commodityName = jsonObject.getString("commodityName");
            String masterPic = jsonObject.getString("masterPic");
            viewHolder.tv.setText(commodityName);
            Glide.with(context).load(masterPic).into(viewHolder.img);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tv;
        private ImageView img;

    }
}
