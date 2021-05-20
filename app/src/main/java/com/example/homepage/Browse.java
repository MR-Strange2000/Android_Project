package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.example.homepage.R.layout.add;
import static com.example.homepage.R.layout.home;
import static com.example.homepage.R.layout.search;

public class Browse extends AppCompatActivity {
    GridLayout mainGridLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(search);
        mainGridLayout = (GridLayout)findViewById(R.id.mainGridLayout);
        setSingleEvent(mainGridLayout);
    }

    private void setSingleEvent(GridLayout mainGridLayout) {
        for (int i=0;i<mainGridLayout.getChildCount();i++){
            CardView cardview = (CardView)mainGridLayout.getChildAt(i);
            final int finalI = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI==0){
                        Intent i = new Intent(Browse.this,IT_Software.class);
                        startActivity(i);
                    }
                    else if(finalI==1){
                        Intent i = new Intent(Browse.this,Write_Content.class);
                        startActivity(i);
                    }
                    else if(finalI==2){
                        Intent i = new Intent(Browse.this,Design_Arch.class);
                        startActivity(i);
                    }
                    else if(finalI==3){
                        Intent i = new Intent(Browse.this,Data_Admin.class);
                        startActivity(i);
                    }
                    else if(finalI==4){
                        Intent i = new Intent(Browse.this,Engg_Sci.class);
                        startActivity(i);
                    }
                    else if(finalI==5){
                        Intent i = new Intent(Browse.this,Sales_Mark.class);
                        startActivity(i);
                    }






                }
            });
        }
    }
}
