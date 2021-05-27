package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utils.post_job_format;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post extends AppCompatActivity{
//    Spinner dropdown;
//    String[] items = new String[]{"website" , "it" , "android" , "one" , "two"};
    EditText name , description , price;
    Button post;
//    private FirebaseDatabase database;
//    private DatabaseReference myRef;
    private FirebaseFirestore firestoreDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
//        dropdown = (Spinner)findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdown.setAdapter(adapter);
//        dropdown.setOnItemSelectedListener(this);

        name = (EditText)findViewById(R.id.project_name);
        description = (EditText)findViewById(R.id.project_description);
        price = (EditText)findViewById(R.id.price);
        post = (Button)findViewById(R.id.post_project);

//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("project");
        firestoreDB = FirebaseFirestore.getInstance();
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_job_format job = new post_job_format();
                job.setName(name.getText().toString());
                job.setPrice(price.getText().toString());
                job.setBid("0");
                job.setPayment("f");
                job.setDescription(description.getText().toString());

//                String key = myRef.push().getKey();
//                job.setRid(key);

                firestoreDB.collection("project").add(job).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Job successfully added", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Job adding failed try again later", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}