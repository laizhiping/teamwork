package com.example.acer.mindpicking;

import android.app.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import android.widget.AdapterView.*;


import org.litepal.crud.DataSupport;

import static android.R.attr.textColor;
import static android.R.attr.typeface;

public class EditSetActivity extends Activity implements View.OnClickListener{


    private Button button1;
    private RelativeLayout editSet;
    private GridView fontcolorView;
    private GridView fontsetView;
    private GridView backgroundView;
    private Button button2;
    private Button copytextbutton;
    private EditText feeling;
    private List<Map<String,Object>>fontcolorlist;
    private List<Map<String,Object>>fontsetlist;
    private List<Map<String,Object>>backgroundlist;
    private Spinner spinner;
    private SeekBar textsizeset;
    private int[]fontcolor={R.drawable.red,R.drawable.blue,R.drawable.black,R.drawable.white,R.drawable.green,R.drawable.yellow};
    private int[]fontset={R.drawable.font1,R.drawable.font2,R.drawable.font3,R.drawable.font4};
    private int[]background={R.drawable.background1,R.drawable.background2,R.drawable.background3,R.drawable.background4};
    private String[]colorname={"红色","蓝色","黑色","白色","绿色","黄色"};
    private String[]fontname={"华文彩云","华文楷体","微软雅黑","华文中宋"};
    private SimpleAdapter fontcoloradapter;
    private SimpleAdapter fontadapter;
    private SimpleAdapter backgroudeadapter;
    private String contont;
    private int backgroundnum=R.drawable.back2;
    private EditText recognitionText;
    private ArrayAdapter<String> arr_adapter;

    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private int colornum=2;
    private int textsize=20;
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
        backgroundView=(GridView)findViewById(R.id.backgroundSet);
        Button btnBack=(Button)findViewById(R.id.button_backward) ;
        recognitionText =(EditText)findViewById(R.id.recognitionText) ;
        button2 =(Button)findViewById(R.id.button_forward);
        copytextbutton=(Button)findViewById(R.id.copytextButton);
        feeling =(EditText)findViewById(R.id.editText);
        textsizeset =(SeekBar)findViewById(R.id.seekbarFontSize);
        spinner=(Spinner)findViewById(R.id.spinner2);
        Intent intent1=getIntent();
        recognitionText.setText(intent1.getStringExtra("words"));

        contont=" ";


        List<Folder> allFolder = DataSupport.findAll(Folder.class);
        List<String> allFoldername =new ArrayList<String>();
        for(int i=0;i<allFolder.size();i++){
            allFoldername.add(allFolder.get(i).getFoldName());
        }
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allFoldername);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back=new Intent(EditSetActivity.this,MainActivity.class);
                startActivity(intent_back);
                finish();
            }
        });
        copytextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", recognitionText.getText().toString());
