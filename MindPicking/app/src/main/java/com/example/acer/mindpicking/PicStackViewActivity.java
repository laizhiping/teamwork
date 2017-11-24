package com.example.acer.mindpicking;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import util.ImageAdapter;

public class PicStackViewActivity extends AppCompatActivity {
    private StackView stackView;
    private List<Note> images = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private TextView textView;
    private Timer down;
    private Timer timerup;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_view);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        stackView = (StackView) findViewById(R.id.stackview);
        textView = (TextView) findViewById(R.id.textview);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},140);
        //images=getPictures(Environment.getExternalStorageDirectory()+"/Download");
        Intent i =getIntent();
        int id=i.getIntExtra("google",0);
        images = DataSupport.where("folder_id=?",String.valueOf(id)).find(Note.class);
        if(images==null)
        {
            textView.setText("无图片显示");
        }
        else
        {
            imageAdapter = new ImageAdapter(images, this);
            stackView.setAdapter(imageAdapter);
            stackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textView.setText("第"+(position+1)+"张图片");
                }
            });
        }
    }}

  /*  public List<String> getPictures(final String strPath) {
        List<String> list = new ArrayList<String>();
        File file = new File(strPath);
        /*if(file.canRead())
        {
            Log.d("hello","canread");
        }*/
     /*   File[] allfiles = file.listFiles();
        if (allfiles == null) {
            return null;
        }
        for(int k = 0; k < allfiles.length; k++) {
            final File fi = allfiles[k];
            if(fi.isFile()) {
                int idx = fi.getPath().lastIndexOf(".");
                if (idx <= 0) {
                    continue;
                }
                String suffix = fi.getPath().substring(idx);
                if (suffix.toLowerCase().equals(".jpg") ||
                        suffix.toLowerCase().equals(".jpeg") ||
                        suffix.toLowerCase().equals(".bmp") ||
                        suffix.toLowerCase().equals(".png") ||
                        suffix.toLowerCase().equals(".gif") ) {
                    list.add(fi.getPath());
                }
            }
        }
        if(list.size()==0)
        {
            return null;
        }
        return list;
    }
}*/