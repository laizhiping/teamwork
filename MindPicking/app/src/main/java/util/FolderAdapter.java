package util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.example.acer.mindpicking.Folder;
import com.example.acer.mindpicking.MainActivity;
import com.example.acer.mindpicking.PicStackViewActivity;
import com.example.acer.mindpicking.R;

import java.util.ArrayList;

/**
 * Created by ACER on 2017/11/6.
 */

public class FolderAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Folder> foldersList;
    public FolderAdapter(ArrayList<Folder>foldersList, Context context){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView=inflater.inflate(R.layout.home_page_list_item,null);
        //View myView = View.inflate(context,R.layout.home_page_list_item, null);
        TextView textView=(TextView)myView.findViewById(R.id.folder_name);
        Folder folder=foldersList.get(i);
        textView.setText(folder.getFoldName());
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                context.startActivity(new Intent(context,PicStackViewActivity.class));
            }
        });
        Gallery gallery=(Gallery)myView.findViewById(R.id.item_gallery);
        gallery.setAdapter(folder.getAdapter());
        return myView;
    }
}
