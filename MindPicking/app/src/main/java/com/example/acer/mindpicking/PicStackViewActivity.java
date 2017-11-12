package com.example.acer.mindpicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import util.ImageAdapter;

public class PicStackViewActivity extends AppCompatActivity {
    private StackView stackView;
    private int[] imageIds = {R.drawable.ym1,R.drawable.ym2,R.drawable.ym3,R.drawable.ym4};
    private List<Integer> images = new ArrayList<>();
    private util.ImageAdapter imageAdapter;
    private TextView textView;
    private Timer down;
    private Timer timerup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_view);
        stackView = (StackView) findViewById(R.id.stackview);
        textView = (TextView) findViewById(R.id.textview);
        initData();
        imageAdapter = new ImageAdapter(images, this);
        stackView.setAdapter(imageAdapter);
        stackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("第"+(position+1)+"个杨幂");
            }
        });
    }
    public void initData(){
        for (int i = 0; i < imageIds.length; i++) {
            images.add(imageIds[i]);
        }
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_down:
                if(timerup!=null){
                    timerup.cancel();
                }
                down = new Timer();
                down.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stackView.showNext();
                            }
                        });
                    }
                },0,1000);
                break;
            case R.id.btn_up:
                if(down!=null){
                    down.cancel();
                }
                timerup = new Timer();
                timerup.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stackView.showPrevious();
                            }
                        });
                    }
                },0,1000);
                break;
        }
    }
}

