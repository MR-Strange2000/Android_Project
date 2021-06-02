package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Adapter.in_progressAdapter;
import com.example.Adapter.joblistAdapter;
import com.example.Adapter.open_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static com.example.homepage.R.layout.status_bar;
import static com.example.homepage.selectedjob.jobSelected;

public class Completed extends AppCompatActivity {
    Button completed,delete;
    private DatabaseReference databaseReference, db,db1;
    //private open_adapter.MyviewHolder holder;
    FirebaseUser user;
    FirebaseAuth mAuth;
    public static post_job_format jobSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(status_bar);
        completed = (Button)findViewById(R.id.button50);
        delete = (Button)findViewById(R.id.button60);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("project");
        db = FirebaseDatabase.getInstance().getReference().child("past_details");
        db1 = FirebaseDatabase.getInstance().getReference().child("in_progress_details");
        user = mAuth.getCurrentUser();
        final int id = getIntent().getIntExtra("id", 0);
        jobSelected = in_progressAdapter.getJobs(id);

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
               (db1.child(uid).orderByChild("rid").equalTo(rid)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data: dataSnapshot.getChildren()){
                            data.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        throw databaseError.toException();
                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Completed.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    public static String getSelectedJobId() {
        return jobSelected.getRid();
    }
}