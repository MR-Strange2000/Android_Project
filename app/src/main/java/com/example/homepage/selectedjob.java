package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(selectedjob.this, Post.class);
            startActivity(i);
        }
    });
    placebid.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int bidvalue = (int) snapshot.child("bid").getValue();
                    bidvalue = bidvalue+1;
                    HashMap<String,Object> userMap = new HashMap<>();
                    userMap.put("bid",bidvalue);
                    databaseReference.updateChildren(userMap);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    });


    getJobinfo();
}
private void getJobinfo() {
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
        }
        }