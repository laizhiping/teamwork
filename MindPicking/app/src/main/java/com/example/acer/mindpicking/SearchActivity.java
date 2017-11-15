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
import com.example.acer.mindpicking.MainActivity;

import util.SearchAdapter;

import static com.example.acer.mindpicking.MainActivity.notesList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
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
        ListView searchListView=(ListView)findViewById(R.id.search_list_view);
        searchListView.setAdapter(new SearchAdapter(SearchActivity.this,notesList));
/*
        SearchView searchView=(SearchView)findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText.trim())) {
                        return false;              //此处置入查询条件，预留
                } else {

                }
                return false;
            }
        });*/

    }
}
