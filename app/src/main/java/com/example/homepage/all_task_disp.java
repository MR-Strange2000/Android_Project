package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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
    private RecyclerView rec;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase firebase;
    private DatabaseReference databaseReference,db;
    public static String ClassPath = "JobSelected";
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
<<<<<<< HEAD
        databaseReference = firebase.getReference("project_details");
=======
        databaseReference = firebase.getReference("project");
        db = firebase.getReference("project_details");
>>>>>>> f7ccf565fc9bfd6b9dab151e2b6a273b25b4a13f
        list = new ArrayList<>();
        open = new open_adapter(this , list, ClassPath);
        showData();
    }

    private void showData(){
        db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
<<<<<<< HEAD
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    post_job_format user = snap.getValue(post_job_format.class);
                    user = process_data(user);
                    list.add(user);
=======
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount()>0){
                    post_job_format user = snapshot.getValue(post_job_format.class);
                    String path = user.rid;


                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()+path).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            for(DataSnapshot snap: snapshot.getChildren()){
                                post_job_format user = snap.getValue(post_job_format.class);
                                list.add(user);
                            }
                            adapter = new open_adapter(all_task_disp.this,list,ClassPath);
                            adapter.notifyDataSetChanged();
                            rec.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {
                            Toast.makeText(all_task_disp.this, "Cannot Fetch", Toast.LENGTH_SHORT).show();
                        }
                    });
>>>>>>> f7ccf565fc9bfd6b9dab151e2b6a273b25b4a13f
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private post_job_format process_data(post_job_format data){
        return null;
    };
}