package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Adapter.joblistAdapter;
import com.example.Adapter.open_adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class all_task_disp extends AppCompatActivity {
    RecyclerView rec;
    private FirebaseDatabase firebase;
    DatabaseReference databaseReference;
    private open_adapter open;
    private List<post_job_format> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task_disp);

        rec = (RecyclerView) findViewById(R.id.open_list);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        firebase = FirebaseDatabase.getInstance();
        databaseReference = firebase.getReference("project");
        list = new ArrayList<>();
        open = new open_adapter(this , list);
        showData();
    }

    private void showData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    post_job_format user = snap.getValue(post_job_format.class);
                    list.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(all_task_disp.this, "Cannot Fetch", Toast.LENGTH_SHORT).show();
            }
        });
    }
}