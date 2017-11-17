package com.example.acer.mindpicking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveActivity extends Activity {
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setContentView(R.layout.activity_save);
    }

        private void setupViews(){
            super.setContentView(R.layout.activity_edit);
            mTitleTextView = (TextView) findViewById(R.id.textView2);

            mBackwardbButton = (Button) findViewById(R.id.quxiao);
            mForwardButton = (Button) findViewById(R.id.querenbaocun);
        }

    protected void onBackward(View backwardView) {
        Toast.makeText(this, "点击取消", Toast.LENGTH_LONG).show();
        finish();
    }

    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击确认保存", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SaveActivity.this,MainActivity.class);
        startActivity(intent);
    }

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
    private void saveBitmap(Bitmap bitmap, String bitName)
    {
        File file = new File("/storage/emulated/0/DCIM/Camera/"+bitName);
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


