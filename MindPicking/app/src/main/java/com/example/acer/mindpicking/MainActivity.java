package com.example.acer.mindpicking;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CONST.ConstClass;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

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

    //private ArrayList< String >foldersList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},140);
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
        SQLiteDatabase db = Connector.getDatabase();

        final List<Folder> foldersList = DataSupport.findAll(Folder.class);
        final FolderAdapter adapter = new FolderAdapter(foldersList,this);
        listView.setAdapter(adapter);

        for(int i=0;i<foldersList.size();i++){
            foldersList.get(i).initAdapter(this);
        }
        ImageButton imageButton=(ImageButton)findViewById(R.id.search_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sxz = Connector.getDatabase();
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

            }
        });
        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MainActivity.this,AlbumActivity.class);
                int data=1;
                intent.putExtra("extra_data",data);
                startActivityForResult(intent,1);

            }
        });
        albumImput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =new Intent(MainActivity.this,AlbumActivity.class);
                int data=2;
                intent.putExtra("extra_data",data);
                startActivityForResult(intent,2);

            }
        });
        newtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final EditText inputServer = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("新建文件夹").setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("输入文件夹名称");
                builder.setView(inputServer);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String foldername=inputServer.getText().toString();
                        Folder folder = new Folder();
                        folder.setFoldName(foldername);
                        String SDADS= String.valueOf(folder.getNote().size());

                        folder.save();
                        foldersList.add(folder);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
/*        TextView picStackViewActivity=(TextView)findViewById(R.id.folder_name);
        picStackViewActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,PicStackViewActivity.class));
            }
        });*/
/*        ImageButton camera=(ImageButton) findViewById(R.id.shoot);
        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,EditSetActivity.class));
            }
        });*/

     /*   for(int i=0;i<noteImages.length;i++){
            Note temp=new Note();
            temp.setNoteName("note"+i);
            temp.setImage(noteImages[i]);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String s=simpleDateFormat.format(date);
            temp.setSaveTime(s);
            temp.save();
            ConstClass.notesList.add(temp);
        }
        for(int i=0;i<folderName.length;i++){
            Folder temp=new Folder();
            temp.setFoldName(folderName[i]);
            temp.save();
            temp.initAdapter(this);
            foldersList.add(temp);
        }
        for(int i=0;i<ConstClass.notesList.size();i++){
            ContentValues values = new ContentValues();
            values.put("folder", foldersList.get(i%folderName.length).getFoldName());
            DataSupport.update(Note.class, values, 2);  //将笔记加入到文件夹中
            foldersList.get(i%folderName.length).getNote().add(ConstClass.notesList.get(i));
        }*/

        //配置适配器



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
