package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Adapter.in_progressAdapter;
import com.example.Adapter.open_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class begin_project extends AppCompatActivity {
    Button begin, proceed;
    private DatabaseReference databaseReference, db;

    FirebaseUser user;
    FirebaseAuth mAuth;
    public static post_job_format jobSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_project);
        begin = (Button) findViewById(R.id.button70);
        proceed = (Button) findViewById(R.id.button80);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("project");
        db = FirebaseDatabase.getInstance().getReference().child("in_progress_details");

        user = mAuth.getCurrentUser();
        final int id = getIntent().getIntExtra("id", 0);
        jobSelected = open_adapter.getJobs(id);

        //setData(jobSelected);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project_details detail = new project_details();

                String uid = user.getUid();
                String rid = jobSelected.getRid();
                detail.setUid(uid);
                detail.setRid(rid);
                detail.setCompleted(true);
                db.child(mAuth.getCurrentUser().getUid()).push().setValue(detail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(begin_project.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(begin_project.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
    public static String getSelectedJobId() {
        return jobSelected.getRid();
    }
}