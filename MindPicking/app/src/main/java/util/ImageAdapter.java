package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.mindpicking.Note;

import java.util.List;

/**
 * Created by JorgeZhu on 2017/11/17.
 */

public class ImageAdapter extends PagerAdapter {
    private List<Note> mImages;
    private Context mContext;
    public ImageAdapter(List<Note> mImages,Context context){
        this.mImages = mImages;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        Bitmap bm=null;
        bm = BitmapFactory.decodeFile("/storage/emulated/0/MindPicking/"+mImages.get(position).getImage().toString()+".jpeg");
        imageView.setImageBitmap(bm);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("MCJ",String.valueOf(position));
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(container.getChildAt(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}