package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.joblistAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class selectedjob extends AppCompatActivity {
        TextView title,name,name1,desc,desc1,category,category1,bid,bid1,price,price1;
        Button placebid,cancel;
private DatabaseReference databaseReference;
        FirebaseAuth mAuth;
    public static post_job_format jobSelected;

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
            String value = jobSelected.getBid();
            int i=Integer.parseInt(value);
            i = i+1;
            String val = Integer.toString(i);

            databaseReference.child(jobSelected.getRid()).child("bid").setValue(val);


            /*String value = jobSelected.getBid();
            int i=Integer.parseInt(value);
            i = i+1;
             value = Integer.toString(i);
             databaseReference.child("rid").child("bid").setValue(value);*/

            //jobSelected.setBid(val);
            //HashMap<String,Object> userMap = new HashMap<>();
            //userMap.put("bid",val);
           // databaseReference.updateChildren(userMap);
            Toast.makeText(getApplicationContext(),"Bid value Updated ",Toast.LENGTH_SHORT).show();
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

    public static String getSelectedJobId(){
        return jobSelected.getRid();
    }
        }