package com.bw.com.week03test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.week03test.R;
import com.bw.com.week03test.adapter.FirstAdapter;
import com.bw.com.week03test.adapter.SecendAdapter;
import com.bw.com.week03test.adapter.ShowAdapter;
import com.bw.com.week03test.custom.CustomView;
import com.bw.com.week03test.model.FirstModel;
import com.bw.com.week03test.presenter.SecendPresenter;
import com.bw.com.week03test.presenter.ShowPresenter;
import com.bw.com.week03test.view.FirstView;
import com.bw.com.week03test.view.SecendView;
import com.bw.com.week03test.view.ShowView;
import com.jwkj.libzxing.OnQRCodeScanCallback;
import com.jwkj.libzxing.QRCodeManager;

import org.json.JSONArray;

/**
 * @author liuruiqi
 * @fileName FirstFragment
 * @package com.bw.com.week03test.fragment
 * @date 2019/3/1 14:14
 **/
public class StyleFragment extends Fragment implements ShowView ,FirstView ,SecendView {

    private CustomView cv;
    private ShowPresenter showPresenter;
    private String goods1="鞋子";
    private int page =1;
    private RecyclerView rlv1;
    private RecyclerView rlv2;
    private RecyclerView rlv3;
    private FirstModel firstModel;
    private SecendPresenter secendPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.stylefragment,null,false);
        //找控件
        cv = view.findViewById(R.id.cv);
        rlv1 = view.findViewById(R.id.rlv1);
        rlv2 = view.findViewById(R.id.rlv2);
        rlv3 = view.findViewById(R.id.rlv3);

        //得到布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //rlv3设置
        rlv3.setLayoutManager(layoutManager);
        //
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rlv1.setLayoutManager(layoutManager1);
        //
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rlv2.setLayoutManager(layoutManager2);

        //实例化showP层
        showPresenter = new ShowPresenter(this);

        //实例化1J
        firstModel = new FirstModel(this);

        firstModel.relected();

        //实例化第二层
        secendPresenter = new SecendPresenter(this);

        //监听自定义控件事件 搜索功能
        cv.setonSearchLisenter(new CustomView.onSearchLisenter() {
            @Override
            public void OnSearch(String goods) {
                //
                rlv1.setVisibility(View.GONE);
                rlv2.setVisibility(View.GONE);
                rlv3.setVisibility(View.VISIBLE);

                goods1=goods;
                 //发送数据
                showPresenter.send(goods1,page);

            }
        });

        //监听自定义事件   二维码功能
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




        return view;
    }

    @Override
    public void Show(JSONArray result) {
        //吐司看成不成功
        Toast.makeText(getContext(), result.toString(), Toast.LENGTH_SHORT).show();
        //适配器
        ShowAdapter showAdapter = new ShowAdapter(getContext(), result);
        //rlv3设置适配器
        rlv3.setAdapter(showAdapter);
    }

    //第一层展示
    @Override
    public void first(JSONArray result) {
        //适配器
        FirstAdapter firstAdapter = new FirstAdapter(getContext(),result);
        //设置适配器
        rlv1.setAdapter(firstAdapter);
        //适配器接收接口
        firstAdapter.setOnSecendLisenter(new FirstAdapter.OnSecendLisenter() {
            @Override
            public void onsecend(String id) {
                //连接第二层展示
                secendPresenter.sendd(id);
            }
        });
    }

    @Override
    public void secend(JSONArray result) {
        Toast.makeText(getContext(), result.length()+"", Toast.LENGTH_SHORT).show();
        //适配器
        SecendAdapter secendAdapter = new SecendAdapter(getContext(),result);
        //设置适配
        rlv2.setAdapter(secendAdapter);

    }
}
