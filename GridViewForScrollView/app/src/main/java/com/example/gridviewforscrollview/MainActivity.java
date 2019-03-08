package com.example.gridviewforscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity  {

    private ScrollView scrollView;
    private GridViewForScroll gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //让ScrollView获得焦点
        scrollView = findViewById(R.id.scrollView);
        gv = findViewById(R.id.gv);

        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.requestFocus();
    }
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return 100;
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
            if (convertView==null){
                View.inflate(getApplicationContext(),R.layout.)
            }
            return null;
        }
    };
}
