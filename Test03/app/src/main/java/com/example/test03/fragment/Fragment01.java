package com.example.test03.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.test03.R;
import com.example.test03.adapter.FirstAdapter;
import com.example.test03.adapter.ShowAdapter;
import com.example.test03.custom.CustomSearch;
import com.example.test03.okhttputils.OkHttpUtils;
import com.example.test03.presenter.SearchPresenter;
import com.example.test03.view.ShowView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jwkj.libzxing.OnQRCodeScanCallback;
import com.jwkj.libzxing.QRCodeManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.test03.fragment
 * @date 2019/2/27 20:45
 **/
public class Fragment01 extends Fragment implements ShowView {

    private XRecyclerView xrlv;
    private CustomSearch cs;
    private ViewPager vp;
    private String goods1="鞋子";
    private int page=1;
    private SearchPresenter searchPresenter;
    private ArrayList<ImageView> list;
    private RadioGroup rg;
    private String url="http://172.17.8.100/small/commodity/v1/bannerShow";
    //handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json= (String) msg.obj;
                    //原生解析
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray result = jsonObject.getJSONArray("result");
                        //把集合里的数据一个个取出来  要用for循环一个一个取出来
                        for (int i=0;i<result.length();i++){
                            JSONObject jsonObject1 = result.getJSONObject(i);
                            String imageUrl = jsonObject1.getString("imageUrl");
                            //新建一个图像的集合
                            ImageView imageView = new ImageView(getActivity());
                            Glide.with(getActivity()).load(imageUrl).into(imageView);
                            list.add(imageView);
                            //加入小圆点
                            RadioButton radioButton = new RadioButton(getActivity());
                            rg.addView(radioButton);
                        }
                        //适配器
                        FirstAdapter firstAdapter = new FirstAdapter(getContext(),list);
                        //viewpager设置适配器
                        vp.setAdapter(firstAdapter);
                        //小圆点跟着动
                        rg.check(rg.getChildAt(0).getId());
                        //延迟1秒发送
                        handler.sendEmptyMessageDelayed(1,1000);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    int i = vp.getCurrentItem() + 1;
                    vp.setCurrentItem(i);
                    handler.sendEmptyMessageDelayed(1,1000);
                    break;
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag01,null,false);

        //找控件
        xrlv = view.findViewById(R.id.xrlv);
        //得到布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        //给Xrlv设置模式
        xrlv.setLayoutManager(layoutManager);
        cs = view.findViewById(R.id.cs);
        vp = view.findViewById(R.id.vp);
        rg = view.findViewById(R.id.rg);

        //创建集合
        list = new ArrayList<>();
        getData();

        //------------------------------------------------------------------------------刷新事件--------------------------------------------------------------------
        xrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        searchPresenter.send(goods1,page);
                        xrlv.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        searchPresenter.send(goods1,page);
                        xrlv.loadMoreComplete();
                    }
                },2000);

            }
        });



//-------------------------------------------------------------------------------------------------------下    M      V      P   --------------------------------------------
        //实例化P层
        searchPresenter = new SearchPresenter(this);
        //弱引用关联
        searchPresenter.attachView(this);

        //监听自定义控件
        cs.setOnSearchLisenter(new CustomSearch.OnSearchLisenter() {
            @Override
            public void onsearch(String goods) {
                goods1=goods;
                //给P层传数据
                searchPresenter.send(goods1,page);
            }
        });

        cs.setonErweimaLisenter(new CustomSearch.onErweimaLisenter() {
            @Override
            public void Erweima() {
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
//---------------------------------------------------------------------------------------------上  M    V    P--------------------------------------------------------
    private void getData() {
        //pager设置事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                i=i%list.size();
                rg.check(rg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //okhttp得到网络连接
        OkHttpUtils.getInstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                //新建消息
                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送消息
                handler.sendMessage(message);
            }
        });

    }

    @Override
    public void Show(JSONArray result) {
        Toast.makeText(getContext(), result.length()+"", Toast.LENGTH_SHORT).show();
        //适配器
        ShowAdapter showAdapter = new ShowAdapter(getContext(),result);
        //xrlv设置适配器
        xrlv.setAdapter(showAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchPresenter.deatchView();
        Log.i("zzz","销毁了");
    }
}
