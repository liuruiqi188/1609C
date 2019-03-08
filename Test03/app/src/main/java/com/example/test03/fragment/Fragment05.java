package com.example.test03.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.test03.R;
import com.example.test03.activity.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * @author liuruiqi
 * @fileName Fragment01
 * @package com.example.test03.fragment
 * @date 2019/2/27 20:45
 **/
public class Fragment05 extends Fragment {

    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag05,null,false);
        //找控件
        img = view.findViewById(R.id.qq_img);
        //图片点击登陆事件
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(getActivity());
                //登录授权监听
                umShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                        // 登录信息集合
                        // Log.i("Tag",map+"");
                        String s = map.get("profile_image_url");
                        // img是图片控件，只是为了验证登录成功后获取到你的QQ头像
                        Glide.with(getActivity()).load(s).into(img);
                    }
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });




        return view;
    }
}
