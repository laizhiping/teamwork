package com.example.acer.mindpicking;


import android.Manifest;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.widget.TextView;

import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import util.ImageAdapter;


import com.bartoszlipinski.flippablestackview.FlippableStackView;
import com.bartoszlipinski.flippablestackview.StackPageTransformer;


/**
 * Created by Bartosz Lipinski
 * 12.12.14
 */
public class PicStackViewActivity extends AppCompatActivity{

    private static final int NUMBER_OF_FRAGMENTS = 20;//设置图片数量的参数

    private FlippableStackView mFlippableStack;

    private List<Note>images=new ArrayList<>();

    private TextView pic_view_text;

    //private TextView textview1=(TextView)findViewById(R.id.textview);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pic_view);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},140);
        Intent i=getIntent();
        int id=i.getIntExtra("google",0);
        images= images = DataSupport.where("folder_id=?",String.valueOf(id)).find(Note.class);
        pic_view_text=(TextView)findViewById(R.id.pic_view_text);
        if(images==null)
        {
            pic_view_text.setText("无图片");
        }
        else
        {
            ImageAdapter imageadapter=new ImageAdapter(images,this);
            boolean portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

            mFlippableStack = (FlippableStackView) findViewById(R.id.flippable_stack_view);
            mFlippableStack.initStack(4, portrait ?
                    StackPageTransformer.Orientation.VERTICAL :
                    StackPageTransformer.Orientation.HORIZONTAL);
            mFlippableStack.setAdapter(imageadapter);
            mFlippableStack.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    //Log.d("hello",String.valueOf(position));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }




    }

    /*private List<String> createViewPagerFragments(final String strPath) {
        mViewPagerFragments = new ArrayList<>();

        File file = new File(strPath);
        if(file.canRead())
        {
            Log.d("hello","canread");
        }
        File[] allfiles = file.listFiles();
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
                    mViewPagerFragments.add(fi.getPath());
                }
            }
        }
        if(mViewPagerFragments.size()==0)
        {
            return null;
        }
        return mViewPagerFragments;
    }*/

}