// 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(EditSetActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });


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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent_back=new Intent(EditSetActivity.this,PreviewActivity.class);
                intent_back.putExtra("folder",(String) spinner.getSelectedItem());
                contont=recognitionText.getText().toString();
                intent_back.putExtra("contont",contont);
                intent_back.putExtra("color",colornum);
                intent_back.putExtra("feeling",feeling.getText().toString());
                intent_back.putExtra("background",backgroundnum);
                intent_back.putExtra("textsize",textsize);
                startActivity(intent_back);
                finish();
            }
        });

        fontcolorlist = new ArrayList<Map<String, Object>>();
        fontcoloradapter = new SimpleAdapter(this,getcolorData(),R.layout.fontcoloritem,new String[]{"image","text"},new int[]{R.id.fontimage,R.id.fonttext});
        fontcolorView.setAdapter(fontcoloradapter);
        fontcolorView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                     switch(position){
                                                         case 0:
                                                             recognitionText.setTextColor(Color.RED);
                                                             colornum=0;
                                                             break;
                                                         case 1:
                                                             recognitionText.setTextColor(Color.BLUE);
                                                             colornum=1;
                                                             break;
                                                         case 2:
                                                             recognitionText.setTextColor(Color.BLACK);
                                                             colornum=2;
                                                             break;
                                                         case 3:
                                                             recognitionText.setTextColor(Color.WHITE);
                                                             colornum=3;
                                                             break;
                                                         case 4:
                                                             recognitionText.setTextColor(Color.GREEN);
                                                             colornum=4;
                                                             break;
                                                         case 5:
                                                             recognitionText.setTextColor(Color.YELLOW);
                                                             colornum=5;
                                                             break;
                                                         default:
                                                             break;

                                                     }
                                                 }
                                             });

        changeGridView(fontcolorView,fontcolorlist);

        fontsetlist = new ArrayList<Map<String, Object>>();
        fontadapter= new SimpleAdapter(this,getfontData(),R.layout.fontcoloritem,new String[]{"image","text"},new int[]{R.id.fontimage,R.id.fonttext});
        fontsetView.setAdapter(fontadapter);
        changeGridView(fontsetView,fontsetlist);

        backgroundlist = new ArrayList<Map<String, Object>>();
        backgroudeadapter = new SimpleAdapter(this,getbackgroundeData(),R.layout.setback_layout,new String[]{"image"},new int[]{R.id.fontimage});
        backgroundView.setAdapter(backgroudeadapter);
        changeGridView(backgroundView,backgroundlist);
        int itemWidth = dip2px(this, 100)+200;
        // item之间的间隔
        int itemPaddingH = dip2px(this, 1);
        int size = backgroundlist.size();
        // 计算GridView宽度
        int gridviewWidth = size * (itemWidth + itemPaddingH)-10;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        backgroundView.setLayoutParams(params);
        backgroundView.setColumnWidth(itemWidth);
        backgroundView.setHorizontalSpacing(itemPaddingH);
        backgroundView.setStretchMode(GridView.NO_STRETCH);
        backgroundView.setNumColumns(size);
        backgroundView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        recognitionText.setBackgroundResource(R.drawable.back3);
                        backgroundnum=R.drawable.back3;
                        break;
                    case 1:
                        recognitionText.setBackgroundResource(R.drawable.back2);
                        backgroundnum=R.drawable.back2;
                        break;
                    case 2:
                        recognitionText.setBackgroundResource(R.drawable.back1);
                        backgroundnum=R.drawable.back1;
                        break;
                    case 3:
                        recognitionText.setBackgroundResource(R.drawable.back4);
                        backgroundnum=R.drawable.back4;
                        break;
                    default:
                        break;
                }
            }
        });

        textsizeset.setMax(40);
        textsizeset.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               recognitionText.setTextSize(20+i);
                textsize = 20+i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
    private void changeGridView(GridView gridview,List<Map<String,Object>> flist) {
        // item宽度
        int itemWidth = dip2px(this, 100);
        // item之间的间隔
        int itemPaddingH = dip2px(this, 1);
        int size = flist.size();
        // 计算GridView宽度
        int gridviewWidth = size * (itemWidth + itemPaddingH);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridview.setLayoutParams(params);
        gridview.setColumnWidth(itemWidth);
        gridview.setHorizontalSpacing(itemPaddingH);
        gridview.setStretchMode(GridView.NO_STRETCH);
        gridview.setNumColumns(size);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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
    private List<Map<String,Object>> getbackgroundeData(){
        for(int i=0;i<background.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",background[i]);
            map.put("text","");
            backgroundlist.add(map);
        }
        return backgroundlist;
    }
    private void setupViews() {
        super.setContentView(R.layout.activity_edit);
        mTitleTextView = (TextView) findViewById(R.id.text_title);

        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
    }

    protected void onBackward(View backwardView) {
        //Toast.makeText(this, "点击返回，可在此处调用finish()", Toast.LENGTH_LONG).show();
        finish();
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
     * @paramforwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击预览", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(EditSetActivity.this,PreviewActivity.class);
        startActivity(intent);
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
