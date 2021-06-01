package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.joblistAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class selectedjob extends AppCompatActivity {
    TextView title, name, name1, desc, desc1, category, category1, bid, bid1, price, price1;
    Button placebid, cancel;
    private DatabaseReference databaseReference, db;
    FirebaseUser user;
    FirebaseAuth mAuth;
    public static post_job_format jobSelected;
    private Toast mToastToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedjob);
        title = (TextView) findViewById(R.id.jobtitle);
        name = (TextView) findViewById(R.id.textname);
        name1 = (TextView) findViewById(R.id.textviewname);
        desc = (TextView) findViewById(R.id.jobdesc);
        desc1 = (TextView) findViewById(R.id.textviewdesc);
        category = (TextView) findViewById(R.id.jobcat);
        category1 = (TextView) findViewById(R.id.textviewcat);
        bid = (TextView) findViewById(R.id.jobbid);
        bid1 = (TextView) findViewById(R.id.textviewbid);
        price = (TextView) findViewById(R.id.jobprice);
        price1 = (TextView) findViewById(R.id.textviewprice);
        placebid = (Button) findViewById(R.id.button_bid);
        cancel = (Button) findViewById(R.id.button_cancel);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("project");
        db = FirebaseDatabase.getInstance().getReference().child("project_details");
        user = mAuth.getCurrentUser();
        final int id = getIntent().getIntExtra("id", 0);
        jobSelected = joblistAdapter.getJobs(id);

        setData(jobSelected);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(selectedjob.this, MainActivity.class);
                startActivity(i);
            }
        });
        placebid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project_details detail = new project_details();

                String uid = user.getUid();
                String rid = jobSelected.getRid();
                Integer room_id = jobSelected.get_room_id();

                Toast.makeText(selectedjob.this, "Room id is " + room_id + " Please Take noticee@@@!!!", Toast.LENGTH_LONG).show();
                Toast.makeText(selectedjob.this, "Room id is " + room_id + " Please Take noticee@@@!!!", Toast.LENGTH_LONG).show();

                detail.setUid(uid);
                detail.setRid(rid);
                detail.setCompleted(false);

                db.child(mAuth.getCurrentUser().getUid()).push().setValue(detail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(selectedjob.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                });

//<<<<<<< HEAD
//            String value = jobSelected.getBid();
//            int i=Integer.parseInt(value);
//            i = i+1;
//            value = Integer.toString(i);
//            post_job_format updatted = new post_job_format();
//            updatted.setBid(value);
//            String n = jobSelected.getName();
//            updatted.setName(n);
//            String d = jobSelected.getDescription();
//            updatted.setDescription(d);
//            String c = jobSelected.getCatagory();
//            updatted.setCatagory(c);
//            String p = jobSelected.getPrice();
//            updatted.setPrice(p);
//            String r = jobSelected.getRid();
//            updatted.setRid(r);
//            databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(updatted);
//            String value = jobSelected.getBid();
//            int i=Integer.parseInt(value);
//            i = i+1;
//            value = Integer.toString(i);
//=======


                String value = jobSelected.getBid();
                int i = Integer.parseInt(value);
                i = i + 1;
                value = Integer.toString(i);
//>>>>>>> 5fcd6885c3ef979581fe8764dc6b8d0be4b0b642
//            HashMap<String,Object> userMap = new HashMap<>();
//            userMap.put("bid",value);
//            databaseReference.child(mAuth.getCurrentUser().getUid()).child("bid").setValue(value);
                post_job_format updatted = new post_job_format();
                updatted.setBid(value);
                String n = jobSelected.getName();
                updatted.setName(n);
                String d = jobSelected.getDescription();
                updatted.setDescription(d);
                String c = jobSelected.getCatagory();
                updatted.setCatagory(c);
                String p = jobSelected.getPrice();
                updatted.setPrice(p);
                String r = jobSelected.getRid();
                updatted.setRid(r);
                String payme = jobSelected.getPayment();
                updatted.setPayment(payme);
                Integer romm_id = jobSelected.get_room_id();
                updatted.set_room_id(romm_id);
                // databaseReference.child(mAuth.getCurrentUser().getUid()).removeValue();
                databaseReference.child(r).setValue(updatted);
//                Toast.makeText(selectedjob.this, "Bid updated", Toast.LENGTH_SHORT).show();
                // databaseReference.child(mAuth.getCurrentUser().getUid()).removeValue();
//            databaseReference.child(r).setValue(updatted);


//>>>>>>> 5fcd6885c3ef979581fe8764dc6b8d0be4b0b642

                //jobSelected.setBid(val);
                //HashMap<String,Object> userMap = new HashMap<>();
                //userMap.put("bid",val);
                // databaseReference.updateChildren(userMap);
//                Toast.makeText(getApplicationContext(),"Bid value Updated ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(selectedjob.this, MainActivity.class);
                startActivity(intent);

            }
        });


        //getJobinfo();
    }

    /*private void getJobinfo() {
            databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           // if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
            String name = dataSnapshot.child("name").getValue().toString();
            name1.setText(name);

            String description = dataSnapshot.child("description").getValue().toString();
            desc1.setText(description);
            String category = dataSnapshot.child("catagory").getValue().toString();
            category1.setText(category);
            String bids = dataSnapshot.child("bid").getValue().toString();
            bid1.setText(bids);
            String Price = dataSnapshot.child("price").getValue().toString();
            price1.setText(Price);



            }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

            }
            });
            
            }*/
    private void setData(post_job_format jobSelected) {
        name1.setText(jobSelected.getName());
        desc1.setText(jobSelected.getDescription());
        category1.setText(jobSelected.getCatagory());
        bid1.setText(jobSelected.getBid());
        price1.setText(jobSelected.getPrice());

    }

    public static String getSelectedJobId() {
        return jobSelected.getRid();
    }
}
