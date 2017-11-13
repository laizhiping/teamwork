package com.example.acer.mindpicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class EditSetActivity extends AppCompatActivity {

    private Button button1;
    private RelativeLayout editSet;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit);
        button1 = (Button)findViewById(R.id.bt1);
        editSet = (RelativeLayout)findViewById(R.id.editSet);
        editText = (EditText)findViewById(R.id.editText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSet.getVisibility()==View.GONE){
                    editSet.setVisibility(View.VISIBLE);

                }
                else if (editSet.getVisibility()==View.VISIBLE){
                    editSet.setVisibility(View.GONE);

                }
            }
        });
    }
}
