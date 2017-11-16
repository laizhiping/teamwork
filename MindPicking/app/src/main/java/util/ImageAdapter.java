package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by JorgeZhu on 2017/11/12.
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> mImages;
    private Context mContext;
    public ImageAdapter(List<String> mImages,Context context){
        this.mImages = mImages;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        Bitmap bm=null;
        bm = BitmapFactory.decodeFile(mImages.get(position));
        imageView.setImageBitmap(bm);
        //imageView.setImageResource(mImages.get(position));
        return imageView;
    }
}