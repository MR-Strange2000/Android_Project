package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    TextView t1,t2;
    Button reg, log;
    DatePickerDialog picker;
    EditText edtname,edtmail,edtdate,edtaddress,edtpass,edtconpass;
    RadioGroup rg;
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("User");

        loadingBar = new ProgressDialog(this);
        
        t1 = (TextView)findViewById(R.id.textv);
        t2 = (TextView)findViewById(R.id.gender);
        reg = (Button)findViewById(R.id.btn_reg);
        log = (Button)findViewById(R.id.btn_login2);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        edtname = (EditText)findViewById(R.id.editName);
        edtmail = (EditText)findViewById(R.id.editMail);
        edtdate = (EditText)findViewById(R.id.editdate);
        edtdate.setInputType(InputType.TYPE_NULL);
        edtaddress = (EditText)findViewById(R.id.editaddress);
        edtpass = (EditText)findViewById(R.id.editPass);
        edtconpass = (EditText)findViewById(R.id.editPass1);

        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity2.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtmail.getText().toString();

                String pwdd = edtpass.getText().toString();
                String cnfPwd = edtconpass.getText().toString();
                RegisterUser(email,pwdd,cnfPwd);

            }
            
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext() , Main1.class);
                
                startActivity(i1);
            }
        });
    }

    private void RegisterUser(String email, String pwdd, String cnfPwd) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pwdd)){
            Toast.makeText(this, "Please Enter the Password !", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(cnfPwd)){
            Toast.makeText(this, "Please Confirm The Password !", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait , while we Register");
            loadingBar.show();
            if(pwdd.equals(cnfPwd)) {
                mAuth.createUserWithEmailAndPassword(email,pwdd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    User user = new User();
                                    String name = edtname.getText().toString();
                                    String date = edtdate.getText().toString();
                                    int gid = rg.getCheckedRadioButtonId();
                                    String gstr = ((RadioButton) findViewById(gid)).getText().toString();
                                    String Address = edtaddress.getText().toString();
                                    user.setName(name);
                                    user.setEmail(email);
                                    user.setDate(date);
                                    user.setGender(gstr);
                                    user.setAddress(Address);
                                    user.setPassword(pwdd);
                                    user.setCnfpassword(cnfPwd);
                                    userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(MainActivity2.this, "User Registration Done Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(MainActivity2.this , MainActivity.class);
                                                    startActivity(i);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext() , "Failed"+e.getMessage() , Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                                else{
                                    Toast.makeText(getApplicationContext() , "Registration Failed" , Toast.LENGTH_LONG).show();
                                }
                                loadingBar.dismiss();

                            }
                        });
            }else{
                Toast.makeText(getApplicationContext(), "Match error Between Password And Confirm Password", Toast.LENGTH_SHORT).show();
            }

        }
    }

}