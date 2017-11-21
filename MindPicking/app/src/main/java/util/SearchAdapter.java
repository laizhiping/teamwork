package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.mindpicking.Note;
import com.example.acer.mindpicking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 2017/11/15.
 */

public class SearchAdapter extends BaseAdapter{
    private Context context;
    private List<Note> notesList ;
    public SearchAdapter(Context context,List<Note> notesList){
        this.context=context;
        this.notesList=notesList;
    }
    @Override
    public int getCount() {
        return notesList.size();
    }

    @Override
    public Object getItem(int i) {
        return notesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = LayoutInflater.from(context).inflate(R.layout.search_list_item, null);
        ImageView searchImage=(ImageView)myView.findViewById(R.id.search_list_item_image);
        TextView searchTitle=(TextView)myView.findViewById(R.id.search_list_item_title);
        TextView searchFolder=(TextView)myView.findViewById(R.id.search_list_item_folder);
        TextView searchTime=(TextView)myView.findViewById(R.id.search_list_item_time);
        Bitmap bmp = BitmapFactory.decodeFile(notesList.get(i).getImage().toString());
        searchImage.setImageBitmap(bmp);
        searchTitle.setText(notesList.get(i).getNoteName());
        searchFolder.setText(notesList.get(i).getFolder().getFoldName());
        searchTime.setText(notesList.get(i).getSaveTime());
        return myView;
    }
}
