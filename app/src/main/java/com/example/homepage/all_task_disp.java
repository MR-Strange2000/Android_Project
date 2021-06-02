package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import java.util.Iterator;
import java.util.List;

import static com.example.homepage.R.layout.status_bar;

public class all_task_disp extends AppCompatActivity {
    private RecyclerView rec;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase firebase;
    private DatabaseReference databaseReference,db;
    public static String ClassPath = "JobSelected";
    private open_adapter open;
    public List<post_job_format> list2 = new ArrayList<>();;
    public List<project_details> list1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task_disp);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(status_bar);

        rec = (RecyclerView) findViewById(R.id.open_list);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        firebase = FirebaseDatabase.getInstance();
        databaseReference = firebase.getReference("project");
        db = firebase.getReference("project_details");
        showData();
    }

    private void showData(){
        list2.clear();

        db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<String> path = new ArrayList<>();


                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    path.add(item.child("rid").getValue().toString());
                }


                for(int i=0; i<path.size(); i++){
                        databaseReference.child(path.get(i)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                list2.add(snapshot.getValue(post_job_format.class));
                                adapter = new open_adapter(all_task_disp.this, list2, ClassPath);
                                adapter.notifyDataSetChanged();
                                rec.setAdapter(adapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(all_task_disp.this, "Cannot Fetch", Toast.LENGTH_SHORT).show();
                            }
                        });

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