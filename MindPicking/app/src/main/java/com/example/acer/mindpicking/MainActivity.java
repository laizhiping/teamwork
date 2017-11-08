package com.example.acer.mindpicking;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import util.FileAdapter;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Folder[] folders;
    private Note[] notes;
    private ArrayList< Folder >foldersList = new ArrayList<Folder>() ;;
    //private ArrayList< String >foldersList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.my_listview);

        int[] noteImages={R.drawable.ba75735d6e5e8246d48dd3a532620af4,R.drawable.ae690ee5d271db7ed6531fd1b1bd5f4e,R.drawable.bac9609fa534520309cb48446863f644,
                R.drawable.e362ad63d8ce05c8160d890a7610f4c7,R.drawable.bing,R.drawable.xue,R.drawable.tree,R.drawable.sunshine,R.drawable.sky};
        notes=new Note[noteImages.length];
        for(int i=0;i<notes.length;i++){
            notes[i]=new Note();
            notes[i].setNoteName("note"+i);
            notes[i].setImage(noteImages[i]);
        }
        String[] folderNames={"人性思考","生活感悟","学习经验"};
        folders=new Folder[folderNames.length];
        for(int i=0;i<folders.length;i++){
            folders[i]=new Folder();
            folders[i].setFoldName(folderNames[i]);
        }
        for(int i=0;i<notes.length;i++){
            folders[i%3].addNote(notes[i]);
        }

        for(int i = 0; i< folders.length; i++) {

            foldersList.add(folders[i]);
    }
/*
        class FileAdapter extends BaseAdapter {
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
                View myView = View.inflate(MainActivity.this,R.layout.mylistitem, null);
                TextView textView=(TextView)myView.findViewById(R.id.item_text);
                //ImageView imageView=(ImageView)myView.findViewById(R.id.item_image);
                //textView.setText(foldersList.get(i));
                textView.setText(foldersList.get(i).getFoldName());
                //imageView.setImageResource(R.drawable.bing);//类中无此属性，仅测试用
                return myView;
            }
        }*/
    //配置适配器
    FileAdapter adapter = new FileAdapter(foldersList,this);
        listView.setAdapter(adapter);
    }
}
