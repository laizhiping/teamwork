package util;

import android.content.Context;
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

/**
 * Created by ACER on 2017/11/15.
 */

public class SearchAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Note> notesArrayList ;
    public SearchAdapter(Context context,ArrayList<Note>notesArrayList){
        this.context=context;
        this.notesArrayList=notesArrayList;
    }
    @Override
    public int getCount() {
        return notesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return notesArrayList.get(i);
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
        searchImage.setImageResource(notesArrayList.get(i).getImage());
        searchTitle.setText(notesArrayList.get(i).getNoteName());
        searchFolder.setText(notesArrayList.get(i).getFolder());
        searchTime.setText(notesArrayList.get(i).getSaveTime());
        return myView;
    }
}
