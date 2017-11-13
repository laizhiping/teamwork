package com.example.acer.mindpicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EditSetActivity extends AppCompatActivity {

    private Button button1;
    private RelativeLayout editSet;
    private GridView fontcolorView;
    private GridView fontsetView;
    private List<Map<String,Object>>fontcolorlist;
    private List<Map<String,Object>>fontsetlist;
    private int[]fontcolor={R.drawable.red,R.drawable.blue,R.drawable.black,R.drawable.white,R.drawable.green,R.drawable.yellow};
    private int[]fontset={R.drawable.font1,R.drawable.font2,R.drawable.font3,R.drawable.font4};
    private String[]colorname={"红色","蓝色","黑色","白色","绿色","黄色"};
    private String[]fontname={"华文彩云","华文楷体","微软雅黑","华文中宋"};
    private SimpleAdapter fontcoloradapter;
    private SimpleAdapter fontadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit);
        button1 = (Button)findViewById(R.id.bt1);
        editSet = (RelativeLayout)findViewById(R.id.editSet);
        fontcolorView=(GridView)findViewById(R.id.fontTheme);
        fontsetView=(GridView)findViewById(R.id.gvTheme);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSet.getVisibility()==View.GONE){
                    editSet.setVisibility(View.VISIBLE);
                }
                else if (editSet.getVisibility()==View.VISIBLE){
                    editSet.setVisibility(View.GONE);
                }
            }
        });
        fontcolorlist = new ArrayList<Map<String, Object>>();
        fontcoloradapter = new SimpleAdapter(this,getcolorData(),R.layout.fontcoloritem,new String[]{"image","text"},new int[]{R.id.fontimage,R.id.fonttext});
        fontcolorView.setAdapter(fontcoloradapter);

        fontsetlist = new ArrayList<Map<String, Object>>();
        fontadapter= new SimpleAdapter(this,getfontData(),R.layout.fontcoloritem,new String[]{"image","text"},new int[]{R.id.fontimage,R.id.fonttext});
        fontsetView.setAdapter(fontadapter);
    }
    private List<Map<String,Object>> getcolorData(){
        for(int i=0;i<fontcolor.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",fontcolor[i]);
            map.put("text",colorname[i]);
            fontcolorlist.add(map);
        }
        return fontcolorlist;
    }
    private List<Map<String,Object>> getfontData(){
        for(int i=0;i<fontset.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",fontset[i]);
            map.put("text",fontname[i]);
            fontsetlist.add(map);
        }
        return fontsetlist;
    }
}
