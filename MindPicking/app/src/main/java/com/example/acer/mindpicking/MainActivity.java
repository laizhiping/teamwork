package com.example.acer.mindpicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import android.view.Window;
import android.widget.ListView;

import util.FolderAdapter;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Folder[] folder;
    private Note[] note;
    private ArrayList< Folder >foldersList = new ArrayList<Folder>() ;
    private  ArrayList<Note>notesList=new ArrayList<Note>();
    private String[] folderName={"人性思考","生活感悟","学习经验"};
    private int[] noteImages={R.drawable.ba75735d6e5e8246d48dd3a532620af4,R.drawable.ae690ee5d271db7ed6531fd1b1bd5f4e,R.drawable.bac9609fa534520309cb48446863f644,
            R.drawable.e362ad63d8ce05c8160d890a7610f4c7,R.drawable.bing,R.drawable.xue,R.drawable.tree,R.drawable.sunshine,R.drawable.sky};
    //private ArrayList< String >foldersList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.my_listview);

        note=new Note[noteImages.length];
        for(int i=0;i<note.length;i++){
            note[i]=new Note();
            note[i].setNoteName("note"+i);
            note[i].setImage(noteImages[i]);
        }
        folder=new Folder[folderName.length];
        for(int i=0;i<folder.length;i++){
            folder[i]=new Folder();
            folder[i].setFoldName(folderName[i]);
            folder[i].initAdapter(this);
        }
        for(int i=0;i<note.length;i++){        //将笔记加入到文件夹中
            folder[i%3].addNote(note[i]);
        }

        for(int i = 0; i< folder.length; i++) {

            foldersList.add(folder[i]);
    }

    //配置适配器
    FolderAdapter adapter = new FolderAdapter(foldersList,this);
       listView.setAdapter(adapter);
    }

}
