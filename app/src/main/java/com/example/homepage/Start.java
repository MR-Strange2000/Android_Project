package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Start extends AppCompatActivity {
    Button login,register;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        t1 = (TextView)findViewById(R.id.t1);
        login = (Button)findViewById(R.id.login1);
        register = (Button)findViewById(R.id.reg1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this,Main1.class);
                startActivity(i);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
}