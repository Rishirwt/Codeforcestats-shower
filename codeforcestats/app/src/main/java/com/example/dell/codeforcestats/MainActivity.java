package com.example.dell.codeforcestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    ImageButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.e1);
        b1=findViewById(R.id.i1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String handle=e1.getText().toString().trim();

                Intent i=new Intent(MainActivity.this,profile.class);
                i.putExtra("handle",handle);
                startActivity(i);

            }
        });
    }
}
