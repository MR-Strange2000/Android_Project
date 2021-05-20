package com.example.homepage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Main1 extends AppCompatActivity {
    Button signIn,cancel;
    EditText user , pwd;
    TextView t1,t2;
    private FirebaseAuth mAuth;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        signIn = (Button)findViewById(R.id.btn_login);
        cancel = (Button)findViewById(R.id.btn_reg);


        user = (EditText)findViewById(R.id.editMail);
        pwd = (EditText) findViewById(R.id.editPass);
        t1 = (TextView)findViewById(R.id.textv);
        t2 = (TextView)findViewById(R.id.fg);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main1.this,Start.class);
                startActivity(i);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString();
                String pwd1 = pwd.getText().toString();
                Signin(email,pwd1);

                }

        });

    }

    private void Signin(String email, String pwd1) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pwd1)){
            Toast.makeText(this, "Please Enter the Password !", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait....while we Login");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email,pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        loadingBar.dismiss();
                        FirebaseDatabase.getInstance().getReference("User")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Global.currentUser = dataSnapshot.getValue(User.class);
                                        Intent i = new Intent(Main1.this, MainActivity.class);
                                        startActivity(i);
                                        Toast.makeText(Main1.this, "Login Done Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {


                                    }
                                });
                    }
                    else{
                        Toast.makeText(Main1.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
}
