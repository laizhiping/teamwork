package com.example.acer.mindpicking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.acer.mindpicking.MainActivity;

import org.litepal.crud.DataSupport;

import CONST.ConstClass;
import util.SearchAdapter;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TextView textView=(TextView)findViewById(R.id.cancel_search);
        textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SearchActivity.this, MainActivity.class));
                    finish();
                }

            });
        List<Note> allNote = DataSupport.findAll(Note.class);
        SearchView searchView=(SearchView)findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText.trim())) {
                    ListView searchListView=(ListView)findViewById(R.id.search_list_view);
                    List<Note> noteList = DataSupport.where("noteName = ?", newText.trim()).find(Note.class);
                    searchListView.setAdapter(new SearchAdapter(SearchActivity.this, noteList));
                                 //此处置入查询条件，预留
                } else {

                }
                return true;
            }
        });

    }
}
