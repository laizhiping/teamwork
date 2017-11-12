package com.example.acer.mindpicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import util.FolderAdapter;


public class MainActivity extends AppCompatActivity {
    private ImageButton concealFunction;
    private ImageView imageView2;
    private ImageView imageView;
    private ImageButton albumImput;
    private ImageButton shoot;
    private ImageButton newtype;
    private ImageView concealCarrier;
    private TextView album_import;
    private TextView character_recognition;
    private ImageView prepare;
    private TextView new_type;
    private ImageButton top;

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
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.my_listview);

        concealFunction = (ImageButton)findViewById(R.id.conceal);
        imageView = (ImageView) findViewById(R.id.imageView);
        albumImput = (ImageButton)findViewById(R.id.albumimput);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        shoot = (ImageButton)findViewById(R.id.shoot);
        newtype = (ImageButton)findViewById(R.id.newtype);
        new_type = (TextView)findViewById(R.id.newType);
        album_import =(TextView)findViewById(R.id.albumImport);
        character_recognition = (TextView)findViewById(R.id.characterRrecognition);
        concealCarrier = (ImageView)findViewById(R.id.circularCarrier);
        prepare = (ImageView) findViewById(R.id.prepare);
        top = (ImageButton)findViewById(R.id.top);

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

        concealFunction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                concealCarrier.setVisibility(View.GONE);
                albumImput.setVisibility(View.GONE);
                shoot.setVisibility(View.GONE);
                newtype.setVisibility(View.GONE);
                album_import.setVisibility(View.GONE);
                new_type.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                concealFunction.setVisibility(View.GONE);
                character_recognition.setVisibility(View.GONE);
                top.setVisibility(View.VISIBLE);
                prepare.setVisibility(View.VISIBLE);
            }
        });
        top.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                concealCarrier.setVisibility(View.VISIBLE);
                albumImput.setVisibility(View.VISIBLE);
                shoot.setVisibility(View.VISIBLE);
                newtype.setVisibility(View.VISIBLE);
                album_import.setVisibility(View.VISIBLE);
                new_type.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                concealFunction.setVisibility(View.VISIBLE);
                character_recognition.setVisibility(View.VISIBLE);
                top.setVisibility(View.GONE);
                prepare.setVisibility(View.GONE);
            }
        });
    }
}
