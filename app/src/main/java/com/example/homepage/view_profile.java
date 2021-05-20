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

import de.hdodenhof.circleimageview.CircleImageView;

public class view_profile extends AppCompatActivity {
    TextView txtName,t1,t11,t2,t22,t3,t33,t4,t44,t5,t55,t6,t66;
    private CircleImageView profileImageView;
    private DatabaseReference databaseReference;
    Button cancel;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        txtName = (TextView)findViewById(R.id.txtName);
        profileImageView = (CircleImageView)findViewById(R.id.dp);
        t1 = (TextView)findViewById(R.id.text22);
        t11 = (TextView)findViewById(R.id.editNam1);
        t2 = (TextView)findViewById(R.id.text33);
        t22 = (TextView)findViewById(R.id.editMail11);
        t3 = (TextView)findViewById(R.id.text44);
        t33 = (TextView)findViewById(R.id.editdate11);
        t4 = (TextView)findViewById(R.id.gen1);
        t44 = (TextView)findViewById(R.id.editgen1);
        t5 = (TextView)findViewById(R.id.text55);
        t55 = (TextView)findViewById(R.id.editaddress11);
        t6 = (TextView)findViewById(R.id.text66);
        t66 = (TextView)findViewById(R.id.editPass22);
        cancel = (Button)findViewById(R.id.button_update1);

        txtName.setText(Global.currentUser.getName());
        t11.setText(Global.currentUser.getName());
        t22.setText(Global.currentUser.getEmail());
        t33.setText(Global.currentUser.getDate());
        t44.setText(Global.currentUser.getGender());
        t55.setText(Global.currentUser.getAddress());
        t66.setText(Global.currentUser.getPassword());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this,Profile_sec.class));
            }
        });
        getUserinfo();
    }

    private void getUserinfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    String name = dataSnapshot.child("name").getValue().toString();
                    txtName.setText(name);
                    t11.setText(name);
                    String email = dataSnapshot.child("email").getValue().toString();
                    t22.setText(email);
                    String date = dataSnapshot.child("date").getValue().toString();
                    t33.setText(date);
                    String gender = dataSnapshot.child("gender").getValue().toString();
                    t44.setText(gender);
                    String address = dataSnapshot.child("address").getValue().toString();
                    t55.setText(address);
                    String password = dataSnapshot.child("password").getValue().toString();
                    t66.setText(password);
                    if(dataSnapshot.hasChild("image")){
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}