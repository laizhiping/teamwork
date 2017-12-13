package com.example.acer.mindpicking;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PreviewActivity extends AppCompatActivity {
    //private RelativeLayout mLayoutTitleBar;
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
        imageView=(ImageView)findViewById(R.id.input_image);
        final Intent intent_get=getIntent();
        final String content = intent_get.getStringExtra("contont");
        final String folder = intent_get.getStringExtra("folder");
        final String feel = intent_get.getStringExtra("feeling");
        int position =  intent_get.getIntExtra("color",2);
        int textsize = intent_get.getIntExtra("textsize",20);
        int backgroundenum =intent_get.getIntExtra("background",R.drawable.back2);
        Bitmap bitmap = textAsBitmap(content, textsize,position);
        mBackwardbButton =(Button)findViewById(R.id.button_backward);
        mBackwardbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back=new Intent(PreviewActivity.this,EditSetActivity.class);
                intent_back.putExtra("words",content);
                startActivity(intent_back);
            }
        });
        imageView.setVisibility(View.VISIBLE);
        imageView.setBackgroundResource(backgroundenum);//这里是背景图的选择
        imageView.setImageBitmap(bitmap);
        Button button2=(Button)findViewById(R.id.button_forward) ;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final EditText inputServer = new EditText(PreviewActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(PreviewActivity.this);
                builder.setTitle("自定义名称").setIcon(android.R.drawable.ic_dialog_info);
                //builder.setMessage("输入文件夹名称");
                builder.setView(inputServer);
                builder.setPositiveButton("确认保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {//输入为空需要加判断！！！！
                        String name=inputServer.getText().toString();
                        imageView.buildDrawingCache(true);
                        imageView.buildDrawingCache();
                        imageView.setDrawingCacheEnabled(true);
                        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());

                        SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                        Date date = new Date();
                        String imagename=Format.format(date);
                        saveBitmap(bitmap,imagename+".jpeg");
                        imageView.setDrawingCacheEnabled(false);
                        Note note = new Note();
                        note.setNoteName(name);
                        note.setImage(imagename);
                        note.setContent(content);
                        List<Folder> foldList = DataSupport.where("foldName = ?",folder).find(Folder.class);
                        /*String SDADS= String.valueOf(foldList.get(0).getNote().size());*/
                        //Toast.makeText(getApplicationContext(),SDADS, Toast.LENGTH_LONG).show();
                        note.setFolder(foldList.get(0));
                        note.setFeeling(feel);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String s=simpleDateFormat.format(date);
                        note.setSaveTime(s);
                        note.save();
                      /*  Toast.makeText(getApplicationContext(),SDADS, Toast.LENGTH_LONG).show();*/
                        foldList.get(0).getNote().add(note);

                        ContentValues values = new ContentValues();
                        values.put("Foldnum", foldList.get(0).getNote().size());
                        DataSupport.update(Folder.class, values,foldList.get(0).getId());
                        Intent intent_back=new Intent(PreviewActivity.this,MainActivity.class);
                        startActivity(intent_back);
                        finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_preview);
        mTitleTextView = (TextView) findViewById(R.id.text_title);

        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
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

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */
    /*public void onClick(View v) {

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
    }*/
    public static Bitmap textAsBitmap(String text, float textSize,int i) {

        TextPaint textPaint = new TextPaint();

// textPaint.setARGB(0x31, 0x31, 0x31, 0);
        switch(i){
            case 0:
                textPaint.setColor(Color.RED);
                break;
            case 1:
                textPaint.setColor(Color.BLUE);
                break;
            case 2:
                textPaint.setColor(Color.BLACK);
                break;
            case 3:
                textPaint.setColor(Color.WHITE);
                break;
            case 4:
                textPaint.setColor(Color.GREEN);
                break;
            case 5:
                textPaint.setColor(Color.YELLOW);
                break;
            default:
                break;
        }
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);

        StaticLayout layout = new StaticLayout(text, textPaint, 450,
                Layout.Alignment.ALIGN_CENTER, 1.5f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + 20,
                layout.getHeight() + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(10, 10);
// canvas.drawColor(Color.GRAY);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//绘制透明色
        layout.draw(canvas);
        Log.d("textAsBitmap",
                String.format("1:%d %d", layout.getWidth(), layout.getHeight()));
        return bitmap;
    }
    private void saveBitmap(Bitmap bitmap,String bitName)
    {
        File file = new File("/storage/emulated/0/MindPicking/"+bitName);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 90, out))
            {
                out.flush();
                out.close();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
