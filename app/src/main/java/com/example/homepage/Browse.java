package com.example.homepage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.joblistAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import java.util.ArrayList;
import java.util.List;

import static com.example.homepage.R.layout.add;
import static com.example.homepage.R.layout.home;
import static com.example.homepage.R.layout.search;
import com.example.Adapter.joblistAdapter;

public class Browse extends AppCompatActivity {
    private EditText searchBox;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public List<post_job_format> list = new ArrayList<>();
    public List<post_job_format> newList = new ArrayList<>();
    public static String classPath = "selectedjob";
    //GridLayout mainGridLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(search);

        //mainGridLayout = (GridLayout)findViewById(R.id.mainGridLayout);
        //setSingleEvent(mainGridLayout);
        searchBox = findViewById(R.id.searchBox);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("project/"+ FirebaseAuth.getInstance().getUid());
        getDataFromFirebase(databaseReference);

        searchBox.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                    getDataFromFirebase(databaseReference);
                else
                    getDataFromSearchQuery(s.toString());
            }

            @Override
            public void afterTextChanged(Editable mEdit)
            {
            }
        });

//        getDataFromFirebase(databaseReference);

    }

    private void getDataFromFirebase(DatabaseReference databaseReference) {
        list.clear();
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot == null)
                            Toast.makeText(Browse.this, "No data found! Add a Puja on home page", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            list.add(dsp.getValue(post_job_format.class));
                        }
                        adapter = new joblistAdapter(Browse.this,list,classPath);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Browse.this, "Server Error! Check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getDataFromSearchQuery(String searchString) {
       newList.clear();
        for(int i=0; i< list.size(); i++){
            post_job_format object = list.get(i);
            if(object.getName().toLowerCase().trim().contains(searchString) || object.getCatagory().toLowerCase().trim().contains(searchString)){
                newList.add(object);
            }
        }
        adapter = new joblistAdapter(Browse.this, newList, classPath);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
    }



