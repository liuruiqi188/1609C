package com.example.day0226;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button camera;
    private Button photoes;
    private ImageView img;
    private String PhotoFileName="";
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        camera = findViewById(R.id.camera);
        photoes = findViewById(R.id.photoes);
        img = findViewById(R.id.img);

        //点击事件
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //隐式跳转
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                PhotoFileName =Environment.getExternalStorageDirectory()+File.separator+ format.format(new Date())+"Photo.jpg";
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(PhotoFileName)));
                startActivityForResult(intent, 1);
            }
        });
    }

    //重写onActivityResult方法

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK){

            switch (requestCode){



                case 1:

                    System.out.println("Get CAMERA  RESULT ");

                    // String LocalPhoto= Environment.getExternalStorageDirectory()+File.separator+PhotoFileName;

                    Bitmap bitmap = getLoacalBitmap(PhotoFileName);

                    PhotoResult.setImageBitmap(bitmap);

                    break;

                default:

                    break;

            }

        }

    }

}
