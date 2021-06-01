package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Adapter.joblistAdapter;
import com.example.Adapter.open_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static com.example.homepage.selectedjob.jobSelected;

public class Completed extends AppCompatActivity {
    Button completed;
    private DatabaseReference databaseReference, db;
    //private open_adapter.MyviewHolder holder;
    FirebaseUser user;
    FirebaseAuth mAuth;
    public static post_job_format jobSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        completed = (Button)findViewById(R.id.button50);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("project");
        db = FirebaseDatabase.getInstance().getReference().child("past_details");
        user = mAuth.getCurrentUser();
        final int id = getIntent().getIntExtra("id", 0);
        jobSelected = open_adapter.getJobs(id);

        //setData(jobSelected);
        completed.setOnClickListener(new View.OnClickListener() {
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
                       Toast.makeText(Completed.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    public static String getSelectedJobId() {
        return jobSelected.getRid();
    }
}