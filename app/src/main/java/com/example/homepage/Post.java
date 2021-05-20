package com.example.homepage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import static com.example.homepage.R.layout.add;
import static com.example.homepage.R.layout.search;

public class Post extends AppCompatActivity {
    GridLayout mainGridLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(add);
        mainGridLayout1 = (GridLayout)findViewById(R.id.mainGridLayout1);
        setSingleEvent(mainGridLayout1);
    }

    private void setSingleEvent(GridLayout mainGridLayout1) {
        for (int i=0;i<mainGridLayout1.getChildCount();i++){
            CardView cardview = (CardView)mainGridLayout1.getChildAt(i);
            final int finalI = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI==0){
                        Intent i = new Intent(Post.this,IT_Software.class);
                        startActivity(i);
                    }
                    else if(finalI==1){
                        Intent i = new Intent(Post.this,Write_Content.class);
                        startActivity(i);
                    }
                    else if(finalI==2){
                        Intent i = new Intent(Post.this,Design_Arch.class);
                        startActivity(i);
                    }
                    else if(finalI==3){
                        Intent i = new Intent(Post.this,Mob_Comp.class);
                        startActivity(i);
                    }
                    else if(finalI==4){
                        Intent i = new Intent(Post.this,Data_Admin.class);
                        startActivity(i);
                    }
                    else if(finalI==5){
                        Intent i = new Intent(Post.this,IT_Software.class);
                        startActivity(i);
                    }





                }
            });
        }
    }
}