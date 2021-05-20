package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.homepage.R.layout.add;
import static com.example.homepage.R.layout.home;
import static com.example.homepage.R.layout.messages;
import static com.example.homepage.R.layout.profile;
import static com.example.homepage.R.layout.search;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(home);
        BottomNavigationView b = (BottomNavigationView)findViewById(R.id.b_nav);
        b.setOnNavigationItemSelectedListener(navlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_home1()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             Fragment selectedfragment = null;
            switch(item.getItemId()) {
                case R.id.nav_home:
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(home);
                    selectedfragment = new fragment_home1();
                    break;

                case R.id.nav_search:
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(search);
                    selectedfragment = new fragment_search();
                    break;

                case R.id.nav_post:
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(add);
                    selectedfragment = new fragment_add();
                    break;

                case R.id.nav_msg:
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(messages);
                    selectedfragment = new fragment_msg();
                    break;

                case R.id.nav_acc:
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(profile);
                    selectedfragment = new fragment_profile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
            return true;
        }


    };

}
