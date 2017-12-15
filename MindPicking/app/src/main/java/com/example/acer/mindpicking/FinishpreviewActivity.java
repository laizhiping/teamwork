package com.example.acer.mindpicking;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class FinishpreviewActivity extends AppCompatActivity {
    private ImageView imageView1;
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private  TextView ganxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishpreview);
        imageView1=(ImageView)findViewById(R.id.input_image1);
        ganxiang = (TextView)findViewById(R.id.ganxiang);
        final Intent intent_get=getIntent();
        final String content = intent_get.getStringExtra("google");
        Bitmap bm=null;
        bm = BitmapFactory.decodeFile("/storage/emulated/0/MindPicking/"+content+".jpeg");
        imageView1.setImageBitmap(bm);
        List<Note> noteList = DataSupport.where("image=?",content).find(Note.class);
        ganxiang.setText(noteList.get(0).getFeeling().toString());
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_edit);
        mTitleTextView = (TextView) findViewById(R.id.text_title);

        mBackwardbButton = (Button) findViewById(R.id.button_backward);

    }

    protected void onBackward(View backwardView) {
        //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        finish();
    }

    /*button_forward.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", recognitionText.getText().toString());
// 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(FinishpreviewActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
        }
    });*/


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            //case R.id.button_forward:
                //onForward(v);
                //break;

            default:
                break;
        }
    }

}
