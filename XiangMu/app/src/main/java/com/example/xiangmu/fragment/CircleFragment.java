package com.example.xiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiangmu.R;
import com.example.xiangmu.adapter.LeftAdapter;
import com.example.xiangmu.adapter.RightAdapter;
import com.example.xiangmu.adapter.SearchAdapter;
import com.example.xiangmu.custom.CustomView;
import com.example.xiangmu.presenter.LeftPresenter;
import com.example.xiangmu.presenter.RightPresenter;
import com.example.xiangmu.presenter.SearchPresenter;
import com.example.xiangmu.view.LeftView;
import com.example.xiangmu.view.RightView;
import com.example.xiangmu.view.SearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jwkj.libzxing.OnQRCodeScanCallback;
import com.jwkj.libzxing.QRCodeManager;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.example.xiangmu.fragment
 * @date 2019/3/4 15:59
 **/
public class CircleFragment extends Fragment implements LeftView ,RightView,SearchView {

    private CustomView cv;
    private XRecyclerView rlv1;
    private XRecyclerView rlv2;
    private LeftPresenter leftPresenter;
    private RightPresenter rightPresenter;
    private XRecyclerView rlv3;
    private String goods1="鞋子";
    private int page =1;
    private SearchPresenter searchPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.circle_fragment,null,false);

        //找控件
        cv = view.findViewById(R.id.cv);
        rlv1 = view.findViewById(R.id.rlv1);
        rlv2 = view.findViewById(R.id.rlv2);
        rlv3 = view.findViewById(R.id.rlv3);

        //自定义监听事件                       二维码扫描
        cv.setonErweimaLisenter(new CustomView.onErweimaLisenter() {
            @Override
            public void OnErweima() {
                QRCodeManager.getInstance().with(getActivity()).setReqeustType(1).scanningQRCode(new OnQRCodeScanCallback() {
                    @Override
                    public void onCompleted(String s) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });

        //实例化P层
        searchPresenter = new SearchPresenter(this);


        //得到布局管理器
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        //rlv1设置
        rlv3.setLayoutManager(layoutManager2);


        //自定义监听事件                            搜索物品
        cv.setonSearchLisenter(new CustomView.onSearchLisenter() {
            @Override
            public void OnSearch(String goods) {
                //把分类布局隐藏 把搜索页面显示出来
                rlv1.setVisibility(View.GONE);
                rlv2.setVisibility(View.GONE);
                rlv3.setVisibility(View.VISIBLE);

                goods1=goods;
                //联系P层
                searchPresenter.send(goods1,page);

            }
        });


        //得到布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //rlv1设置
        rlv1.setLayoutManager(layoutManager);

        //布局管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        //rlv2设置
        rlv2.setLayoutManager(layoutManager1);


        //实例化1J图表
        leftPresenter = new LeftPresenter(this);

        leftPresenter.relected();

        //实例化2J图表
        rightPresenter = new RightPresenter(this);


        return view;
    }

    @Override
    public void left(JSONArray result) {
        //适配器
        LeftAdapter leftAdapter = new LeftAdapter(getActivity(),result);
        //展示数据
        rlv1.setAdapter(leftAdapter);
        //接受接口
        leftAdapter.setOnSecendLisenter(new LeftAdapter.OnSecendLisenter() {
            @Override
            public void onsecend(String id) {
                //在这连接第二层 给第二层把id传过去
                rightPresenter.send(id);

            }
        });
    }

    @Override
    public void right(JSONArray result) {
        //适配器
        RightAdapter rightAdapter = new RightAdapter(getContext(),result);
        rlv2.setAdapter(rightAdapter);
    }

    @Override
    public void search(JSONArray result) {
        //适配器
        SearchAdapter searchAdapter = new SearchAdapter(getContext(),result);
        //设置适配器
        rlv3.setAdapter(searchAdapter);

    }
}
