package com.example.homepage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.homepage.R.layout.home;
import static com.example.homepage.R.layout.status_bar;


public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN = 6000;
    Animation topanim;
    CircleImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(status_bar);
        img = (CircleImageView)findViewById(R.id.dp1);
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        img.setAnimation(topanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this,Start.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);

    }




}