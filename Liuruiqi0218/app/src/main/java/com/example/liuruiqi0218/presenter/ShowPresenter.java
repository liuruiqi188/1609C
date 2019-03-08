package com.example.liuruiqi0218.presenter;

import com.example.liuruiqi0218.activity.MainActivity;
import com.example.liuruiqi0218.model.ShowModel;
import com.example.liuruiqi0218.view.ShowView;

import org.json.JSONArray;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName ShowPresenter
 * @package com.example.liuruiqi0218.presenter
 * @date 2019/2/18 8:50
 **/
public class ShowPresenter <T>{

    private final ShowModel showModel;
    private final ShowView showView;
    private Reference<T> tWeakReference;


    //构造
    public ShowPresenter(ShowView view) {
        //实例化M层
        showModel = new ShowModel();
        //实例化V
        showView = view;

    }

    //在P层使用WeakReference管理外部类对象
    public void attchView(T t){
        //创建弱引用
        tWeakReference = new WeakReference<>(t);
    }


    public void relate() {
        //发送数据
        showModel.lianxi();
        //jiekou
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onResult(JSONArray data1) {
                showView.Show(data1);
            }
        });
    }

    //解除关联
    public  void deathView(){
        if (tWeakReference.get()!=null){
            tWeakReference.clear();
            tWeakReference=null;
        }
    }

}
