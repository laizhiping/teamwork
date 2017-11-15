package com.example.acer.mindpicking;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import static android.R.attr.textColor;

public class EditSetActivity extends Activity implements View.OnClickListener{


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

    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViews();
      //  getSupportActionBar().hide();
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

    private void setupViews() {
        super.setContentView(R.layout.activity_edit);
        mTitleTextView = (TextView) findViewById(R.id.text_title);

        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
    }

    protected void onBackward(View backwardView) {
        Toast.makeText(this, "点击返回，可在此处调用finish()", Toast.LENGTH_LONG).show();
        //finish();
    }

    protected void showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 提供是否显示提交按钮
     * @param forwardResId  文字
     * @param show  true则显示
     */
    protected void showForwardView(int forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forwardResId);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击预览", Toast.LENGTH_LONG).show();
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }


    public void setTitleColor(int textColor){mTitleTextView.setTextColor(textColor);}

    //取出FrameLayout并调用父类removeAllViews()方法


    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
                onForward(v);
                break;

            default:
                break;
        }
    }
}
