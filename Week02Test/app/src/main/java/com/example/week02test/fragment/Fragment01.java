package com.example.week02test.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.week02test.CustomTitle;
import com.example.week02test.R;
import com.example.week02test.adapter.MyAdapter;
import com.example.week02test.presenter.SearchPersents;
import com.example.week02test.view.SearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.week02test.fragment
 * @date 2019/2/23 9:27
 **/
public class Fragment01 extends Fragment implements SearchView {

    private XRecyclerView rlv;
    private CustomTitle cv;
    private SearchPersents searchPersents;
    private  int page=1;
    private String goods1="鞋子";

    //handler
    private Handler handler=new Handler(){

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment01,null,false);




        //找控件
        rlv = view.findViewById(R.id.rlv);
        cv = view.findViewById(R.id.cv);

        //得到布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //设置模式
        rlv.setLayoutManager(linearLayoutManager);
        //实例化searchP层
        searchPersents = new SearchPersents(this);
        //设置监听cv事件
        cv.onSearchLisenter(new CustomTitle.onSearchLisenter() {
            @Override
            public void onSearch(String goods) {
goods1=goods;
                //联系P层
                searchPersents.send(goods1,page);
            }
        });

        //xrlv刷新
        rlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        searchPersents.send(goods1,page);
                        rlv.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
handler.postDelayed(new Runnable() {
    @Override
    public void run() {
        page++;
        searchPersents.send(goods1,page);
        rlv.loadMoreComplete();
    }
},2000);
            }
        });




        return view;
    }

    @Override
    public void Search(JSONArray result) {
        Toast.makeText(getContext(), result.length()+"", Toast.LENGTH_SHORT).show();
        //适配器
        final MyAdapter myAdapter = new MyAdapter(getActivity(),result);
        //给rlv设置适配器
        rlv.setAdapter(myAdapter);

        //适配器调用接口
        myAdapter.setonRemoveLisenter(new MyAdapter.onRemoveLisenter() {
            @Override
            public void onRemove(final int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("您确定要删除么？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myAdapter.ItemRemove(i);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }
}
