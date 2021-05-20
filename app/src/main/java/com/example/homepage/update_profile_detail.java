package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

public class update_profile_detail extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    EditText edtname,edtmail,edtdate,edtgen,edtadd,edtpass;
    Button update,cancel;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_detail);
        t1 = (TextView)findViewById(R.id.text1);
        t2 = (TextView)findViewById(R.id.text2);
        t3 = (TextView)findViewById(R.id.text3);
        t4 = (TextView)findViewById(R.id.text4);
        t5 = (TextView)findViewById(R.id.gen);
        t6 = (TextView)findViewById(R.id.text5);
        t7 = (TextView)findViewById(R.id.text6);


        edtname = (EditText)findViewById(R.id.editNam);
        edtmail = (EditText)findViewById(R.id.editMail1);
        edtdate = (EditText)findViewById(R.id.editdate1);
        edtgen = (EditText)findViewById(R.id.editgen);
        edtadd = (EditText)findViewById(R.id.editaddress1);
        edtpass = (EditText)findViewById(R.id.editPass2);


        update = (Button)findViewById(R.id.button_update);
        cancel = (Button)findViewById(R.id.button_cancel);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        /*edtname.setText(Global.currentUser.getName());
        edtmail.setText(Global.currentUser.getEmail());
        edtdate.setText(Global.currentUser.getDate());
        edtgen.setText(Global.currentUser.getGender());
        edtadd.setText(Global.currentUser.getAddress());
        edtpass.setText(Global.currentUser.getPassword());*/

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile_detail.this,Profile_sec.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateAndSave();
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
                    String email = dataSnapshot.child("email").getValue().toString();
                    String date = dataSnapshot.child("date").getValue().toString();
                    String gender = dataSnapshot.child("gender").getValue().toString();
                    String address = dataSnapshot.child("address").getValue().toString();
                    String password = dataSnapshot.child("password").getValue().toString();


                    edtname.setText(name);
                    edtmail.setText(email);
                    edtdate.setText(date);
                    edtgen.setText(gender);
                    edtadd.setText(address);
                    edtpass.setText(password);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ValidateAndSave() {
        if(TextUtils.isEmpty(edtname.getText().toString())){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtmail.getText().toString())){
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtdate.getText().toString())){
            Toast.makeText(this, "Please Enter The Date", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtgen.getText().toString())){
            Toast.makeText(this, "Please Enter Your Gender", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtadd.getText().toString())){
            Toast.makeText(this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtpass.getText().toString())){
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        }

        else{

                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("name",edtname.getText().toString());
                userMap.put("email",edtmail.getText().toString());
                userMap.put("date",edtdate.getText().toString());
                userMap.put("gender",edtgen.getText().toString());
                userMap.put("address",edtadd.getText().toString());
                userMap.put("password",edtpass.getText().toString());
                userMap.put("cnfpassword",edtpass.getText().toString());
                databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                user.updateEmail(edtmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(update_profile_detail.this, "Email Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(update_profile_detail.this, "Email Updation failed", Toast.LENGTH_SHORT).show();
                    }
                });
                user.updatePassword(edtpass.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(update_profile_detail.this, "Password Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(update_profile_detail.this, "Password Updation failed", Toast.LENGTH_SHORT).show();
                    }
                });



        }

    }
}