package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.Adapter.in_progressAdapter;
import com.example.Adapter.past_adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class in_progress_disp extends AppCompatActivity {
    private RecyclerView rec;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase firebase;
    private DatabaseReference databaseReference,db;
    public static String classPath2 = "BeginProject";
    private in_progressAdapter inprogress;
    public List<post_job_format> list = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress_disp);
        rec = (RecyclerView) findViewById(R.id.open_list);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        firebase = FirebaseDatabase.getInstance();
        //databaseReference  = firebase.getReference("project_details");
        databaseReference = firebase.getReference("project");
        db = firebase.getReference("in_progress_details");

        inprogress = new in_progressAdapter(this , list, classPath2);
        showData();
    }
    private void showData(){
        list.clear();

        db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<String> path = new ArrayList<>();

//

                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    path.add(item.child("rid").getValue().toString());
                }

////                        String path = snapshot.child("rid").getValue().toString();

                for(int i=0; i<path.size(); i++){
                    databaseReference.child(path.get(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // for (DataSnapshot snap : snapshot.getChildren()) {
                            list.add(snapshot.getValue(post_job_format.class));
                            //list.add(user1);


                            // }
                            adapter = new in_progressAdapter(in_progress_disp.this, list, classPath2);
                            adapter.notifyDataSetChanged();
                            rec.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(in_progress_disp.this, "Cannot Fetch", Toast.LENGTH_SHORT).show();



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

