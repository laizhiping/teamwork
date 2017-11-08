package util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.mindpicking.Folder;
import com.example.acer.mindpicking.MainActivity;
import com.example.acer.mindpicking.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 2017/11/6.
 */

public class FileAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Folder> foldersList;
    //public List<Folder>cloneable(List<Folder>)
    public FileAdapter(ArrayList<Folder>foldersList, Context context){
        this.context=context;
        this.foldersList=foldersList;
    }
    @Override
    public int getCount() {
        return foldersList.size();
    }

    @Override
    public Object getItem(int i) {
        return foldersList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater;
        //inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View myView=inflater.inflate(R.layout.mylistitem,null);
        View myView = View.inflate(context,R.layout.mylistitem, null);
        TextView textView=(TextView)myView.findViewById(R.id.item_text);
        //textView.setText("2");
        textView.setText(foldersList.get(i).getFoldName());

        ImageView imageView=(ImageView)myView.findViewById(R.id.item_image);
        imageView.setImageResource(foldersList.get(i).getNotes().get(0).getImage());

/*        ImageView imageView = new ImageView(context);
        //给ImageView设置资源  
        imageView.setImageResource(foldersList.get(i).getNotes().get(i).getImage());
        //设置比例类型  
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置布局 图片128x192显示  
        imageView.setLayoutParams(new Gallery.LayoutParams(128,192));*/
        return myView;
    }
}
