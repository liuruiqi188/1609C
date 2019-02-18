package com.example.liuruiqi0218.imageloder;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author liuruiqi
 * @fileName ImageLoder
 * @package com.example.liuruiqi0218.imageloder
 * @date 2019/2/18 9:37
 **/
public class ImageLoder extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
    }
}
