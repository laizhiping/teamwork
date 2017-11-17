package com.example.acer.mindpicking;;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Albumbutton extends Activity
{
    @ Override
    public void onCreate ( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albumbutton);

        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =new Intent(Albumbutton.this,ImageActivity.class);
                int data=1;
                intent.putExtra("extra_data",data);
                startActivityForResult(intent,1);

            }
        });
        Button button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =new Intent(Albumbutton.this,ImageActivity.class);
                int data=2;
                intent.putExtra("extra_data",data);
                startActivityForResult(intent,2);
            }
        });

    }
}
